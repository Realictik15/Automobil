package com.automobil.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REVIEWS")
public class Reviews {
    @Id
    @Column(name = "IDREVI")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEWS_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_REVIEWS_ID", allocationSize = 1, name = "REVIEWS_SEQ")
    private Long idRevi;

    @ManyToOne
    @JoinColumn(name = "IDUSER", nullable = false)
    private Clients clients;

    @ManyToOne
    @JoinColumn(name = "IDMARK", nullable = false)
    private Marks mark;

    @ManyToOne
    @JoinColumn(name = "IDMODEL", nullable = false)
    private Models model;

    @ManyToOne
    @JoinColumn(name = "IDGEN", nullable = false)
    private Generations generations;

    @ManyToOne
    @JoinColumn(name = "IDMODIF", nullable = false)
    private Modifications modification;

    @Column(name = "DATEREL")
    private Date daterel;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "PLUS")
    private String plus;

    @Column(name = "MINS")
    private String mins;

    @Column(name = "RAITING")
    private Double raiting;

    @Column(name = "LIKES")
    private Integer likes;

    @Column(name = "VIEWS")
    private Integer views;

    @Column(name = "IMAGES")
    private String images;

    @OneToMany(mappedBy = "reviews", cascade = {CascadeType.MERGE,CascadeType.PERSIST}, orphanRemoval = true)
    private List<Messages> messages = new ArrayList<>();

    public void setMessages(List<Messages> messages) {
        if (messages != null) {
            if (!this.messages.isEmpty()) {
                this.messages.clear();
            }
            this.messages.addAll(messages);
            messages.forEach(x -> x.setReviews(this));
        }
    }
}
