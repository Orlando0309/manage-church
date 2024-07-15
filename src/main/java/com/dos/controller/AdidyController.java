/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.controller;

import com.dos.church.Constante;
import com.dos.church.HttpResult;
import com.dos.contrat.IAdidyFactory;
import com.dos.contrat.IAdidyViewFactory;
import com.dos.model.AdidyView;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andri
 */
@RestController
@RequestMapping("/api/adidy")
public class AdidyController {
    private final IAdidyViewFactory<AdidyView, Integer> adidyViewFactory;
    private final IAdidyFactory<AdidyView> adidyFactory;

    public AdidyController(
            @Qualifier("adidyView") IAdidyViewFactory<AdidyView, Integer> adidyViewFactory,
            @Qualifier("adidy") IAdidyFactory<AdidyView> adidyFactory) {
        this.adidyViewFactory = adidyViewFactory;
        this.adidyFactory = adidyFactory;
    }
    @PutMapping("/{id}")
    public HttpResult<AdidyView> updateState(@PathVariable("id") Integer id){
        try {
           AdidyView adidy= adidyViewFactory.getById(id.longValue(), AdidyView.class);
            System.out.println("adidy "+adidy.toString());
           adidyFactory.persistAdidy(adidy, adidy.getCodeAdidy(), LocalDate.now());
           return new HttpResult<>(adidy, Constante.HTTP_SUCCESS, "The adidy has been paid");
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpResult<>(null, Constante.HTTP_ERROR,e.getMessage());
        }
    }
    
    @GetMapping
    public List<AdidyView> getAdidy(@RequestParam(required = false) Integer annee, @RequestParam(required = false) Integer state) {
        try {
            return adidyViewFactory.getAdidy(annee, state);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return null;
        }
    }
}
