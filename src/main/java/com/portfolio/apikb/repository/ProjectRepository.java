
package com.portfolio.apikb.repository;

import com.portfolio.apikb.models.Project;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

    public ArrayList<Project> findByName(String name);
    
}
