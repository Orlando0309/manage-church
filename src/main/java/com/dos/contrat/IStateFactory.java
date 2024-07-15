/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dos.contrat;

/**
 *
 * @author andri
 * @param <T> the object to update the state
 * @param <S> the type of the state
 */
public interface IStateFactory<T extends IEntity<Long>,S extends Object>{
    public boolean updateState(T stateowner,S state) throws Exception;
}
