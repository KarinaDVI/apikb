
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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public String savePersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "Persona creada correctamente";
    }
    /*
    @GetMapping("/id/{id}")
    public Optional<Persona> getPersonaByID(@PathVariable("id") Long id){
        return ipersonaService.getPersonaByID(id);
    }
    */
    
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
    //MGB
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("/editm/{id}")
    public Persona updatePersona (@PathVariable Long id,
                    @RequestParam("nombre") String nuevoNombre,
                    @RequestParam("apellido") String nuevoApellido,
                    @RequestParam("edad") int nuevaEdad,
                    @RequestParam("fechaNac") String nuevaFechaNac,
                    @RequestParam("seniority") String nuevaSeniority,
                    @RequestParam("urlimage") String nuevaImage,
                    @RequestParam("company") String nuevaCompany,
                    @RequestParam("position") String nuevaPosition,
                    @RequestParam("abouts") String nuevoAbouts){
        
        Persona persona = ipersonaService.findPersona(id);
        
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setEdad(nuevaEdad);
        persona.setFechaNac(nuevaFechaNac);
        persona.setSeniority(nuevaSeniority);
        persona.setUrlimage(nuevaImage);
        persona.setCompany(nuevaCompany);
        persona.setPosition(nuevaPosition);
        persona.setAbouts(nuevoAbouts);
        
        ipersonaService.savePersona(persona);
        return persona;
    }
    */
    
    //Profe sincrónico:
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edith/{id}")
    public Persona updatePerson(@PathVariable("id") Long id, @RequestBody Persona personaTochange) {

        Persona p = ipersonaService.findPersona(id);
        personaTochange.setId(p.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(personaTochange, p);
        return ipersonaService.savePersona(p);
    }
    */
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Persona> updateSkill(@PathVariable("id") Long id, @RequestBody Persona personaRequest) {
        Persona persona =ipersonaService.getOnePersonByID(id);
        // .orElseThrow(() -> new ResourceNotFoundException("EducationId " + id + "not found"));
        //skill.setPerson(skillRequest.getPerson());
        personaRequest.setId(persona.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(personaRequest, persona);
        return new ResponseEntity<>(ipersonaService.savePersona(persona), HttpStatus.OK);
    }
    
    //Del crud de producto
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editp/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id, @RequestBody Persona personaRequest){
       
        Persona persona = ipersonaService.getPersonaByID(id).get();
        persona.setNombre(personaRequest.getNombre());
        persona.setNombre(personaRequest.getNombre());
        persona.setEdad(personaRequest.getEdad());
        persona.setFechaNac(personaRequest.getFechaNac());
        persona.setSeniority(personaRequest.getSeniority());
        persona.setUrlimage(personaRequest.getUrlimage());
        persona.setCompany(personaRequest.getCompany());
        persona.setPosition(personaRequest.getPosition());
        persona.setAbouts(personaRequest.getAbouts());
        return new ResponseEntity(("persona actualizada"), HttpStatus.OK);
    }
*/
                              
                              
}
