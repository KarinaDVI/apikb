
package com.portfolio.apikb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="experience")

public class Experience {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String position;
    private String company;
    private int starts;
    private int ends;
    @Column(name="urlimg",length=2048)
    private String urlimg;
    private String mode;
    
}
