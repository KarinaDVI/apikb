
package com.portfolio.apikb.Controller;

import com.portfolio.apikb.models.Project;
import com.portfolio.apikb.services.ProjectService;
import java.util.ArrayList;
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

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/apikb/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    
    @GetMapping("/all")
    public ArrayList<Project> getAllProject(){
        return projectService.getAllProjects();
    }
    @PostMapping()
    public Project saveProject(@RequestBody Project project){
        return projectService.saveProject(project);
    }
    @GetMapping("/{id}")
    public Optional<Project> getProjectByID(@PathVariable("id") Long id){
        return projectService.getProjectByID(id);
    }
     @GetMapping("/query")
    public ArrayList<Project> getProjectByName(@RequestParam("nombre") String name){
        return projectService.getProjectByName(name);
    }
    @DeleteMapping("/{id}")
    public String removeProject(@PathVariable("id") Long id){
        if(projectService.removeProject(id)){
            return "Project eliminado "+id+" correctamente";
        }else{
            return "No se pudo eliminar los datos del proyecto";
        }
    }
    @PutMapping ("update/{id}")
    public Project updateProject (@PathVariable Long id,
                    @RequestParam("nombre") String nuevoNombre,
                    @RequestParam("acerca") String aboutProject,
                    @RequestParam("img") String img
                    ){
        
        Project project = projectService.findProject(id);
        
        project.setName(nuevoNombre);
        project.setAboutProyect(aboutProject);
        project.setImg(img);
        
        
        projectService.saveProject(project);
        return project;
    }
    
    /*
    @PutMapping ("edit/{id}")
    public Project editProject (@PathVariable("id") Long id, 
                        @RequestBody AboutDto aboutDTO){
        
        Project project=projectService.findProject(id);
        
        project.setNombre(aboutDTO.getNombre());
        project.setEdad(aboutDTO.getEdad());
        project.setSeniority(aboutDTO.getSeniority());
        project.setImage(aboutDTO.getImage());
        project.setCompany(aboutDTO.getCompany());
        project.setPosition(aboutDTO.getPosition());
        project.setAbouts(aboutDTO.getAbouts());

        projectService.saveProject(project);
        return project;
    }
       */                       
                              
}
