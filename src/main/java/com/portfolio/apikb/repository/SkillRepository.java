
package com.portfolio.apikb.repository;

import com.portfolio.apikb.models.Skill;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

    public Optional<Skill> findByName(String name);
    @Override
    public Optional<Skill> findById(Long id);
}
