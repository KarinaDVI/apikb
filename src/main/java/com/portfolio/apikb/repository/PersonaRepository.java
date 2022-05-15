
package com.portfolio.apikb.repository;

import com.portfolio.apikb.models.Persona;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    public abstract ArrayList<Persona> findByNombre(String nombre);
}
