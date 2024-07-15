/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.model;

import com.dos.contrat.IEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author andri
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Adidy implements IEntity<Long> {
     @Id
    private Long id;
     
     @Column
    private String code;

    @Column
    private String label;
}
