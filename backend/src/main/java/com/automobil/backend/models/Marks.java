package com.automobil.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "MARKS")
public class Marks {
    @Id
    @Column(name = "IDMARK")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MARKS_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_MARKS_ID", allocationSize = 1, name = "MARKS_SEQ")
    private Long idMark;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDCOUNRTY", nullable = false)
    private Countries countries;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "TITLE_RU")
    private String titleRu;

    @Column(name = "INFO")
    private String info;

    @Column(name = "IMAGE")
    private String image;

    @OneToMany(mappedBy = "mark", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Models> models = new ArrayList<>();

    @OneToMany(mappedBy = "mark", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Advertisments> advertisments = new ArrayList<>();

    @OneToMany(mappedBy = "mark", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Reviews> reviews = new ArrayList<>();

    public void setModels(List<Models> models) {
        if (models != null) {
            if (!this.models.isEmpty()) {
                this.models.clear();
            }
            this.models.addAll(models);
            models.forEach(x -> x.setMark(this));
        }
    }

    public void setAdvertisments(List<Advertisments> advertisments) {
        if (advertisments != null) {
            if (!this.advertisments.isEmpty()) {
                this.advertisments.clear();
            }
            this.advertisments.addAll(advertisments);
            advertisments.forEach(x -> x.setMark(this));
        }
    }

    public void setReviews(List<Reviews> reviews) {
        if (reviews != null) {
            if (!this.reviews.isEmpty()) {
                this.reviews.clear();
            }
            this.reviews.addAll(reviews);
            reviews.forEach(x -> x.setMark(this));
        }
    }


}
