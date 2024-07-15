/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.controller;

import com.dos.model.Code;
import com.dos.services.CodeService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andri
 */
@RestController
@RequestMapping("/api/code")
@AllArgsConstructor
public class CodeController {
    @Autowired
    final CodeService codeservice;
    
    @GetMapping
    public List<Code> get(){
        try {
            return codeservice.getAll(Code.class);
        } catch (Exception ex) {
            Logger.getLogger(CodeController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
