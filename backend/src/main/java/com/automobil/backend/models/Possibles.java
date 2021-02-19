package com.automobil.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POSSIBLES")
public class Possibles {
    @Id
    @Column(name = "IDPOSSIBLE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSSIBLE_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_POSSIBLE_ID", allocationSize = 1, name = "POSSIBLE_SEQ")
    private Long idPossible;

    @ManyToOne
    @JoinColumn(name = "IDMODIF", nullable = false)
    private Modifications modification;

    @ManyToOne
    @JoinColumn(name = "IDCOMPL", nullable = false)
    private Completesets completesets;
}
