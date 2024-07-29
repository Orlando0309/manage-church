/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.controller;

import com.dos.church.HttpSuccessResponse;
import com.dos.church.HttpErrorResponse;
import com.dos.church.HttpResult;
import com.dos.contrat.ICodeFactory;
import com.dos.contrat.IDAO;
import com.dos.model.Code;
import com.dos.model.CodeMouvement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author andri
 */
@RestController
@RequestMapping("/api/code")
@CrossOrigin(origins="*")
public class CodeController {
    ICodeFactory<CodeMouvement> codeFactory;

    @Autowired
    IDAO<Code,Long> rootCodeFactory;

    public CodeController(@Qualifier("mouvementcodefactory") ICodeFactory<CodeMouvement> codeFactory) {
        this.codeFactory = codeFactory;
    }

    @GetMapping("/root")
    public HttpResult<List<Code>> getList(){
        try{
            List<Code> data=rootCodeFactory.getAll(Code.class);
            return new HttpSuccessResponse<>(data,"Found "+data.size());
        }catch(Exception ex){
            return new HttpErrorResponse<>(null,ex.getMessage());
        }
    }

    @PostMapping
    public HttpResult<CodeMouvement> saveOne(@RequestBody CodeMouvement code){
        try{
            Long id=codeFactory.save(code);
            code.setId(id);
            return new HttpSuccessResponse<>(code,"Saved Successfully!");
        }catch(Exception ex){
            return new HttpErrorResponse<>(null,ex.getMessage());
        }
    }
    @GetMapping
    public HttpResult<List<CodeMouvement>> searchCode(@RequestParam String q){
        try{
            List<CodeMouvement> dataResult=codeFactory.findByCode(q);
            return new HttpSuccessResponse<>(dataResult,"Found "+dataResult.size());
        }catch (Exception ex){
            return new HttpErrorResponse<>(null,ex.getMessage());
        }
    }
}
