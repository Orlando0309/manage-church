/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dos.contrat;

import java.util.HashMap;

/**
 *
 * @author andri
 * @param <T> the root model of the code
 */
public interface ICodeFactory<T extends IEntity<Long>> {
    public T getCodeInfo(String code) throws Exception;
    public HashMap<String,T> getCodesInfos(String...code) throws Exception;
}
