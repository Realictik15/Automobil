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
@Table(name = "GEARBOXES")
public class GearBoxes {
    @Id
    @Column(name = "IDGEAR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEARBOXES_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_GEARBOXES_ID", allocationSize = 1, name = "GEARBOXES_SEQ")
    private Long idGear;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "INFO")
    private String info;

    @OneToMany(mappedBy = "gearbox", cascade = {CascadeType.MERGE,CascadeType.PERSIST}, orphanRemoval = true)
    private List<Transmissions> transmissions = new ArrayList<>();

    public void setTransmissions(List<Transmissions> transmissions) {
        if (transmissions != null) {
            if (!this.transmissions.isEmpty()) {
                this.transmissions.clear();
            }
            this.transmissions.addAll(transmissions);
            transmissions.forEach(x -> x.setGearbox(this));
        }
    }
}

