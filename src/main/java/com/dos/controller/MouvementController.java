package com.dos.controller;

import com.dos.church.*;
import com.dos.contrat.IAnnualFilter;
import com.dos.contrat.IMouvementFactory;
import com.dos.contrat.IMouvementFilter;
import com.dos.contrat.IMouvementViewService;
import com.dos.model.Balance;
import com.dos.model.Monthly;
import com.dos.model.Mouvement;
import com.dos.model.MouvementView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mouvement")
@CrossOrigin(origins="*")
public class MouvementController {
    private final IMouvementFactory<Mouvement> mouvementFactory;
    private final IMouvementViewService<MouvementView> mouvementViewService;

    @Autowired
    private final IMouvementFilter<MouvementView,MouvementView,MouvementView> filterFactory;


    @Autowired
    IAnnualFilter annualFilter;

    public MouvementController(
            @Qualifier("mouvementfactory") IMouvementFactory<Mouvement> mouvementFactory,
            @Qualifier("mouvementViewService") IMouvementViewService<MouvementView> mouvementViewService,
            IMouvementFilter<MouvementView,MouvementView,MouvementView> filterFactory
    ) {
        this.mouvementFactory = mouvementFactory;
        this.mouvementViewService=mouvementViewService;
        this.filterFactory=filterFactory;
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

    @PostMapping("/many")
    public HttpResult<Boolean> saveManyMouvement(@RequestBody Mouvement[] mouvement){
        try{
            boolean savedMouvement=mouvementFactory.addManyMouvement(mouvement);
            return new HttpResult<>(savedMouvement,Constante.HTTP_SUCCESS,"Saved successfully");
        }catch (Exception ex){
           return new HttpErrorResponse<>(false,ex.getMessage());
        }
    }

    @GetMapping
    public  HttpResult<List<MouvementView>> getMouvement(@RequestParam(required = false) String flux,@RequestParam(required = false) Integer year,@RequestParam(required = false) Integer month){
        try {
            List<MouvementView> data=mouvementViewService.filter((cq)->{
             mouvementViewService.filterByFluxAndYearAndMonth(cq,flux,year,month);
            }, MouvementView.class);
            return new HttpResult<>(data,Constante.HTTP_SUCCESS,null);
        }catch (Exception ex){
            return new HttpResult<>(null, Constante.HTTP_ERROR,ex.getMessage());
        }

    }


    @GetMapping("/filter")
    public HttpResult<?> filterByYear(@RequestParam Integer year,@RequestParam(required = false) Integer avg){
        try{
            List<Monthly<Balance>> result=annualFilter.filter(year,avg!=null);
            return new HttpSuccessResponse<>(result,"");
        }catch (Exception ex){
            ex.printStackTrace();
            return new HttpResult<>(null, Constante.HTTP_ERROR,ex.getMessage());
        }
    }
}
