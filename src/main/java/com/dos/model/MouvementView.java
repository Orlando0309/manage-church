package com.dos.model;

import com.dos.contrat.IEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name="V_mouvement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MouvementView implements IEntity<Long> {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "rootcode")
    private Integer rootcode;

    @Column(name = "rtid")
    private Integer rtid;

    @Column(name = "code")
    private String code;

    @Column(name = "codelabel")
    private String codelabel;

    @Column(name = "codemouvement")
    private Integer codemouvement;

    @Column(name = "montant")
    private BigDecimal montant;

    @Column(name = "datemouvement")
    private LocalDate datemouvement;

    @Column(name = "anneemouvement")
    private Integer anneemouvement;

    @Column(name = "monthmouvement")
    private Integer monthmouvement;

    @Column(name = "daymouvement")
    private Integer daymouvement;

    @Column(name = "flux")
    private String flux;
}
