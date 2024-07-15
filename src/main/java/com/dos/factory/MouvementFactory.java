/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.factory;

import com.dos.contrat.IMouvementFactory;
import java.util.List;
import com.dos.model.Mouvement;
import org.springframework.stereotype.Service;

@Service
public class MouvementFactory extends ServiceFactory<Mouvement,Long> implements IMouvementFactory<Mouvement, Object> {

    @Override
    public Mouvement addMouvement(Mouvement mouvement) throws Exception {
        Long savedMouvementId= this.save(mouvement);
        mouvement.setId(savedMouvementId);
        return mouvement;
    }

    @Override
    public List<Mouvement> getMouvement(Object criteria) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
