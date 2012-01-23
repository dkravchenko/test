package net.sam_solutions.courses.dkrauchanka.detachable_models;

import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
import org.apache.wicket.model.LoadableDetachableModel;

public class DetachableTaskModel extends LoadableDetachableModel<TaskDTO>{
    private final Integer id;
    
    public DetachableTaskModel(Integer id){
        if(id == null){
            throw new IllegalArgumentException();
        }
        this.id = id;
    }
    
    public DetachableTaskModel(TaskDTO task){
        this(task.getId());
    }
    
    @Override
    protected TaskDTO load() {
        TaskService taskService = new TaskService();
        return taskService.getTask(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetachableTaskModel other = (DetachableTaskModel) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    
    
    
}
