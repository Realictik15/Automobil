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
@Table(name = "CARBODY")
public class Carbody {
    @Id
    @Column(name = "IDCARBODY")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARBODY_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_CARBODY_ID", allocationSize = 1, name = "CARBODY_SEQ")
    private Long idCarBody;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "INFO")
    private String info;

    @OneToMany(mappedBy = "carbody", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Bodies> bodiesList = new ArrayList<>();

    @OneToMany(mappedBy = "carbody", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Advertisments> advertisments = new ArrayList<>();

    public void setsAdvertismentsList(List<Advertisments> advertisments) {
        if (advertisments != null) {
            if (!this.advertisments.isEmpty()) {
                this.advertisments.clear();
            }
            this.advertisments.addAll(advertisments);
            advertisments.forEach(x -> x.setCarbody(this));
        }
    }

    public void setBodiesList(List<Bodies> bodiesList) {
        if (bodiesList != null) {
            if (!this.bodiesList.isEmpty()) {
                this.bodiesList.clear();
            }
            this.bodiesList.addAll(bodiesList);
            bodiesList.forEach(x -> x.setCarbody(this));
        }
    }
}

