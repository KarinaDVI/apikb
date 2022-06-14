
package com.portfolio.apikb.services;

import com.portfolio.apikb.models.Experience;
import com.portfolio.apikb.repository.ExperienceRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {

    @Autowired
    ExperienceRepository expeRepo;
    
    public ArrayList<Experience>getAllExperience(){
        return (ArrayList<Experience>)expeRepo.findAll();
    }
    public Experience saveExperience(Experience experience){
        return expeRepo.save(experience);
    }
    public Optional<Experience> getExperienceByID(Long id){
        return expeRepo.findById(id);
    }
   //de apiHernan
    public Experience getOneExperienceByID(Long id) {
        return expeRepo.findById(id).get();
    }
    
    public boolean removeExperience(Long id){
        try{
            expeRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Experience findExperience(Long id){
        Experience expe = expeRepo.findById(id).orElse(null);
        return expe;
    }

    public Optional<Experience> getExperienceByPosition(String position) {
        return expeRepo.findByPosition(position);
    }

   
}

