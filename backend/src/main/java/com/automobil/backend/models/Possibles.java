package com.automobil.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDMODIF", nullable = false)
    private Modifications modification;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDCOMPL", nullable = false)
    private CompleteSets completesets;
}
