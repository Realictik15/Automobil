package com.automobil.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMPARISONS")
public class Comparisons {
    @Id
    @Column(name = "IDCOMPAR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPAR_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_COMPAR_ID", allocationSize = 1, name = "COMPAR_SEQ")
    private Long idCompar;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDADVERT", nullable = false)
    private Advertisments advertisment;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDUSER", nullable = false)
    private Clients clients;
}
