
package com.portfolio.apikb.Controller;

import com.portfolio.apikb.InterfaceService.IPersonaService;
import com.portfolio.apikb.models.Persona;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/apikb/person")
public class PersonController {
    @Autowired
    IPersonaService ipersonaService;
    
    @GetMapping("/all")
    public List<Persona> getAllAbout(){
        return ipersonaService.getPersona();
    }
    @PostMapping()
    public String savePersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "Persona creada correctamente";
    }
    @GetMapping("/{id}")
    public Optional<Persona> getPersonaByID(@PathVariable("id") Long id){
        return ipersonaService.getPersonaByID(id);
    }
     @GetMapping("/{nombre}")
    public List<Persona> getPersonaByNombre(@RequestParam("nombre") String nombre){
        return ipersonaService.getPersonaByNombre(nombre);
    }
    @DeleteMapping("/{id}")
    public String removePersona(@PathVariable("id") Long id){
        if(ipersonaService.deletePersona(id)){
            return "About eliminado "+id+" correctamente";
        }else{
            return "No se pudo eliminar los datos del acercaDe";
        }
    }
    @PutMapping ("/update/{id}")
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
    
    //Profe sincrónico:
    @PutMapping("/edit/{id}")
    public Persona updatePerson(@PathVariable("id") Long id, @RequestBody Persona personaTochange) {

        Persona p = ipersonaService.findPersona(id);
        personaTochange.setId(p.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(personaTochange, p);
        return ipersonaService.savePersona(p);
    }
    
    @PutMapping("/modify/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id, @RequestBody Persona personaRequest){
       
        Persona persona = ipersonaService.getPersonaByID(id).get();
        persona.setNombre(personaRequest.getNombre());
        
        persona.setNombre(personaRequest.getNombre());
        persona.setEdad(personaRequest.getEdad());
        persona.setSeniority(personaRequest.getSeniority());
        persona.setUrlimage(personaRequest.getUrlimage());
        persona.setCompany(personaRequest.getCompany());
        persona.setPosition(personaRequest.getPosition());
        persona.setAbouts(personaRequest.getAbouts());
        
        return new ResponseEntity(("persona actualizada"), HttpStatus.OK);
    }
                              
                              
}
