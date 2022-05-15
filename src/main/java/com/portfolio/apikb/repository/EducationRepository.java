
package com.portfolio.apikb.repository;

import com.portfolio.apikb.models.Education;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long>{
    public ArrayList<Education> findByTitle(String title);
}
