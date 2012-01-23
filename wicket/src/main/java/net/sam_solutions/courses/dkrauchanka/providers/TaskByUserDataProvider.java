package net.sam_solutions.courses.dkrauchanka.providers;

import java.util.Iterator;
import net.sam_solutions.courses.dkrauchanka.detachable_models.DetachableTaskModel;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class TaskByUserDataProvider extends SortableDataProvider<TaskDTO>{
    private TaskService taskService = new TaskService();
    private String login;
    
    public TaskByUserDataProvider(String login){
        this.login = login;
    }

    public Iterator<TaskDTO> iterator(int first, int count) {
        Integer page = (int)Math.ceil(first/10)+1;
        return taskService.listTaskByUser(page, 10, login).iterator();
    }

    public int size() {
        return taskService.getPagesCount(1, "filter_user" ,login);
    }

    public IModel<TaskDTO> model(TaskDTO object) {
        return new DetachableTaskModel(object);
    }

    public void detach() {
        
    }
}
