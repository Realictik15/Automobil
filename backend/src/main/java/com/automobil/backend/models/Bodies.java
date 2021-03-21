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
@Table(name = "BODIES")
public class Bodies {
    @Id
    @Column(name = "IDBODYGEN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BODIES_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_BODIES_ID", allocationSize = 1, name = "BODIES_SEQ")
    private Long idBodyGen;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDCARBODY", nullable = false)
    private Carbody carbody;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDGEN", nullable = false)
    private Generations generations;

    @OneToOne(mappedBy = "bodies")
    private Sizess sizess;


}
