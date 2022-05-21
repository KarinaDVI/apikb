
package com.portfolio.apikb.Controller;

import com.portfolio.apikb.models.Skill;
import com.portfolio.apikb.services.SkillService;
import java.util.ArrayList;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
@RequestMapping("/apikb/skill")
public class SkillController {
    @Autowired
    SkillService skillService;
    
    @GetMapping("/all")
    public ArrayList<Skill> getAllSkills(){
        return skillService.getAllSkills();
    }
    @PostMapping()
    public Skill saveSkill(@RequestBody Skill skill){
        return skillService.saveSkill(skill);
    }
    @GetMapping("/{id}")
    public Optional<Skill> getSkillByID(@PathVariable("id") Long id){
        return skillService.getSkillByID(id);
    }
     @GetMapping("/{nombre}")
    public ArrayList<Skill> getSkillByName(@RequestParam("nombre") String name){
        return skillService.getSkillByName(name);
    }
    @DeleteMapping("/{id}")
    public String removeSkill(@PathVariable("id") Long id){
        if(skillService.removeSkill(id)){
            return "Skill eliminado "+id+" correctamente";
        }else{
            return "No se pudo eliminar los datos del skill";
        }
    }
    @PutMapping ("/update/{id}")
    public Skill updateSkill (@PathVariable Long id,
                    @RequestParam("nombre") String nuevoNombre,
                    @RequestParam("progress") int progress,
                    @RequestParam("confirms") int confirms,
                    @RequestParam("confirmsNames") String confirmsNames,
                    @RequestParam("outerColor") String outerStrokeColor,
                    @RequestParam("innerColor") String innerStrokeColor
                    ){
        
        Skill skill = skillService.findSkill(id);
        
        skill.setName(nuevoNombre);
        skill.setProgress(progress);
        skill.setConfirms(confirms);
        skill.setConfirmsNames(confirmsNames);
        skill.setOuterStrokeColor(outerStrokeColor);
        skill.setInnerStrokeColor(innerStrokeColor); 
        
        skillService.saveSkill(skill);
        return skill;
    }
    
    @PutMapping("/edit/{id}")
    public Skill cambiarSkill(@PathVariable("id") Long id, @RequestBody Skill skillTochange) {

        Skill s = skillService.findSkill(id);
        System.out.println(s);
        skillTochange.setId(s.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(skillTochange, s);
        return skillService.saveSkill(s);
    }
    
                         
                              
}
