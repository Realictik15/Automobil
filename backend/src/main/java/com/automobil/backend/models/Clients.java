package com.automobil.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
переделать поля part сделать enum
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENTS")
public class Clients {
    @Id
    @Column(name = "IDUSER")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTS_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_CLIENTS_ID", allocationSize = 1, name = "CLIENTS_SEQ")
    private Long idUser;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IDPART")
    private Parts part;

    @Column(name = "FNAME")
    private String firstName;

    @Column(name = "SNAME")
    private String lastName;

    @Column(name = "BORNDAY")
    private Date bornDay;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASS")
    private String pass;

    @Column(name = "EMAIL")
    private String emale;

    @Column(name = "TELEPHONE")
    private Long telephone;

    @Column(name = "DRIVEEXP")
    private Integer driveExp;

    @Column(name = "MONEY")
    private Double money;

    @OneToMany(mappedBy = "clients", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Reviews> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "clients", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Advertisments> advertisments = new ArrayList<>();

    @OneToMany(mappedBy = "clients", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Messages> messages = new ArrayList<>();

    @OneToMany(mappedBy = "clients", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Comparisons> comparisons = new ArrayList<>();

    @OneToMany(mappedBy = "clients", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    public void setComparisons(List<Comparisons> comparisons) {
        if (comparisons != null) {
            if (!this.comparisons.isEmpty()) {
                this.comparisons.clear();
            }
            this.comparisons.addAll(comparisons);
            comparisons.forEach(x -> x.setClients(this));
        }
    }

    public void setLikes(List<Likes> likes) {
        if (likes != null) {
            if (!this.likes.isEmpty()) {
                this.likes.clear();
            }
            this.likes.addAll(likes);
            likes.forEach(x -> x.setClients(this));
        }
    }

    public void setMessages(List<Messages> messages) {
        if (messages != null) {
            if (!this.messages.isEmpty()) {
                this.messages.clear();
            }
            this.messages.addAll(messages);
            //  messages.forEach(x -> x.setClients(this));
            for (Messages message : messages) {
                message.setClients(this);
            }
        }
    }

    public void setReviews(List<Reviews> reviews) {
        if (reviews != null) {
            if (!this.reviews.isEmpty()) {
                this.reviews.clear();
            }
            this.reviews.addAll(reviews);
            reviews.forEach(x -> x.setClients(this));
        }
    }

    public void setAdvertisments(List<Advertisments> advertisments) {
        if (advertisments != null) {
            if (!this.advertisments.isEmpty()) {
                this.advertisments.clear();
            }
            this.advertisments.addAll(advertisments);
            advertisments.forEach(x -> x.setClients(this));
        }
    }

}
