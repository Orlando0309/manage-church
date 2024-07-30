package com.dos.filter;

import com.dos.church.Constante;
import com.dos.contrat.IAnnualFilter;
import com.dos.factory.MouvementMonthlyFactory;
import com.dos.factory.RootFactory;
import com.dos.model.Balance;
import com.dos.model.Monthly;
import com.dos.model.MouvementView;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service("filterByYear")
public class MouvementFilterByYear extends RootFactory implements IAnnualFilter {

    @Override
    public List<Monthly<Balance>> filter(Integer year, boolean avg){
        try(Session session=factory.openSession()){
            CriteriaBuilder cb=session.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = cb.createQuery(Object[].class);
            Root<MouvementView> root = criteriaQuery.from(MouvementView.class);

            List<Selection<?>> selections = new ArrayList<>(Arrays.asList(root.get("anneemouvement"),
                    root.get("monthmouvement"),
                    root.get("flux")));

            List<Expression<?>> groupByFields = new ArrayList<>(Arrays.asList(
                    root.get("anneemouvement"),
                    root.get("monthmouvement"),
                    root.get("flux")
            ));

            Predicate predicate=cb.equal(root.get("anneemouvement"),year);

            if(!avg){
                selections.add(cb.sum(root.get("montant")));
            }
            else{
                selections.add(cb.avg(root.get("montant")));
            }

            criteriaQuery.where(predicate);
            criteriaQuery.multiselect(selections);
            criteriaQuery.groupBy(groupByFields);

            return mapResults(session.createQuery(criteriaQuery).getResultList());
        }
    }
    List<Monthly<Balance>> mapResults(List<Object[]> result){
        MouvementMonthlyFactory monthlyFactory=new MouvementMonthlyFactory();
        monthlyFactory.init();
        for(Object[] r:result){
            //0 anneemouvement 1 monthmouvement 2 flux 3 montant
            if(r.length==4){
                BigDecimal montant=r[3] instanceof Double? BigDecimal.valueOf((Double)r[3]):(BigDecimal) r[3];
                Balance balance=new Balance(montant,BigDecimal.ZERO);
                String str= (String) r[2];
                if(Objects.equals(str, Constante.MV)){
                    balance=new Balance(BigDecimal.ZERO,montant);
                }
                System.out.println("");
                System.out.println("Init:"+balance);
                monthlyFactory.addData((Integer) r[1],balance);
            }
        }
        return monthlyFactory.getMonthResult();
    }


}
