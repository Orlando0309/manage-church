/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dos.contrat;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author andri
 * @param <T> the root model of the code
 */
public interface ICodeFactory<T extends IEntity<Long>>  extends IDAO<T,Long>{
    public T getCodeInfo(String code) throws Exception;
    public List<T> findByCode(String str) throws Exception;
    public HashMap<String,T> getCodesInfos(String...code) throws Exception;
}
