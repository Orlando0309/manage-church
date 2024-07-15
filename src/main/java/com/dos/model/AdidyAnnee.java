package com.dos.model;

import com.dos.contrat.IEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "adidyannee")
@Data
@NoArgsConstructor
public class AdidyAnnee implements IEntity<Long> {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idadidy")
    private Integer idadidy;

    @Column(name = "montant", nullable = false)
    private Double montant;

    @Column(name = "taona", nullable = false)
    private Integer taona;

    @Column(name = "etat", nullable = true)
    private Integer etat;

    public void setMontant(Double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant must be greater than 0");
        }
        this.montant = montant;
    }

    public void setTaona(Integer taona) {
        if (taona == null) {
            this.taona = java.time.Year.now().getValue();
        } else {
            this.taona = taona;
        }
    }

    public void setEtat(Integer etat) {
        if (etat != null && etat != 0 && etat != 1) {
            throw new IllegalArgumentException("Etat must be 0 or 1");
        }
        this.etat = etat;
    }
}
