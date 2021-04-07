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
@Table(name = "CLASSES")
public class Classes {
    @Id
    @Column(name = "IDCLAS")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASSES_SEQ")
    @SequenceGenerator(sequenceName = "SEQUENCE_OF_CLAS_ID",allocationSize = 1,name = "CLASSES_SEQ")
    private Long idClas;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "INFO")
    private String info;

    @OneToMany(mappedBy = "classes",cascade = {CascadeType.MERGE,CascadeType.PERSIST}, orphanRemoval = true)
    private List<Models> models = new ArrayList<>();

    public void setModels(List<Models> models) {
        if (models != null) {
            if (!this.models.isEmpty()) {
                this.models.clear();
            }
            this.models.addAll(models);
            models.forEach(x -> x.setClasses(this));
        }
    }
}
