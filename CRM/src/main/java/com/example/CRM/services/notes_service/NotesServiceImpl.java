package com.example.CRM.services.notes_service;

import com.example.CRM.dto.request.NotesRequestDTO;
import com.example.CRM.dto.response.NotesResponseData;
import com.example.CRM.dto.response.ResponseDTO;
import com.example.CRM.exception.EmptyException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.Customer;
import com.example.CRM.model.CustomerNotes;
import com.example.CRM.repository.CustomerNotesRepository;
import com.example.CRM.repository.CustomerRepository;
import com.example.CRM.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class NotesServiceImpl implements NotesServices {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerNotesRepository customerNotesRepository;

    @Value("${success.note.added}")
    private String noteAddedMessage;

    @Value("${success.note.deleted}")
    private String noteDeletedMessage;

    @Value("${success.note.deletedAll}")
    private String NoteDeletedAllMessage;

    @Value("${success.note.updated}")
    private String noteUpdatedMessage;

    @Value("${error.customer.notFound}")
    private String customerNotFoundMessage;

    @Value("${error.customer.empty}")
    private String customerListEmptyMessage;

    @Value("${error.note.notFound}")
    private String noteNotFoundMessage;

    @Value("${error.note.empty}")
    private String notesListEmptyMessage;

    @Override
    public ResponseEntity<ResponseDTO> addANote(Long customerId, NotesRequestDTO notesRequestDTO) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            CustomerNotes note = new CustomerNotes(notesRequestDTO, optionalCustomer.get());
            ArrayList<CustomerNotes> notesList = new ArrayList<>();
            notesList.add(note);
            customerRepository.save(optionalCustomer.get());
            customerNotesRepository.save(note);

            return ResponseUtil.successResponse(new NotesResponseData(notesList), String.format(noteAddedMessage, optionalCustomer.get().getFirstName() + " " + optionalCustomer.get().getLastName()));
        }
        throw new NotFoundException(String.format(customerNotFoundMessage, customerId));
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteANote(Long customerId, Long noteId) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Optional<CustomerNotes> optionalCustomerNotes = customerNotesRepository.findById(noteId);
            if (optionalCustomerNotes.isPresent()) {
                customerNotesRepository.delete(optionalCustomerNotes.get());
                ArrayList<CustomerNotes> notesList = new ArrayList<>();
                notesList.add(optionalCustomerNotes.get());

                return ResponseUtil.successResponse(new NotesResponseData(notesList), String.format(noteDeletedMessage, optionalCustomer.get().getFirstName() + " " + optionalCustomer.get().getLastName()));
            }
            throw new NotFoundException(String.format(noteNotFoundMessage, noteNotFoundMessage, noteId));
        }
        throw new NotFoundException(String.format(customerNotFoundMessage, customerId));
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllNotesOfACustomer(Long customerId) throws NotFoundException, EmptyException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            ArrayList<CustomerNotes> notesList = new ArrayList<>(optionalCustomer.get().getNotes());

            if (notesList.isEmpty()) {
                throw new EmptyException(notesListEmptyMessage);
            }

            return ResponseUtil.successResponse(new NotesResponseData(notesList));
        }
        throw new NotFoundException(String.format(customerNotFoundMessage, customerId));
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteAllNotesOfACustomer(Long customerId) throws NotFoundException, EmptyException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            ArrayList<CustomerNotes> notesList = new ArrayList<>(customer.getNotes());
            customer.getNotes().clear();
            customerRepository.save(customer);
            if (notesList.isEmpty()) {
                throw new EmptyException(notesListEmptyMessage);
            }

            return ResponseUtil.successResponse(new NotesResponseData(notesList), String.format(NoteDeletedAllMessage, customer.getFirstName() + " " + customer.getLastName()));
        }
        throw new NotFoundException(String.format(customerNotFoundMessage, customerId));
    }

    @Override
    public ResponseEntity<ResponseDTO> getOneNote(Long customerId, Long notesId) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Optional<CustomerNotes> optionalNote = customerNotesRepository.findById(notesId);
            if (optionalNote.isPresent()) {
                ArrayList<CustomerNotes> notesList = new ArrayList<>();
                notesList.add(optionalNote.get());

                return ResponseUtil.successResponse(new NotesResponseData(notesList));
            } else {
                throw new NotFoundException(String.format(noteNotFoundMessage, notesId));
            }
        }
        throw new NotFoundException(String.format(customerNotFoundMessage, customerId));

    }

    @Override
    public ResponseEntity<ResponseDTO> updateANote(Long customerId, Long notesId, NotesRequestDTO updatedNotes) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Optional<CustomerNotes> optionalNotes = customerNotesRepository.findById(notesId);
            if (optionalNotes.isEmpty()) {
                throw new NotFoundException(String.format(noteNotFoundMessage, notesId));
            }
            optionalNotes.ifPresent(customerNotes -> customerNotes.setText(updatedNotes.getText()));
            ArrayList<CustomerNotes> notesList = new ArrayList<>();
            notesList.add(optionalNotes.get());
            customerNotesRepository.save(optionalNotes.get());

            return ResponseUtil.successResponse(new NotesResponseData(notesList), String.format(noteUpdatedMessage, optionalCustomer.get().getFirstName() + " " + optionalCustomer.get().getLastName()));
        }
        throw new NotFoundException(String.format(customerNotFoundMessage, customerId));
    }
}
