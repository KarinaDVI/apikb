
package com.portfolio.apikb.Controller;

import com.portfolio.apikb.models.Experience;
import com.portfolio.apikb.services.ExperienceService;
import java.util.ArrayList;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public Experience saveExperience(@RequestBody Experience experience){
        return experienceService.saveExperience(experience);
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable(value = "id") Long id) {
        Experience experience = experienceService.getOneExperienceByID(id);
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }
    @GetMapping("/getname/{position}")
    public Optional<Experience> getExperienceByPosition(@RequestParam("position") String position){
        return experienceService.getExperienceByPosition(position);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String removeExperience(@PathVariable("id") Long id){
        if(experienceService.removeExperience(id)){
            return "Skill eliminado "+id+" correctamente";
        }else{
            return "No se pudo eliminar los datos del skill";
        }
    }
    
     //Usar: Hernan nuevo
   @PreAuthorize("hasRole('ADMIN')")
   @PutMapping("/edit/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable("id") Long id, @RequestBody Experience experienceRequest) {
        Experience experience =experienceService.getOneExperienceByID(id);
        experienceRequest.setId(experience.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(experienceRequest, experience);

        return new ResponseEntity<>(experienceService.saveExperience(experience), HttpStatus.OK);
    }
    
                        
                              
}
