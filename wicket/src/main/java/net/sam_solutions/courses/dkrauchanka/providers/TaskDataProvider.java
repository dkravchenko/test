package net.sam_solutions.courses.dkrauchanka.providers;

import java.util.Iterator;
import net.sam_solutions.courses.dkrauchanka.detachable_models.DetachableTaskModel;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class TaskDataProvider extends SortableDataProvider<TaskDTO>{
    private TaskService taskService = new TaskService();

    public Iterator<TaskDTO> iterator(int first, int count) {
        Integer page = (int)Math.ceil(first/10)+1;
        return taskService.listTask(page, 10).iterator();
    }

    public int size() {
        return taskService.getPagesCount(1,null,null);
    }

    public IModel<TaskDTO> model(TaskDTO object) {
        return new DetachableTaskModel(object);
    }

    public void detach() {
        
    }
}
