package com.example.CRM.services.notes_service;

import com.example.CRM.dto.request.NotesRequestDTO;
import com.example.CRM.dto.response.ResponseDTO;
import com.example.CRM.exception.EmptyException;
import com.example.CRM.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface NotesServices {

    ResponseEntity<ResponseDTO> addANote(Long customerId, NotesRequestDTO notesRequestDTO) throws NotFoundException;

    ResponseEntity<ResponseDTO> deleteANote(Long customerId, Long NoteId) throws NotFoundException;

    ResponseEntity<ResponseDTO> getAllNotesOfACustomer(Long customerId) throws NotFoundException, EmptyException;

    ResponseEntity<ResponseDTO> deleteAllNotesOfACustomer(Long customerId) throws NotFoundException, EmptyException;


    ResponseEntity<ResponseDTO> getOneNote(Long customerId, Long notesId) throws NotFoundException;

    ResponseEntity<ResponseDTO> updateANote(Long customerId, Long notesId, NotesRequestDTO updatedNotes) throws NotFoundException;
}
