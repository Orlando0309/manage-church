/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dos.contrat;

import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author andri
 * @param <T> the Entity
 * @param <I> the Id Type
 */
public interface IDAO<T extends IEntity<I>,I> {
    public I save(T object) throws Exception;
    public List<I> saveMany(T...objects) throws  Exception;
    public List<T> getAll(Class<T> classObject) throws Exception;
    public T update(I id,T newdata) throws Exception;
    public T getById(I id,Class<T> instance) throws Exception;
    public List<T> filter(Consumer<CriteriaQuery<T>> criteriaConsumer, Class<T> instance) throws Exception;
    public SessionFactory getFactory();
}
