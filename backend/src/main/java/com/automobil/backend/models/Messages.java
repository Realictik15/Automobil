package com.automobil.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MESSAGES")
public class Messages {
    @Id
    @Column(name = "IDMESS")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_MESSAGE_ID", allocationSize = 1, name = "MESSAGE_SEQ")
    private Long idMess;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDREVI",nullable = false)
    private Reviews reviews;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDUSER", nullable = false)
    private Clients clients;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "DATESEND")
    private Date dateSend;

}
