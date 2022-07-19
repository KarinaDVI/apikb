
package com.portfolio.apikb.Controller;

import com.portfolio.apikb.models.Education;
import com.portfolio.apikb.services.EducationService;
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
@RequestMapping("/apikb/education")
public class EducationController {
    @Autowired
    EducationService educationService;
    
    @GetMapping("/all")
    public ArrayList<Education> getAllEducation(){
        return educationService.getAllEducation();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public Education saveEducation(@RequestBody Education education){
        return educationService.saveEducation(education);
    }
    @GetMapping("/id/{id}")
    public Optional<Education> getEducationByID(@PathVariable("id") Long id){
        return educationService.getEducationByID(id);
    }
    @GetMapping("/one/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable(value = "id") Long id) {
        Education education = educationService.getOneEducationByID(id);
        return new ResponseEntity<>(education, HttpStatus.OK);
    }
     @GetMapping("/getname/{title}")
    public Optional<Education> getEducationByName(@RequestParam("title") String title){
        return educationService.getEducationByTitle(title);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String removeExperience(@PathVariable("id") Long id){
        if(educationService.removeEducation(id)){
            return "Skill eliminado "+id+" correctamente";
        }else{
            return "No se pudo eliminar los datos del skill";
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable("id") Long id, @RequestBody Education educationRequest) {
        Education education =educationService.getOneEducationByID(id);
        // .orElseThrow(() -> new ResourceNotFoundException("EducationId " + id + "not found"));
        //skill.setPerson(skillRequest.getPerson());
        educationRequest.setId(education.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(educationRequest, education);
        return new ResponseEntity<>(educationService.saveEducation(education), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editMM/{id}")
    public Education editarEducation(@PathVariable("id") Long id, @RequestBody Education educationTochange) {

        Education ed = educationService.findEducation(id);
        System.out.println(ed);
        educationTochange.setId(ed.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(educationTochange, ed);
        return educationService.saveEducation(ed);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("update/{id}")
    public Education updateEducation (@PathVariable Long id,
                    @RequestParam("school") String school,
                    @RequestParam("title") String title,
                    @RequestParam("starts") int starts,
                    @RequestParam("ends") int ends,
                    @RequestParam("urlimg") String urlimg
                    ){
        
        Education education = educationService.findEducation(id);
        
        education.setSchool(school);
        education.setTitle(title);
        education.setStarts(starts);
        education.setEnds(ends);
        education.setUrlimg(urlimg);
        
        educationService.saveEducation(education);
        return education;
    }
}
