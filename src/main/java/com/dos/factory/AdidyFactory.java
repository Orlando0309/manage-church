/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.factory;

import com.dos.contrat.IAdidyFactory;
import com.dos.contrat.IStateFactory;
import com.dos.model.AdidyAnnee;
import com.dos.model.AdidyView;
import com.dos.model.CodeMouvement;
import com.dos.model.Mouvement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author andri
 */
@NoArgsConstructor
@AllArgsConstructor
@Service("adidy")
public class AdidyFactory extends ServiceFactory<AdidyView, Long> implements IAdidyFactory<AdidyView>,IStateFactory<AdidyView, Integer> {
    
    @Autowired
    @Qualifier("mouvementcodefactory")
    private  CodeFactory codefactory;
    
    @Autowired
    private  MouvementFactory mvfactory;
    
    @Autowired
    private  AdidyAnneeFactory adfactory;



    @Override
    public AdidyView persistAdidy(AdidyView adidy, String code, LocalDate date) throws Exception {
        CodeMouvement codeValue = codefactory.getCodeInfo(code);
        Mouvement mouvement = new Mouvement();
        mouvement.setMontant(BigDecimal.valueOf(adidy.getMontant()));
        mouvement.setDatemouvement(date);
        mouvement.setCodemouvement(codeValue.getId().intValue());
        if(!mvfactory.checkIfMouvementAlreadyExists(mouvement))
            mvfactory.addMouvement(mouvement);
        boolean isUpdated = this.updateState(adidy, 1);
        if (isUpdated) {
            adidy.setEtat(1);
        }else
            throw new Exception("The adidy could not be saved");
//        condition check raha vrai retournena, erreur sinon
        return adidy;
//        mouvement
    }
    
    @Override
    public boolean updateState(AdidyView stateowner, Integer state) throws Exception {
        AdidyAnnee newData = new AdidyAnnee();
        newData.setEtat(state);
        newData.setId(stateowner.getId());
        AdidyAnnee retour = adfactory.update(stateowner.getId(), newData);
        return retour != null;
    }
}
