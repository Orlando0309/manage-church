/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dos.contrat;

import java.util.List;

/**
 *
 * @author andri
 * @param <T>
 * @param <S>
 */
public interface IAdidyViewFactory<T extends IEntity<Long>,S> extends IAdidyFactory<T> {
    public List<T> getAdidy(Integer annee, S state) throws Exception;
}
