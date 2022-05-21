
package com.portfolio.apikb.InterfaceService;

import com.portfolio.apikb.models.Persona;
import java.util.List;
import java.util.Optional;


public interface IPersonaService {
    
    public List<Persona> getPersona();
    public Persona savePersona(Persona persona);
    public boolean deletePersona(Long id);
    public Persona findPersona(Long id);
    public Optional<Persona> getPersonaByID(Long id);
    public List<Persona> getPersonaByNombre(String nombre);
    public boolean removePersona(Long id);
}
