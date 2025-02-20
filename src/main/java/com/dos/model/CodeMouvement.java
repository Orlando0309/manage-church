/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.model;

/**
 *
 * @author andri
 */

import com.dos.contrat.IEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "codemouvement")
@Data
@NoArgsConstructor

public class CodeMouvement implements IEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rootcode")
    private Integer rootcode;

    @Setter(AccessLevel.NONE)
    @Column(name = "code", nullable = false, length = 10)
    private String code;

    @Column(name = "label", nullable = false, length = 50)
    private String label;

    @Column(name = "flux", nullable = false, length = 50)
    private String flux;

    public void setCode(String code) {
        this.code = code + "0".repeat(Math.max(0, 5 - code.length()));
    }

    //    // Getters and Setters will be generated by Lombok
//
//    // Relationships
//    @ManyToOne
//    @JoinColumn(name = "rootcode", insertable = false, updatable = false)
//    private Code rootCode;
}
