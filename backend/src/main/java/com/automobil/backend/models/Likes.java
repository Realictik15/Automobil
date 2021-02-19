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
@Table(name = "LIKES")
public class Likes {
    @Id
    @Column(name = "IDLIKE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKE_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_LIKE_ID", allocationSize = 1, name = "LIKE_SEQ")
    private Long idLike;

    @ManyToOne
    @JoinColumn(name = "IDADVERT", nullable = false)
    private Advertisments advertisment;

    @ManyToOne
    @JoinColumn(name = "IDUSER", nullable = false)
    private Clients clients;
}

