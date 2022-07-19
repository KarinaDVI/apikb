
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
@Table(name="persona")
public class Persona {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String apellido;
    private int edad;
    private String fechaNac;
    private String seniority;
    @Column(name="urlimage",length=2048)
    private String urlimage;
    private String company;
    private String position;
    private String abouts;
    
}
