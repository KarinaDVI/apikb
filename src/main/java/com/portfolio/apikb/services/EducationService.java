
package com.portfolio.apikb.services;

import com.portfolio.apikb.models.Education;
import com.portfolio.apikb.repository.EducationRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EducationService {

    @Autowired
    EducationRepository eduRepo;
    
    public ArrayList<Education>getAllEducation(){
        return (ArrayList<Education>)eduRepo.findAll();
    }
    public Education saveEducation(Education education){
        return eduRepo.save(education);
    }
    public Optional<Education> getEducationByID(Long id){
        return eduRepo.findById(id);
    }
    public Education getOneEducationByID(Long id) {
        return eduRepo.findById(id).get();
    }
    public Optional<Education> getEducationByTitle(String title){
        return eduRepo.findByTitle(title);
    }
    public boolean removeEducation(Long id){
        try{
            eduRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Education findEducation(Long id){
        Education edu = eduRepo.findById(id).orElse(null);
        return edu;
    }
}
