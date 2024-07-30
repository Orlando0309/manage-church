package com.dos.contrat;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;

import java.util.List;

public interface IMouvementFilter<T, S, M> {
    List<Selection<?>> getSelection(T m, Root<M> root) throws Exception;
    List<Expression<?>> getGroupByFields(S m, Root<M> root) throws Exception;
    List<Predicate> getPredicates(T m, Root<M> root, CriteriaBuilder cb) throws Exception;

    public List<M> filter(T selection,S groupby,String aggregationFunction) throws  Exception;
}
