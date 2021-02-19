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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MODIFICATIONS")
public class Modifications {
    @Id
    @Column(name = "IDMODIF")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODIF_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_MODIF_ID", allocationSize = 1, name = "MODIF_SEQ")
    private Long idModif;

    @ManyToOne
    @JoinColumn(name = "IDGEN", nullable = false)
    private Generations generations;

    @ManyToOne
    @JoinColumn(name = "IDENG", nullable = false)
    private Engines engines;

    @ManyToOne
    @JoinColumn(name = "IDTRANS", nullable = false)
    private Transmissions transmissions;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SITS")
    private Integer sits;

    @Column(name = "DOORS")
    private Integer doors;

    @OneToMany(mappedBy = "modification", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Advertisments> advertisments = new ArrayList<>();

    @OneToMany(mappedBy = "modification", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Reviews> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "modification", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Possibles> possibles = new ArrayList<>();

    public void setAdvertisments(List<Advertisments> advertisments) {
        if (advertisments != null) {
            if (!this.advertisments.isEmpty()) {
                this.advertisments.clear();
            }
            this.advertisments.addAll(advertisments);
            advertisments.forEach(x -> x.setModification(this));
        }
    }

    public void setReviews(List<Reviews> reviews) {
        if (reviews != null) {
            if (!this.reviews.isEmpty()) {
                this.reviews.clear();
            }
            this.reviews.addAll(reviews);
            reviews.forEach(x -> x.setModification(this));
        }
    }

    public void setPossibles(List<Possibles> possibles) {
        if (possibles != null) {
            if (!this.possibles.isEmpty()) {
                this.possibles.clear();
            }
            this.possibles.addAll(possibles);
            possibles.forEach(x -> x.setModification(this));
        }
    }
}
