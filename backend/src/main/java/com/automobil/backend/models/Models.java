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
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MODELS")
public class Models {
    @Id
    @Column(name = "IDMODEL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODELS_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_MODELS_ID", allocationSize = 1, name = "MODELS_SEQ")
    private Long idModel;

    @ManyToOne
    @JoinColumn(name = "IDMARK", nullable = false)
    private Marks mark;

    @ManyToOne
    @JoinColumn(name = "IDCLAS", nullable = false)
    private Classes classes;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "INFO")
    private String info;

    @Column(name = "IMAGE")
    private String image;

    @OneToMany(mappedBy = "model", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Generations> generations;

    @OneToMany(mappedBy = "model", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Advertisments> advertisments;

    @OneToMany(mappedBy = "model", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Reviews> reviews;

    public void setGenerations(List<Generations> generations) {
        if (generations != null) {
            if (!this.generations.isEmpty()) {
                this.generations.clear();
            }
            this.generations.addAll(generations);
            generations.forEach(x -> x.setModel(this));
        }
    }

    public void setAdvertisments(List<Advertisments> advertisments) {
        if (advertisments != null) {
            if (!this.advertisments.isEmpty()) {
                this.advertisments.clear();
            }
            this.advertisments.addAll(advertisments);
            advertisments.forEach(x -> x.setModel(this));
        }
    }

    public void setReviews(List<Reviews> reviews) {
        if (reviews != null) {
            if (!this.reviews.isEmpty()) {
                this.reviews.clear();
            }
            this.reviews.addAll(reviews);
            reviews.forEach(x -> x.setModel(this));
        }
    }
}
