/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.factory;

import com.dos.contrat.IMouvementFactory;
import com.dos.model.Mouvement;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

@Service("mouvementfactory")
public class MouvementFactory extends ServiceFactory<Mouvement,Long> implements IMouvementFactory<Mouvement> {

    @Override
    public Mouvement addMouvement(Mouvement mouvement) throws Exception {
        Long savedMouvementId= this.save(mouvement);
        mouvement.setId(savedMouvementId);
        return mouvement;
    }

    @Override
    public boolean checkIfMouvementAlreadyExists(Mouvement mouvement) throws Exception {
        if(mouvement==null){
            throw new IllegalArgumentException("Null parameter given to checkIfMouvementAlreadyExists method");
        }
        try (Session session = factory.openSession()) {
            StringBuilder queryString = new StringBuilder("SELECT COUNT(*) FROM mouvement WHERE 1=1");

            if (mouvement.getCodemouvement() != null) {
                queryString.append(" AND codemouvement = :codemouvement");
            }

            if (mouvement.getDatemouvement() != null) {
                queryString.append(" AND EXTRACT(YEAR FROM datemouvement) = :year");
            }

            Query<Long> query = session.createNativeQuery(queryString.toString(), Long.class);

            if (mouvement.getCodemouvement() != null) {
                query.setParameter("codemouvement", mouvement.getCodemouvement());
            }

            if (mouvement.getDatemouvement() != null) {
                int year = mouvement.getDatemouvement().getYear();
                query.setParameter("year", year);
            }

            Long count = query.getSingleResult();

            return count > 0;
        }
    }


}
