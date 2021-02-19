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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PARTS")
public class Parts {
    @Id
    @Column(name = "IDPART")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PART_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_PART_ID", allocationSize = 1, name = "PART_SEQ")
    private Long idPart;

    @Column(name = "TITLE")
    private String title;

    @OneToMany(mappedBy = "part", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Clients> clients = new ArrayList<>();

    public void setClients(List<Clients> clients) {
        if (clients != null) {
            if (!this.clients.isEmpty()) {
                this.clients.clear();
            }
            this.clients.addAll(clients);
            clients.forEach(x -> x.setPart(this));
        }
    }

}
