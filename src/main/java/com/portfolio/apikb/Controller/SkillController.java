
package com.portfolio.apikb.Controller;

import com.portfolio.apikb.models.Skill;
import com.portfolio.apikb.services.SkillService;
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
@RequestMapping("/apikb/skill")
public class SkillController {
    @Autowired
    SkillService skillService;
    
    @GetMapping("/all")
    public ArrayList<Skill> getAllSkills(){
        return skillService.getAllSkills();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public Skill saveSkill(@RequestBody Skill skill){
        return skillService.saveSkill(skill);
    }
    
    //Metodo de apikb
    /*
    @GetMapping("/id/{id}")
    public Optional<Skill> getSkillByID(@PathVariable("id") Long id){
        return skillService.getSkillByID(id);
    }
    */
    
    //Metodo de apiHernan
    @GetMapping("/one/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable(value = "id") Long id) {
        Skill skill = skillService.getOneSkillByID(id);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }
    
     @GetMapping("/getname/{nombre}")
    public Optional<Skill> getSkillByName(@RequestParam("nombre") String name){
        return skillService.getSkillByName(name);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String removeSkill(@PathVariable("id") Long id){
        if(skillService.removeSkill(id)){
            return "Skill eliminado "+id+" correctamente";
        }else{
            return "No se pudo eliminar los datos del skill";
        }
    }
    //MBG
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("/editm/{id}")
    public Skill updateSkill (@PathVariable Long id,
                    @RequestParam("nombre") String nuevoNombre,
                    @RequestParam("progress") int progress,
                    @RequestParam("confirmsNames") String confirmsNames,
                    @RequestParam("outerColor") String outerStrokeColor,
                    @RequestParam("innerColor") String innerStrokeColor,
                    @RequestParam("imageSrc") String imageSrc
                    ){
        
        Skill skill = skillService.findSkill(id);
        
        skill.setName(nuevoNombre);
        skill.setProgress(progress);
        skill.setConfirmsNames(confirmsNames);
        skill.setOuterStrokeColor(outerStrokeColor);
        skill.setInnerStrokeColor(innerStrokeColor); 
        skill.setImageSrc(imageSrc); 
        
        skillService.saveSkill(skill);
        return skill;
    }
*/
    
    //Hernan nuevo
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edith/{id}")
    public Skill cambiarSkill(@PathVariable("id") Long id, @RequestBody Skill skillTochange) {

        Skill s = skillService.findSkill(id);
        System.out.println(s);
        skillTochange.setId(s.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(skillTochange, s);
        return skillService.saveSkill(s);
    }
*/
    
    //Usar: Hernan nuevo
   @PreAuthorize("hasRole('ADMIN')")
   @PutMapping("/edit/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable("id") Long id, @RequestBody Skill skillRequest) {
        Skill skill =skillService.getOneSkillByID(id);
        // .orElseThrow(() -> new ResourceNotFoundException("EducationId " + id + "not found"));
        //skill.setPerson(skillRequest.getPerson());
        skillRequest.setId(skill.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(skillRequest, skill);

        return new ResponseEntity<>(skillService.saveSkill(skill), HttpStatus.OK);
    }
    
    //Refrito del crud de productos
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit2/{id}")
    public ResponseEntity<?> editSkill(@PathVariable("id") Long id, @RequestBody Skill skillRequest) {
        
        Skill skill =skillService.getSkillByID(id).get();
        skill.setName(skillRequest.getName());
        skill.setProgress(skillRequest.getProgress());
        skill.setConfirmsNames(skillRequest.getConfirmsNames());
        skill.setInnerStrokeColor(skillRequest.getInnerStrokeColor());
        skill.setOuterStrokeColor(skillRequest.getOuterStrokeColor());
        skill.setImageSrc(skillRequest.getImageSrc());
        
        skillService.saveSkill(skill);

        return new ResponseEntity<>("Skill actualizado", HttpStatus.OK);
    }
*/
                              
}
