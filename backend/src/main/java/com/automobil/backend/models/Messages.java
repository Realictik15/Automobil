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

    @ManyToOne
    @JoinColumn(name = "IDREVI",nullable = false)
    private Reviews reviews;

    @ManyToOne
    @JoinColumn(name = "IDUSER", nullable = false)
    private Clients clients;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "DATESEND")
    private Date dateSend;

}
