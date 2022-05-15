
package com.portfolio.apikb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


   
@Getter @Setter
@Entity
@Table(name="education")

public class Education {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String school;
    private String title;
    private int starts;
    private int ends;
    private String img;
}
