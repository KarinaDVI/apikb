
package com.portfolio.apikb.repository;

import com.portfolio.apikb.models.Project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

    public Optional<Project> findByName(String name);
    
}
