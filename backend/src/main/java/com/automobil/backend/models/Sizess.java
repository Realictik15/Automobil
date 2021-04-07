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
@Table(name = "SIZESS")
public class Sizess {
    @Id
    @Column(name = "IDSIZ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIZESS_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_SIZESS_ID", allocationSize = 1, name = "SIZESS_SEQ")
    private Long idSiz;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDBODYGEN")
    private Bodies bodies;

    @Column(name = "LENGHT")
    private Integer lenght;

    @Column(name = "WIDHT")
    private Integer widht;

    @Column(name = "HIGHT")
    private Integer hight;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "WHEELBASE")
    private Integer wheelbase;

    @Column(name = "CLEARANCE")
    private Integer clearance;

    @Column(name = "WHEELSSIZE")
    private Integer wheelsSize;

    @Column(name = "TRUNKWIGHT")
    private Integer trunkWight;
}
