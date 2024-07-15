/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dos.contrat;

/**
 *
 * @author andri
 * @param <ID>
 */
public interface IEntity<ID extends Object> {
    public ID getId();
    public void setId(ID id);
}
