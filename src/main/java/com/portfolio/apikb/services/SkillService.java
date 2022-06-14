
package com.portfolio.apikb.services;
import com.portfolio.apikb.models.Skill;
import com.portfolio.apikb.repository.SkillRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
  
    @Autowired
    SkillRepository skillRepo;
    
    public ArrayList<Skill>getAllSkills(){
        return (ArrayList<Skill>)skillRepo.findAll();
    }
    public Skill saveSkill(Skill skill){
        return skillRepo.save(skill);
    }
    
    //Repo de apikb
    public Optional<Skill> getSkillByID(Long id){
        return skillRepo.findById(id);
    }
    
    
    //de apiHernan
    public Skill getOneSkillByID(Long id) {
        return skillRepo.findById(id).get();
    }
    
    public Optional<Skill> getSkillByName(String name){
        return skillRepo.findByName(name);
    }
    
    public boolean removeSkill(Long id){
        try{
            skillRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Skill findSkill(Long id){
        Skill skill = skillRepo.findById(id).orElse(null);
        return skill;
    }

 
}
 

