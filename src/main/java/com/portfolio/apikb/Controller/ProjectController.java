
package com.portfolio.apikb.Controller;

import com.portfolio.apikb.models.Project;
import com.portfolio.apikb.services.ProjectService;
import java.util.ArrayList;
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
    @GetMapping("/id/{id}")
    public Optional<Project> getProjectByID(@PathVariable("id") Long id){
        return projectService.getProjectByID(id);
    }
    @GetMapping("/one/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long id) {
        Project project = projectService.getOneProjectByID(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
     @GetMapping("/getname/{name}")
    public Optional<Project> getProjectByName(@RequestParam("nombre") String name){
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
                    @RequestParam("urlimg") String urlimg
                    ){
        
        Project project = projectService.findProject(id);
        
        project.setName(nuevoNombre);
        project.setAboutProject(aboutProject);
        project.setUrlimg(urlimg);
        projectService.saveProject(project);
        return project;
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long id, @RequestBody Project projectRequest) {
        Project project =projectService.getOneProjectByID(id);
        // .orElseThrow(() -> new ResourceNotFoundException("EducationId " + id + "not found"));
        //skill.setPerson(skillRequest.getPerson());
        projectRequest.setId(project.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(projectRequest, project);

        return new ResponseEntity<>(projectService.saveProject(project), HttpStatus.OK);
    }
                        
                              
}
