
package com.portfolio.apikb.repository;

import com.portfolio.apikb.models.Skill;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

    public ArrayList<Skill> findByName(String name);
    
}
