
package com.portfolio.apikb.Controller;

import com.portfolio.apikb.InterfaceService.IPersonaService;
import com.portfolio.apikb.dto.PersonDto;
import com.portfolio.apikb.models.Persona;
import java.util.List;
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

public class PersonController {
    @Autowired
    IPersonaService ipersonaService;
    
    @GetMapping("/apikb/person/all")
    public List<Persona> getAllAbout(){
        return ipersonaService.getPersona();
    }
    @PostMapping("/apikb/person/")
    public String savePersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "Persona creada correctamente";
    }
    @GetMapping("/apikb/person/{id}")
    public Optional<Persona> getPersonaByID(@PathVariable("id") Long id){
        return ipersonaService.getPersonaByID(id);
    }
     @GetMapping("/apikb/person/{nombre}")
    public List<Persona> getPersonaByNombre(@RequestParam("nombre") String nombre){
        return ipersonaService.getPersonaByNombre(nombre);
    }
    @DeleteMapping("/apikb/person/{id}")
    public String removePersona(@PathVariable("id") Long id){
        if(ipersonaService.deletePersona(id)){
            return "About eliminado "+id+" correctamente";
        }else{
            return "No se pudo eliminar los datos del acercaDe";
        }
    }
    @PutMapping ("/apikb/person/update/{id}")
    public Persona updatePersona (@PathVariable Long id,
                    @RequestParam("nombre") String nuevoNombre,
                    @RequestParam("apellido") String nuevoApellido,
                    @RequestParam("edad") int nuevaEdad,
                    @RequestParam("seniority") String nuevaSeniority,
                    @RequestParam("urlimage") String nuevaImage,
                    @RequestParam("company") String nuevaCompany,
                    @RequestParam("position") String nuevaPosition,
                    @RequestParam("abouts") String nuevoAbouts){
        
        Persona persona = ipersonaService.findPersona(id);
        
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setEdad(nuevaEdad);
        persona.setSeniority(nuevaSeniority);
        persona.setUrlimage(nuevaImage);
        persona.setCompany(nuevaCompany);
        persona.setPosition(nuevaPosition);
        persona.setAbouts(nuevoAbouts);
        
        ipersonaService.savePersona(persona);
        return persona;
    }
    
    @PutMapping ("/apikb/person/edit/{id}")
    public Persona editAbout (@PathVariable("id") Long id, 
                        @RequestBody PersonDto personDTO){
        
        Persona aboutMe=ipersonaService.findPersona(id);
        
        aboutMe.setNombre(personDTO.getNombre());
        aboutMe.setEdad(personDTO.getEdad());
        aboutMe.setSeniority(personDTO.getSeniority());
        aboutMe.setUrlimage(personDTO.getUrlimage());
        aboutMe.setCompany(personDTO.getCompany());
        aboutMe.setPosition(personDTO.getPosition());
        aboutMe.setAbouts(personDTO.getAbouts());

        ipersonaService.savePersona(aboutMe);
        return aboutMe;
    }
                              
                              
}
