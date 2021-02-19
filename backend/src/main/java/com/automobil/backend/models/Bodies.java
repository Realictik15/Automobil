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
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "IDCARBODY", nullable = false)
    private Carbody carbody;

    @ManyToOne
    @JoinColumn(name = "IDGEN", nullable = false)
    private Generations generations;

    @OneToMany(mappedBy = "bodies", cascade = CascadeType.ALL)
    private List<Sizess> sizessList = new ArrayList<>();

    public void setSizessList(List<Sizess> sizessList) {
        if (sizessList != null) {
            if (!this.sizessList.isEmpty()) {
                this.sizessList.clear();
            }
            this.sizessList.addAll(sizessList);
            sizessList.forEach(x -> x.setBodies(this));
        }
    }
}
