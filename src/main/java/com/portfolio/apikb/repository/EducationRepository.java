
package com.portfolio.apikb.repository;

import com.portfolio.apikb.models.Education;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long>{
    public Optional<Education> findByTitle(String title);
}
