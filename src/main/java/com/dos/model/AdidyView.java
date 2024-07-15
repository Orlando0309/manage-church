/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.model;

import com.dos.contrat.IEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 *
 * @author andri
 */
@Entity(name = "V_adidy")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdidyView implements IEntity<Long>{
     @Id
    private Long id;

    @Column(name = "idadidy")
    private Long idAdidy;

    @Column(name = "codeadidy")
    private String codeAdidy;

    @Column(name = "labeladidy")
    private String labelAdidy;

    private Double montant;

    private Integer taona;

    private Integer etat;
}
