
package com.portfolio.apikb.repository;

import com.portfolio.apikb.models.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    public Optional<Persona> findByNombre(String nombre);
}
