/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.factory;

import com.dos.contrat.IAdidyViewFactory;
import com.dos.model.Adidy;
import com.dos.model.AdidyView;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 *
 * @author andri
 */
@AllArgsConstructor
@Service("adidyView")
public class AdidyViewFactory extends ServiceFactory<AdidyView,Long>  implements IAdidyViewFactory<AdidyView, Integer>{
    
   
    
    @Override
    public List<AdidyView> getAdidy(Integer annee, Integer state) throws Exception {
        List<AdidyView> resultList;
        try (Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<AdidyView> cq = cb.createQuery(AdidyView.class);
            Root<AdidyView> rootEntry = cq.from(AdidyView.class);
            cq.select(rootEntry);
            
            //condition here
            if(annee==null){
                annee=Calendar.getInstance().get(Calendar.YEAR);
            }
            if(state==null){
                state=0;
            }
            
            cq.where(cb.and(
                    cb.equal(rootEntry.get("taona"),annee),
                    cb.equal(rootEntry.get("etat"),state)
            ));
            
            resultList = session.createQuery(cq).getResultList();
        }
        return resultList;
    }

    @Override
    public AdidyView persistAdidy(AdidyView adidy, String code, LocalDate date) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
