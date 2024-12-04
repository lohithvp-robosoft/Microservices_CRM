package com.example.CRM.services.customer_service;

import com.example.CRM.dto.request.CaseManagementRequest;
import com.example.CRM.dto.request.CustomerRequestDTO;
import com.example.CRM.dto.response.CaseManagementResponse;
import com.example.CRM.dto.response.CustomerAndCaseMapper;
import com.example.CRM.dto.response.CustomerResponseData;
import com.example.CRM.dto.response.ResponseDTO;
import com.example.CRM.exception.EmptyException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.Customer;
import com.example.CRM.repository.CustomerRepository;
import com.example.CRM.utils.ResponseUtil;

import com.example.CRM.utils.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServicesImpl implements CustomerServices {

    @Autowired
    CustomerRepository customerRepository;

    @Value("${success.customer.added}")
    private String customerAddedMessage;

    @Value("${success.customer.deleted}")
    private String customerDeletedMessage;

    @Value("${success.customer.updated}")
    private String customerUpdatedMessage;

    @Value("${error.customer.notFound}")
    private String customerNotFoundMessage;

    @Value("${error.customer.empty}")
    private String customerListEmptyMessage;

    @Value("${url.case}")
    private String baseUrl;

    @Value("${error.case.notFound}")
    private String caseNotFoundMessage;

    @Value("${success.case.added}")
    private String caseAddedMessage;

    @Value("${success.case.deleted}")
    private String caseDeletedMessage;

    @Value("${success.case.updated}")
    private String caseUpdatedMessage;

    @Value("${case.keyName}")
    private String cases;

    @Override
    public ResponseEntity<ResponseDTO> addCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer(customerRequestDTO);
        customerRepository.save(customer);
        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        return ResponseUtil.successResponse(new CustomerResponseData(customerList),
                String.format(customerAddedMessage, customer.getFirstName() + " " + customer.getLastName()));
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllCustomer() throws EmptyException {
        ArrayList<Customer> customerList = (ArrayList<Customer>) customerRepository.findAll();
        if (customerList.isEmpty()) {
            throw new EmptyException(customerListEmptyMessage);
        }
        return ResponseUtil.successResponse(new CustomerResponseData(customerList));
    }

    @Override
    public ResponseEntity<ResponseDTO> getOneCustomerById(Long id) throws NotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            ArrayList<Customer> customersList = new ArrayList<>();
            customersList.add(customer.get());

            return ResponseUtil.successResponse(new CustomerResponseData(customersList));
        } else {
            throw new NotFoundException(String.format(customerNotFoundMessage, id));
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> findCustomerByFirstAndLastName(String name) throws NotFoundException {
        ArrayList<Customer> searchedCustomer = new ArrayList<>(customerRepository.findByFirstName(name));
        searchedCustomer.addAll(customerRepository.findByLastName(name));

        if (searchedCustomer.isEmpty()) {
            throw new NotFoundException("Customer " + name + " not found");
        }

        return ResponseUtil.successResponse(new CustomerResponseData(searchedCustomer));

    }

    @Override
    public ResponseEntity<ResponseDTO> updateCustomerById(Long id, CustomerRequestDTO updatedCustomerDetail)
            throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = getCustomer(updatedCustomerDetail, optionalCustomer);
            customerRepository.save(customer);
            ArrayList<Customer> customerList = new ArrayList<>();
            customerList.add(customer);

            return ResponseUtil.successResponse(new CustomerResponseData(customerList),
                    String.format(customerUpdatedMessage, customer.getFirstName() + " " + customer.getLastName()));
        }

        throw new NotFoundException(String.format(customerNotFoundMessage, id));
    }

    private static Customer getCustomer(CustomerRequestDTO updatedCustomerDetail, Optional<Customer> optionalCustomer) {
        Customer customer = optionalCustomer.get();
        if (updatedCustomerDetail.getFirstName() != null)
            customer.setFirstName(updatedCustomerDetail.getFirstName());
        if (updatedCustomerDetail.getLastName() != null)
            customer.setLastName(updatedCustomerDetail.getLastName());
        if (updatedCustomerDetail.getEmail() != null)
            customer.setEmail(updatedCustomerDetail.getEmail());
        if (updatedCustomerDetail.getPhone() != null)
            customer.setPhone(updatedCustomerDetail.getPhone());
        if (updatedCustomerDetail.getAddress() != null)
            customer.setAddress(updatedCustomerDetail.getAddress());
        customer.setAddress(updatedCustomerDetail.getAddress());
        return customer;
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteCustomerById(Long id) throws NotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            ArrayList<Customer> deletedCustomer = new ArrayList<>();
            deletedCustomer.add(customer.get());
            return ResponseUtil.successResponse(new CustomerResponseData(deletedCustomer), String.format(
                    customerDeletedMessage, customer.get().getFirstName() + " " + customer.get().getLastName()));
        }
        throw new NotFoundException(String.format(String.format(customerNotFoundMessage, id)));
    }

    public ResponseEntity<ResponseDTO> addACase(CaseManagementRequest caseManagementRequest, Long customerId) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            String url = String.format(baseUrl, customerId);
            String responseJson = WebClientUtils.externalPostRequest(url, caseManagementRequest);
            List<CaseManagementResponse> caseManagementDataList = ResponseUtil.extractDataFromResponse(cases, responseJson, CaseManagementResponse.class);
            CustomerAndCaseMapper customerAndCaseMapper = new CustomerAndCaseMapper(optionalCustomer.get(),
                    caseManagementDataList);
            return ResponseUtil.successResponse(customerAndCaseMapper, String.format(caseAddedMessage, customerId));
        }
        throw new NotFoundException(String.format(customerNotFoundMessage, customerId));
    }

    @Override
    public ResponseEntity<ResponseDTO> updateACase(CaseManagementRequest requestBody, Long customerId, Long caseId) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            String url = String.format(baseUrl, customerId) + "/" + caseId;
            String responseCaseJson = WebClientUtils.externalGetRequest(url);

            List<CaseManagementResponse> caseManagementDataList = ResponseUtil.extractDataFromResponse(cases, responseCaseJson, CaseManagementResponse.class);
            CustomerAndCaseMapper customerAndCaseMapper = new CustomerAndCaseMapper(optionalCustomer.get(),
                    caseManagementDataList);
            return ResponseUtil.successResponse(customerAndCaseMapper, String.format(caseUpdatedMessage, caseId, customerId));
        }
        throw new NotFoundException(String.format(customerNotFoundMessage, customerId));
    }

    @Override
    public ResponseEntity<ResponseDTO> getACase(Long customerId, Long caseId) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            String url = String.format(baseUrl, customerId) + "/" + caseId;
            String responseJson = WebClientUtils.externalGetRequest(url);
            List<CaseManagementResponse> caseManagementDataList = ResponseUtil.extractDataFromResponse(cases, responseJson, CaseManagementResponse.class);
            if (caseManagementDataList == null) {
                throw new NotFoundException(String.format(caseNotFoundMessage, caseId));
            }
            CustomerAndCaseMapper customerAndCaseMapper = new CustomerAndCaseMapper(optionalCustomer.get(), caseManagementDataList);

            return ResponseUtil.successResponse(customerAndCaseMapper);
        }
        throw new NotFoundException(String.format(customerNotFoundMessage, customerId));
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteACase(Long customerId, Long caseId) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            String url = String.format(baseUrl, customerId) + "/" + caseId;
            String responseJson = WebClientUtils.externalDeleteRequest(url);
            List<CaseManagementResponse> caseManagementDataList = ResponseUtil.extractDataFromResponse(cases, responseJson, CaseManagementResponse.class);
            if (caseManagementDataList == null) {
                throw new NotFoundException(String.format(caseNotFoundMessage, caseId));
            }
            CustomerAndCaseMapper customerAndCaseMapper = new CustomerAndCaseMapper(optionalCustomer.get(), caseManagementDataList);
            return ResponseUtil.successResponse(customerAndCaseMapper, String.format(caseDeletedMessage, caseId, customerId));
        }
        throw new NotFoundException(String.format(customerNotFoundMessage, customerId));
    }
}
