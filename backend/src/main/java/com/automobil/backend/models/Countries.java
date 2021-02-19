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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "COUNTRIES")
public class Countries {
    @Id
    @Column(name = "IDCOUNRTY")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNTRY_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_COUNTRY_ID", allocationSize = 1, name = "COUNTRY_SEQ")
    private Long idCountry;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "INFO")
    private String info;


    @OneToMany(mappedBy = "countries", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Marks> marks = new ArrayList<>();

    public void setMarks(List<Marks> marks) {
        if (marks != null) {
            if (!this.marks.isEmpty()) {
                this.marks.clear();
            }
            this.marks.addAll(marks);
            marks.forEach(x -> x.setCountries(this));
        }

    }
}
