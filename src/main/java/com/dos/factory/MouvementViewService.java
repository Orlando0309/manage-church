package com.dos.factory;

import com.dos.contrat.IMouvementViewService;
import com.dos.model.MouvementView;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("mouvementViewService")
public class MouvementViewService extends ServiceFactory<MouvementView,Long> implements IMouvementViewService<MouvementView> {
    @Override
    public void filterByFluxAndYearAndMonth(CriteriaQuery<MouvementView> cq, String flux, Integer year,Integer   month) {
        try(Session session=factory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            Root<MouvementView> root = cq.from(MouvementView.class);
            cq.select(root);
            List<Predicate> predicates = new ArrayList<>();

            if(flux!=null){
                predicates.add(cb.equal(root.get("flux"),flux));
            }
            if(year!=null){
                predicates.add(cb.equal(root.get("anneemouvement"),year));
            }
            if(month!=null){
                predicates.add(cb.equal(root.get("monthmouvement"),month));
            }
            cq.where(predicates.toArray(new Predicate[0]));
            cq.select(root);
        }
    }
}
