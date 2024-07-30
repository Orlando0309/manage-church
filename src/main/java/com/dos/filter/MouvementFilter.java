package com.dos.filter;

import com.dos.contrat.IMouvementFilter;
import com.dos.factory.RootFactory;
import com.dos.model.MouvementView;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MouvementFilter extends RootFactory implements IMouvementFilter<MouvementView, MouvementView, MouvementView> {

    @Override
    public List<Selection<?>> getSelection(MouvementView m, Root<MouvementView> root) throws Exception {
        List<Selection<?>> selections = new ArrayList<>();
        if (m.getAnneemouvement() != null) selections.add(root.get("anneemouvement"));
        if (m.getMonthmouvement() != null) {
            selections.add(root.get("monthmouvement"));
        }
        if (m.getCode() != null) {
            selections.add(root.get("code"));
        }
        // Add more fields as necessary
        return selections;
    }

    @Override
    public List<Expression<?>> getGroupByFields(MouvementView m, Root<MouvementView> root) throws Exception {
        List<Expression<?>> groupByFields = new ArrayList<>();
        if (m.getAnneemouvement() != null) {
            groupByFields.add(root.get("anneemouvement"));
        }
        if (m.getMonthmouvement() != null) {
            groupByFields.add(root.get("monthmouvement"));
        }
        if (m.getCode() != null) {
            groupByFields.add(root.get("code"));
        }
        // Add more fields as necessary
        return groupByFields;
    }

    @Override
    public List<Predicate> getPredicates(MouvementView m, Root<MouvementView> root, CriteriaBuilder cb) throws Exception {
        List<Predicate> predicates = new ArrayList<>();
//        if (m.getAnneemouvement() != null) {
//            predicates.add(cb.equal(root.get("anneemouvement"), m.getAnneemouvement()));
//        }
//        if (m.getMonthmouvement() != null) {
//            predicates.add(cb.equal(root.get("monthmouvement"), m.getMonthmouvement()));
//        }
//        if (m.getCode() != null) {
//            predicates.add(cb.equal(root.get("code"), m.getCode()));
//        }
        // Add more fields as necessary
        return predicates;
    }

    @Override
    public List<MouvementView> filter(MouvementView selection, MouvementView groupby, String aggregateFunction) throws Exception {
        List<MouvementView> result;
        try(Session session=factory.openSession()){
            CriteriaBuilder cb=session.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = cb.createQuery(Object[].class);
            Root<MouvementView> root = criteriaQuery.from(MouvementView.class);

            List<Selection<?>> selections = getSelection(selection, root);
            List<Expression<?>> groupByFields = getGroupByFields(groupby, root);
            List<Predicate> predicates = getPredicates(selection, root, cb);

            switch (aggregateFunction.toLowerCase()) {
                case "sum":
                    selections.add(cb.sum(root.get("montant")));
                    break;
                case "avg":
                    selections.add(cb.avg(root.get("montant")));
                    break;
                case "count":
                    selections.add(cb.count(root.get("montant")));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid aggregate function: " + aggregateFunction);
            }
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            criteriaQuery.multiselect(selections);
            criteriaQuery.groupBy(groupByFields);

            result = mapResults(selection,session.createQuery(criteriaQuery).getResultList());

        }
        return result;
    }
    private List<MouvementView> mapResults(MouvementView m,List<Object[]> results) {
        List<MouvementView> mappedResults = new ArrayList<>();
        for (Object[] result : results) {
            MouvementView mv = new MouvementView();
            for(Object o:result){
                System.out.println(o);
            }
            // Assuming the order of the fields in the result array
            int index = 0;
            if (result.length > index && m.getAnneemouvement() != null) {
                mv.setAnneemouvement((Integer) result[index++]);
            }
            if (result.length > index && m.getMonthmouvement() != null) {

                mv.setMonthmouvement((Integer) result[index++]);
            }
            if (result.length > index && m.getCode() != null) {
                mv.setCode((String) result[index++]);
            }
            if (result.length > index) {
                System.out.println("ato");
                mv.setMontant((BigDecimal) result[index]);
                System.out.println("vita");
            }
            mappedResults.add(mv);
        }
        return mappedResults;
    }


}
