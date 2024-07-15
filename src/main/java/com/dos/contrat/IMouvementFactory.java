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
 * @param <C> criteria Builder
 */
public interface IMouvementFactory<T extends IEntity<Long>,C extends Object> {
    public T addMouvement(T mouvement) throws Exception;
    public List<T> getMouvement(C criteria) throws Exception;
}
