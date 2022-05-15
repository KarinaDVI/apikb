
package com.portfolio.apikb.Controller;

import com.portfolio.apikb.models.Education;
import com.portfolio.apikb.services.EducationService;
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
@RequestMapping("/apikb/education")
public class EducationController {
    @Autowired
    EducationService educationService;
    
    @GetMapping("/all")
    public ArrayList<Education> getAllEducation(){
        return educationService.getAllEducation();
    }
    @PostMapping()
    public Education saveExperience(@RequestBody Education education){
        return educationService.saveEducation(education);
    }
    @GetMapping("/{id}")
    public Optional<Education> getEducationByID(@PathVariable("id") Long id){
        return educationService.getEducationByID(id);
    }

    @DeleteMapping("/{id}")
    public String removeExperience(@PathVariable("id") Long id){
        if(educationService.removeEducation(id)){
            return "Skill eliminado "+id+" correctamente";
        }else{
            return "No se pudo eliminar los datos del skill";
        }
    }
    @PutMapping ("update/{id}")
    public Education updateEducation (@PathVariable Long id,
                    @RequestParam("school") String school,
                    @RequestParam("title") String title,
                    @RequestParam("starts") int starts,
                    @RequestParam("ends") int ends,
                    @RequestParam("img") String img
                    ){
        
        Education education = educationService.findEducation(id);
        
        education.setSchool(school);
        education.setTitle(title);
        education.setStarts(starts);
        education.setEnds(ends);
        education.setImg(img);
        
        educationService.saveEducation(education);
        return education;
    }
}
