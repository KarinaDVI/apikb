
package com.portfolio.apikb.Controller;

import com.portfolio.apikb.models.Experience;
import com.portfolio.apikb.services.ExperienceService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apikb/experience")
public class ExperienceController {
    @Autowired
    ExperienceService experienceService;
    
    @GetMapping("/all")
    public ArrayList<Experience> getAllExperience(){
        return experienceService.getAllExperience();
    }
    @PostMapping()
    public Experience saveExperience(@RequestBody Experience experience){
        return experienceService.saveExperience(experience);
    }
    @GetMapping("/{id}")
    public Optional<Experience> getExperienceByID(@PathVariable("id") Long id){
        return experienceService.getExperienceByID(id);
    }

    @DeleteMapping("/{id}")
    public String removeExperience(@PathVariable("id") Long id){
        if(experienceService.removeExperience(id)){
            return "Skill eliminado "+id+" correctamente";
        }else{
            return "No se pudo eliminar los datos del skill";
        }
    }
    @PutMapping ("update/{id}")
    public Experience updateExperience (@PathVariable Long id,
                    @RequestParam("position") String position,
                    @RequestParam("company") String company,
                    @RequestParam("img") String img,
                    @RequestParam("start") int starts,
                    @RequestParam("ends") int ends
                    ){
        
        Experience experience = experienceService.findExperience(id);
        
        experience.setPosition(position);
        experience.setCompany(company);
        experience.setImg(img);
        experience.setStarts(starts);
        experience.setEnds(ends);
        experienceService.saveExperience(experience);
        return experience;
    }
    
                        
                              
}
