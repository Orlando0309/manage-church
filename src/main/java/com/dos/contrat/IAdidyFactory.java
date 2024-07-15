/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dos.contrat;

import java.time.LocalDate;

/**
 *
 * @author andri
 * @param <T>
 */
public interface IAdidyFactory<T extends  IEntity<Long>> extends IDAO<T,Long> {
    public T persistAdidy(T adidy,String code,LocalDate date) throws Exception;
}
