
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
@Table(name="skill")

public class Skill {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int progress;
    private String confirmsNames;
    private String outerStrokeColor;
    private String innerStrokeColor;
    @Column(name="imageSrc",length=2048)
    private String imageSrc;
    
    
}
