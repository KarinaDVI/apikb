
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


@RestController
@RequestMapping("/apikb/person")
@CrossOrigin
public class PersonController {
    @Autowired
    IPersonaService ipersonaService;
    
    @GetMapping("/all")
    public List<Persona> getAllAbout(){
        return ipersonaService.getPersona();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public String savePersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "Persona creada correctamente";
    }
 
    //Metodo de apiHernan
    @GetMapping("/one/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable(value = "id") Long id) {
        Persona persona = ipersonaService.getOnePersonByID(id);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }
     @GetMapping("/getname/{nombre}")
    public Optional<Persona> getPersonaByNombre(@RequestParam("nombre") String nombre){
        return ipersonaService.getPersonaByNombre(nombre);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String removePersona(@PathVariable("id") Long id){
        if(ipersonaService.deletePersona(id)){
            return "About eliminado "+id+" correctamente";
        }else{
            return "No se pudo eliminar los datos del acercaDe";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Persona> updateSkill(@PathVariable("id") Long id, @RequestBody Persona personaRequest) {
        Persona persona =ipersonaService.getOnePersonByID(id);
        personaRequest.setId(persona.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(personaRequest, persona);
        return new ResponseEntity<>(ipersonaService.savePersona(persona), HttpStatus.OK);
    }
                                                             
}
