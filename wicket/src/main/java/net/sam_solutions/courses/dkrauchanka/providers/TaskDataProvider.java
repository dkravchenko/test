package net.sam_solutions.courses.dkrauchanka.providers;

import java.util.Iterator;
import net.sam_solutions.courses.dkrauchanka.detachable_models.DetachableTaskModel;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTOWicket;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class TaskDataProvider extends SortableDataProvider<TaskDTOWicket>{
    private TaskService taskService = new TaskService();

    public Iterator<TaskDTOWicket> iterator(int first, int count) {
        Integer page = (int)Math.ceil(first/10)+1;
        return taskService.listTaskWicket(page, 10).iterator();
    }

    public int size() {
        return taskService.getPagesCount(1,null,null);
    }

    public IModel<TaskDTOWicket> model(TaskDTOWicket object) {
        return new DetachableTaskModel(object);
    }

    public void detach() {
        
    }
}
