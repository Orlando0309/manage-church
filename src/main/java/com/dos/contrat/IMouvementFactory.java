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
 */
public interface IMouvementFactory<T extends IEntity<Long>> extends IDAO<T,Long>{
    public T addMouvement(T mouvement) throws Exception;
    public boolean addManyMouvement(T... mouvement) throws Exception;
    public boolean checkIfMouvementAlreadyExists(T mouvement) throws Exception;

}
