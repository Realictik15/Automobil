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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ENGINES")
public class Engines {
    @Id
    @Column(name = "IDENG")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENGINES_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_ENGINES_ID", allocationSize = 1, name = "ENGINES_SEQ")
    private Long ideng;

    @Column(name = "EVOLUME")
    private Integer volume;

    @Column(name = "EPOWER")
    private Integer power;

    @Column(name = "TYPEENG")
    private String typeEng;

    @Column(name = "TYPEFUEL")
    private String typeFuel;

    @Column(name = "FUELCONSUM")
    private Double fuelConsum;

    @Column(name = "ACCELIRATION")
    private Double acceliration;

    @Column(name = "EUROTYPE")
    private Integer euroType;

    @Column(name = "LOCATIONS")
    private String locations;

    @Column(name = "TORQUE")
    private Integer torque;

    @Column(name = "CYLINDERS")
    private Integer cylinders;

    @Column(name = "DISPOSCYL")
    private String disposCyl;

    @OneToMany(mappedBy = "engines",cascade = {CascadeType.MERGE,CascadeType.PERSIST}, orphanRemoval = true)
    private List<Modifications> modifications = new ArrayList<>();

    public void setModifications(List<Modifications> modifications) {
        if (modifications != null) {
            if (!this.modifications.isEmpty()) {
                this.modifications.clear();
            }
            this.modifications.addAll(modifications);
            modifications.forEach(x -> x.setEngines(this));
        }
    }
}
