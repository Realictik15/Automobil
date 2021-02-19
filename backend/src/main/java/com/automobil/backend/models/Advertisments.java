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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADVERTISMENTS")
public class Advertisments {
    @Id
    @Column(name = "IDADVERT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADVERT_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_ADVERT_ID", allocationSize = 1, name = "ADVERT_SEQ")
    private Long idAdvert;

    @ManyToOne
    @JoinColumn(name = "IDUSER", nullable = false)
    private Clients clients;

    @ManyToOne
    @JoinColumn(name = "IDMARK", nullable = false)
    private Marks mark;

    @ManyToOne
    @JoinColumn(name = "IDMODEL", nullable = false)
    private Models model;

    @ManyToOne
    @JoinColumn(name = "IDGEN", nullable = false)
    private Generations generations;

    @ManyToOne
    @JoinColumn(name = "IDMODIF", nullable = false)
    private Modifications modification;

    @Column(name = "AVAILABLE")
    private String available;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "COLOUR")
    private String colour;

    @Column(name = "MILEAGE")
    private Double mileage;

    @Column(name = "BROKEN")
    private Integer broken;

    @Column(name = "CITY")
    private String city;

    @Column(name = "PLACE")
    private String place;

    @Column(name = "PHONE")
    private Long phone;

    @Column(name = "SWAP")
    private Integer swap;

    @Column(name = "PTS")
    private Integer pts;

    @Column(name = "OWNERS")
    private Integer owners;

    @Column(name = "GOSNUMBER")
    private String gosnumber;

    @Column(name = "BUYDAY")
    private Date buyday;

    @Column(name = "DAYOFWARRANTY")
    private Date dayofwarranty;

    @Column(name = "VIN")
    private String vin;

    @Column(name = "STS")
    private Long sts;

    @Column(name = "COMMENTS")
    private String commentns;

    @Column(name = "IMAGES")
    private String images;

    @OneToMany(mappedBy = "advertisment", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Comparisons> comparisons = new ArrayList<>();

    @OneToMany(mappedBy = "advertisment", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    public void setComparisons(List<Comparisons> comparisons) {
        if (comparisons != null) {
            if (!this.comparisons.isEmpty()) {
                this.comparisons.clear();
            }
            this.comparisons.addAll(comparisons);
            comparisons.forEach(x -> x.setAdvertisment(this));
        }
    }

    public void setLikes(List<Likes> likes) {
        if (likes != null) {
            if (!this.likes.isEmpty()) {
                this.likes.clear();
            }
            this.likes.addAll(likes);
            likes.forEach(x -> x.setAdvertisment(this));
        }
    }
}
