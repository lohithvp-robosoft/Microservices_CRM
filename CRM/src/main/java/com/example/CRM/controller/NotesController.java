package com.example.CRM.controller;

import com.example.CRM.dto.request.NotesRequestDTO;
import com.example.CRM.dto.response.ResponseDTO;
import com.example.CRM.exception.EmptyException;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.services.notes_service.NotesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotesController {

    @Autowired
    NotesServices notesServices;

    @PostMapping("/v1/{customerId}/note")
    public ResponseEntity<ResponseDTO> addANote(@PathVariable("customerId") Long customerId, @RequestBody NotesRequestDTO notesRequestDTO) throws NotFoundException {
        return notesServices.addANote(customerId, notesRequestDTO);
    }

    @DeleteMapping("/v1/{customerId}/note")
    public ResponseEntity<ResponseDTO> deleteANote(@PathVariable("customerId") Long customerId, @RequestHeader("noteId") long noteId) throws NotFoundException {
        return notesServices.deleteANote(customerId, noteId);
    }

    @GetMapping("/v1/{customerId}/note")
    public ResponseEntity<ResponseDTO> getANote(@PathVariable("customerId") Long customerId, @RequestHeader("noteId") Long noteId) throws NotFoundException {
        return notesServices.getOneNote(customerId, noteId);
    }

    @PutMapping("/v1/{customerId}/note")
    public ResponseEntity<ResponseDTO> updateANote(@PathVariable("customerId") Long customerId, @RequestHeader("noteId") Long noteId, @RequestBody NotesRequestDTO notesRequestDTO) throws NotFoundException {
        return notesServices.updateANote(customerId,
                noteId, notesRequestDTO);
    }

    @GetMapping("/v1/{customerId}/AllNotes")
    public ResponseEntity<ResponseDTO> getAllNotes(@PathVariable("customerId") Long customerId) throws NotFoundException, EmptyException {
        return notesServices.getAllNotesOfACustomer(customerId);
    }

    @DeleteMapping("/v1/{customerId}/AllNotes")
    public ResponseEntity<ResponseDTO> deleteAllNotes(@PathVariable("customerId") Long customerId) throws NotFoundException, EmptyException {
        return notesServices.deleteAllNotesOfACustomer(customerId);
    }
}
