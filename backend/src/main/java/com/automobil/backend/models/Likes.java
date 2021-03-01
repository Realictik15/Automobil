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
@Table(name = "LIKES")
public class Likes {
    @Id
    @Column(name = "IDLIKE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKE_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_LIKE_ID", allocationSize = 1, name = "LIKE_SEQ")
    private Long idLike;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDADVERT", nullable = false)
    private Advertisments advertisment;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDUSER", nullable = false)
    private Clients clients;
}

