
package com.portfolio.apikb.services;

import com.portfolio.apikb.InterfaceService.IPersonaService;
import com.portfolio.apikb.models.Persona;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.apikb.repository.PersonaRepository;
import java.util.ArrayList;
import java.util.List;

        
@Service
public class PersonaService implements IPersonaService{
    @Autowired
    PersonaRepository persoRepo;
    
    @Override
    public ArrayList<Persona>getPersona(){
        return(ArrayList<Persona>)persoRepo.findAll();
    }
    
    @Override
    public Persona savePersona(Persona persona){
        return persoRepo.save(persona);
    }
    @Override
    public Optional<Persona> getPersonaByID(Long id){
        return persoRepo.findById(id);
    }
    @Override
    public Optional<Persona> getPersonaByNombre(String nombre){
        return persoRepo.findByNombre(nombre);
    }
    //de apiHernan
    @Override
    public Persona getOnePersonByID(Long id) {
        return persoRepo.findById(id).get();
    }
   
    @Override
    public boolean removePersona(Long id){
        try{
            persoRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
     @Override
    public boolean deletePersona(Long id) {
          try{
            persoRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public Persona findPersona(Long id){
        Persona perso = persoRepo.findById(id).orElse(null);
        return perso;
    }

   
}

