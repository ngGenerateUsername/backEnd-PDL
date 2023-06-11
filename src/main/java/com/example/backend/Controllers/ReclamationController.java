package com.example.backend.Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Entities.Client;
import com.example.backend.Entities.Reclamation;
import com.example.backend.dao.ClientRepo;
import com.example.backend.dao.ReclamationRepo;

@RestController
@RequestMapping("api/reclamation")
public class ReclamationController {
    
    @Autowired
    private ReclamationRepo _ormRec;

    @Autowired
    private ClientRepo _ormClient;

   
    @GetMapping("")
    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    private List<Reclamation> getAllReclamation() throws IOException
    {
        try {
            List<Reclamation> recList= _ormRec.findAll();
            return recList;
        } catch (Exception e) {
            // TODO: handle exception
            ResponseEntity.ok(e.getMessage());
            System.out.println("im inside exception shit *****");
            return null;
        }

    }

    @PreAuthorize("hasAuthority('client')")
    @PostMapping("")
    private Reclamation addReclamation(@RequestBody Reclamation newRec)
    {
        try {
        Client cl = _ormClient.findById(newRec.getClient().getId()).orElseThrow();
        newRec.setClient(cl);
        return _ormRec.save(newRec);
        } catch (Exception e) {
            System.out.println("im inside Exception!!!!!");
            ResponseEntity.ok(e.getMessage());
            return null;
        }
        
    }


}
