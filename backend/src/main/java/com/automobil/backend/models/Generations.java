package com.automobil.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GENERATIONS")
public class Generations {
    @Id
    @Column(name = "IDGEN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_GEN_ID", allocationSize = 1, name = "GEN_SEQ")
    private Long idGen;

    @ManyToOne
    @JoinColumn(name = "IDMODEL", nullable = false)
    private Models model;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "YEARSTARTDATE")
    private Date yearStartDate;

    @Column(name = "YEARENDDATE")
    private Date yearEndDate;

    @Column(name = "CLASSOFCAR")
    private String classOfCar;

    @Column(name = "IMAGE")
    private String image;

    @OneToMany(mappedBy = "generations", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Modifications> modifications = new ArrayList<>();

    @OneToMany(mappedBy = "generations", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Advertisments> advertisments = new ArrayList<>();

    @OneToMany(mappedBy = "generations", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Reviews> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "generations", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Bodies> bodies = new ArrayList<>();

    public void setModifications(List<Modifications> modifications) {
        if (modifications != null) {
            if (!this.modifications.isEmpty()) {
                this.modifications.clear();
            }
            this.modifications.addAll(modifications);
            modifications.forEach(x -> x.setGenerations(this));
        }
    }

    public void setAdvertisments(List<Advertisments> advertisments) {
        if (advertisments != null) {
            if (!this.advertisments.isEmpty()) {
                this.advertisments.clear();
            }
            this.advertisments.addAll(advertisments);
            advertisments.forEach(x -> x.setGenerations(this));
        }
    }

    public void setReviews(List<Reviews> reviews) {
        if (reviews != null) {
            if (!this.reviews.isEmpty()) {
                this.reviews.clear();
            }
            this.reviews.addAll(reviews);
            reviews.forEach(x -> x.setGenerations(this));
        }
    }

    public void setBodies(List<Bodies> bodies) {
        if (bodies != null) {
            if (!this.bodies.isEmpty()) {
                this.bodies.clear();
            }
            this.bodies.addAll(bodies);
            bodies.forEach(x -> x.setGenerations(this));
        }
    }
}
