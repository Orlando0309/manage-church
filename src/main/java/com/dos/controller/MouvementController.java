package com.dos.controller;

import com.dos.church.Constante;
import com.dos.church.HttpResult;
import com.dos.contrat.IMouvementFactory;
import com.dos.contrat.IMouvementViewService;
import com.dos.model.Mouvement;
import com.dos.model.MouvementView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mouvement")
public class MouvementController {
    private final IMouvementFactory<Mouvement> mouvementFactory;
    private final IMouvementViewService<MouvementView> mouvementViewService;

    public MouvementController(
            @Qualifier("mouvementfactory") IMouvementFactory<Mouvement> mouvementFactory,
            @Qualifier("mouvementViewService") IMouvementViewService<MouvementView> mouvementViewService
    ) {
        this.mouvementFactory = mouvementFactory;
        this.mouvementViewService=mouvementViewService;
    }

    @PostMapping
    public HttpResult<Mouvement> saveMouvement(@RequestBody Mouvement mouvement){
        HttpResult<Mouvement> httpResult;
        try{
            Mouvement savedMouvement=mouvementFactory.addMouvement(mouvement);
            return new HttpResult<>(savedMouvement,Constante.HTTP_SUCCESS,"Saved successfully");
        }catch (Exception ex){
            httpResult=new HttpResult<>(null, Constante.HTTP_ERROR,ex.getMessage());
        }
        return httpResult;
    }

    @GetMapping("/")
    public  HttpResult<List<MouvementView>> getMouvement(@RequestParam(required = false) String flux,@RequestParam(required = false) Integer year){
        HttpResult<List<MouvementView>> httpResult;
        try {
            List<MouvementView> data=mouvementViewService.filter((cq)->{
             mouvementViewService.filterByFluxAndYear(cq,flux,year);
            }, MouvementView.class);
            return new HttpResult<>(data,Constante.HTTP_SUCCESS,null);
        }catch (Exception ex){
            httpResult=new HttpResult<>(null, Constante.HTTP_ERROR,ex.getMessage());
        }
        return httpResult;
    }
}
