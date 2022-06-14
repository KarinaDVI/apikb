
package com.portfolio.apikb.InterfaceService;

import com.portfolio.apikb.models.Persona;
import java.util.ArrayList;
import java.util.Optional;


public interface IPersonaService {
    
    public ArrayList<Persona> getPersona();
    public Persona savePersona(Persona persona);
    public boolean deletePersona(Long id);
    public Persona findPersona(Long id);
    public Persona getOnePersonByID(Long id);
    public Optional<Persona> getPersonaByID(Long id);
    public Optional<Persona> getPersonaByNombre(String nombre);
    public boolean removePersona(Long id);
}
