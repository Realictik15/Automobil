package com.automobil.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMPLETESETS")
public class CompleteSets {
    @Id
    @Column(name = "IDCOMPL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPLETESETS_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_COMPLETESETS_ID", allocationSize = 1, name = "COMPLETESETS_SEQ")
    private Long idCompl;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "COMFORT")
    private String comfort;

    @Column(name = "SAFETY")
    private String safety;

    @Column(name = "MULTI")
    private String multi;

    @Column(name = "SALON")
    private String salon;

    @Column(name = "INFO")
    private String info;

    @OneToMany(mappedBy = "completesets", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Possibles> possibles = new ArrayList<>();

    public void setPossibles(List<Possibles> possibles) {
        if (possibles != null) {
            if (!this.possibles.isEmpty()) {
                this.possibles.clear();
            }
            this.possibles.addAll(possibles);
            possibles.forEach(x -> x.setCompletesets(this));
        }
    }
    public void addCompleset(Possibles possibles){
        possibles.setCompletesets(this);
        this.possibles.add(possibles);
    }
}
