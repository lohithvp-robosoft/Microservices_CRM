package com.example.CRM.dto.response;

import com.example.CRM.model.CustomerNotes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class NotesResponseData {
    private ArrayList<CustomerNotes> notes;
}
