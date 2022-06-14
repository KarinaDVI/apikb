
package com.portfolio.apikb.services;

import com.portfolio.apikb.models.Project;
import com.portfolio.apikb.repository.ProjectRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepo;
    
    public ArrayList<Project>getAllProjects(){
        return (ArrayList<Project>)projectRepo.findAll();
    }
    public Project saveProject(Project project){
        return projectRepo.save(project);
    }
    public Optional<Project> getProjectByID(Long id){
        return projectRepo.findById(id);
    }
    
    public Optional<Project> getProjectByName(String name){
        return projectRepo.findByName(name);
    }
    public boolean removeProject(Long id){
        try{
            projectRepo.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Project findProject(Long id){
        Project project = projectRepo.findById(id).orElse(null);
        return project;
    }

    public Project getOneProjectByID(Long id) {
        return projectRepo.findById(id).get();
    }

}

