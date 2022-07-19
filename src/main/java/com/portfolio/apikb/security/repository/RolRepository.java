
package com.portfolio.apikb.security.repository;

import com.portfolio.apikb.security.entity.Rol;
import com.portfolio.apikb.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    
}
