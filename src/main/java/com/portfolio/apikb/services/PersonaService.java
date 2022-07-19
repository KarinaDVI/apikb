
package com.portfolio.apikb.services;

import com.portfolio.apikb.InterfaceService.IPersonaService;
import com.portfolio.apikb.models.Persona;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.apikb.repository.PersonaRepository;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

        
@Service
public class PersonaService implements IPersonaService{
    @Autowired
    PersonaRepository persoRepo;
    
    @Override
    public ArrayList<Persona>getPersona(){
        
        ArrayList<Persona>listaPersonas = (ArrayList<Persona>)persoRepo.findAll();
        for (Persona persona : listaPersonas){
            calcularEdad(persona);
        }
        return listaPersonas;
    }
    
    @Override
    public Persona savePersona(Persona persona){
        return persoRepo.save(persona);
    }
    
    public Persona calcularEdad(Persona perso){
        String dateO = perso.getFechaNac();
        LocalDate fechaO=LocalDate.parse(dateO, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault()));
        LocalDate fechaHoy=LocalDate.now();
        Period periodo = fechaO.until(fechaHoy);
        int anios=periodo.getYears();
        perso.setEdad(anios);
        //actualiza la base
        savePersona(perso);
        return perso;
    }
    
    @Override
    public Optional<Persona> getPersonaByID(Long id){
        Persona per = persoRepo.findById(id).get();
        calcularEdad(per);
        Optional<Persona> pe = Optional.of(per);
        return pe;
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

