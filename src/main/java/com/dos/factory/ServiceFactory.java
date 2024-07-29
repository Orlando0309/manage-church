/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.factory;

import com.dos.contrat.IDAO;
import com.dos.contrat.IEntity;
import com.dos.factory.RootFactory;
import com.dos.util.ManagerUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author andri
 * @param <T>
 * @param <I>
 */
@NoArgsConstructor
@Builder
public class ServiceFactory<T extends IEntity<I>,I> extends RootFactory implements IDAO<T,I> {
    

    @Override
    public I save(T object) throws Exception {
         SessionFactory f=this.getFactory();
         Session session=f.openSession();
         Transaction transaction=session.beginTransaction();
        I id =(I) session.save(object);
         transaction.commit();

         return id;
    }

    @Override
    public List<I> saveMany(T... objects) throws Exception {
        List<I> ids = new ArrayList<>();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (int i = 0; i < objects.length; i++) {
                T object = objects[i];
                I id = (I) session.save(object);
                ids.add(id);

                // Flush and clear the session periodically to avoid memory issues
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return ids;
    }

    @Override
    public List<T> getAll(Class<T> object) throws Exception {
        List<T> resultList;
        try (Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(object);
            Root<T> rootEntry = cq.from(object);
            cq.select(rootEntry);
            resultList = session.createQuery(cq).getResultList();
        }
        return resultList;
    }

    @Override
    public T update(I id, T newdata) throws Exception {
        if(id==null){
            throw new IllegalArgumentException("The ID is null");
        }
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        T existingEntity = session.get((Class<T>) newdata.getClass(), id);
        if (existingEntity != null) {
            session.evict(existingEntity);
//            newdata.setId(id);
               ManagerUtils.transmute(existingEntity, newdata);
            T retour = (T) session.merge(existingEntity);
            transaction.commit();
            session.close();
            return retour;
        } else {
            transaction.rollback();
            session.close();
            throw new Exception("Entity not found with ID: " + id);
        }
    }

    @Override
    public T getById(I id,Class<T> instance) throws Exception {
        T retour;
        try (Session session = factory.openSession()) {
            retour = (T) session.get(instance, id);
        }
        return retour;
    }

    @Override
    public List<T> filter(Consumer<CriteriaQuery<T>> criteriaConsumer, Class<T> instance) throws Exception {
        List<T> resultList;
        try (Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(instance);
            criteriaConsumer.accept(cq);
            resultList = session.createQuery(cq).getResultList();
        }
        return resultList;
    }





}
