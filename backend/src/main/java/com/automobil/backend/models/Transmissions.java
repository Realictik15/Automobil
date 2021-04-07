package com.automobil.backend.models;

import lombok.*;

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
@ToString
@Table(name = "TRANSMISSIONS")
public class Transmissions {
    @Id
    @Column(name = "IDTRANS")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSMISSION_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_TRANSMISSION_ID", allocationSize = 1, name = "TRANSMISSION_SEQ")
    private Long idTrans;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDGEAR", nullable = false)
    private GearBoxes gearbox;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "DRIVETYPE")
    private String driveType;

    @Column(name = "BRAKES")
    private String brakes;

    @Column(name = "SUSPENSION")
    private String suspension;

    @OneToMany(mappedBy = "transmissions", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Modifications> modifications = new ArrayList<>();

    public void setModifications(List<Modifications> modifications) {
        if (modifications != null) {
            if (!this.modifications.isEmpty()) {
                this.modifications.clear();
            }
            this.modifications.addAll(modifications);
            modifications.forEach(x -> x.setTransmissions(this));
        }
    }

}
