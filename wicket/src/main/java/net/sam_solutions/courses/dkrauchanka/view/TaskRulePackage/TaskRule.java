package net.sam_solutions.courses.dkrauchanka.view.TaskRulePackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.SelectOption;
import net.sam_solutions.courses.dkrauchanka.constants.Constants;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.panels.TablePanel;
import net.sam_solutions.courses.dkrauchanka.providers.TaskByUserDataProvider;
import net.sam_solutions.courses.dkrauchanka.providers.TaskDataProvider;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
import net.sam_solutions.courses.dkrauchanka.service.UserService;
import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.time.Duration;

@AuthorizeInstantiation("admin")
public class TaskRule extends BasePage{
    final FeedbackPanel feedBack = new FeedbackPanel("feedback");
    private AjaxFallbackDefaultDataTable<TaskDTO> table;
    private List<IColumn<TaskDTO>> columns;
    private Form<TaskDTO> form;
    private AjaxButton updateButton;
    private AjaxButton addButton;
    private TaskDTO task = new TaskDTO();
    private TaskDTO toDelete;
    private TaskDTO toUpdate;
    private TaskService taskService = new TaskService();
    private UserService userService = new UserService();
    class DeletePanel extends Panel{
        public DeletePanel(String id, IModel<TaskDTO> model){
            super(id, model);
            add(new AjaxLink("delete"){
                public void onClick(AjaxRequestTarget target){
                    toDelete = (TaskDTO)getParent().getDefaultModelObject();
                    taskService.removeTask(toDelete.getId());
                    target.add(table);
                }
            });
        }
        
    }
    class UpdatePanel extends Panel{
        public UpdatePanel(String id, IModel<TaskDTO> model){
            super(id, model);
            add(new AjaxLink("update"){
                public void onClick(AjaxRequestTarget target){
                    toUpdate = (TaskDTO)getParent().getDefaultModelObject();
                    task.setTitle(toUpdate.getTitle());
                    task.setText(toUpdate.getText());
                    task.setHoursToDo(toUpdate.getHoursToDo());
                    task.setStatus(toUpdate.getStatus());
                    selectedStatus = new SelectOption(toUpdate.getStatus(),toUpdate.getStatus());
                    task.setUser(toUpdate.getUser());
                    selectedUser = new SelectOption(toUpdate.getLogin(),userService.getUser(toUpdate.getLogin()).toString());
                    addButton.setVisible(false);
                    updateButton.setVisible(true);
                    target.add(form);
                }
            });
        }  
    }
    class AddButton extends AjaxButton{
        public AddButton(String name, String value, Form form){
            super(name,new ResourceModel(value),form);
        }
        protected void onSubmit(AjaxRequestTarget target, Form<?> form){
                target.add(feedBack);
                if(toUpdate != null)
                    taskService.addNewTask(toUpdate.getId(), task.getTitle(), task.getText(), task.getHoursToDo(), selectedStatus.getKey(), selectedUser.getKey());
                else
                    taskService.addNewTask(null, task.getTitle(), task.getText(), task.getHoursToDo(), selectedStatus.getKey(), selectedUser.getKey());
                task.setTitle("");
                task.setText("");
                task.setHoursToDo(null);
                task.setStatus("");
                selectedStatus = new SelectOption("to do","to do");
                selectedUser = new SelectOption("0","...");
                addButton.setVisible(true);
                updateButton.setVisible(false);
                target.add(table);
                target.add(form);
        }
        protected void onError(AjaxRequestTarget target, Form<?> form){
                target.add(feedBack);
        }
    }
    private TablePanel<TaskDTO> panel;
    public SelectOption selected = new SelectOption("0","...");
    public SelectOption selectedStatus = new SelectOption("to do","to do");
    public SelectOption selectedUser = new SelectOption("0","...");
    
    public TaskRule(){
        columns = new ArrayList<IColumn<TaskDTO>>();
        
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.title"),"title"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.text"),"text"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.hours"),"hoursToDo"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.status"),"status"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.user"),"user"));
        columns.add(new AbstractColumn<TaskDTO>(new ResourceModel("link.delete")){
            public void populateItem(Item<ICellPopulator<TaskDTO>> cellItem, String componentId, IModel<TaskDTO> model){
                cellItem.add(new DeletePanel(componentId, model));
            }
        });
        columns.add(new AbstractColumn<TaskDTO>(new ResourceModel("link.update")){
            public void populateItem(Item<ICellPopulator<TaskDTO>> cellItem, String componentId, IModel<TaskDTO> model){
                cellItem.add(new UpdatePanel(componentId, model));
            }
        });
        
        table = new AjaxFallbackDefaultDataTable<TaskDTO>("table", columns, new TaskDataProvider(),Constants.ROWS_ON_PAGE);
        panel = new TablePanel("tablePanel",table);
        panel.setOutputMarkupId(true);
        add(panel);
        add(feedBack);
        feedBack.setOutputMarkupId(true);
        
        form = new Form<TaskDTO>("addTaskForm", new CompoundPropertyModel<TaskDTO>(task));
        add(form);
        form.setOutputMarkupId(true);
        
        FormComponent formComponent;
        formComponent = new RequiredTextField<String>("title");
        formComponent.setLabel(new ResourceModel("label.title"));
        form.add(formComponent);
        form.add(new SimpleFormComponentLabel("title-label",formComponent));
        
        formComponent  = new RequiredTextField<String>("text");
        formComponent.setLabel(new ResourceModel("label.text"));
        form.add(formComponent);
        form.add(new SimpleFormComponentLabel("text-label",formComponent));
        
        formComponent  = new RequiredTextField<Integer>("hoursToDo");
        formComponent.setLabel(new ResourceModel("label.hours"));
        form.add(formComponent);
        form.add(new SimpleFormComponentLabel("hoursToDo-label",formComponent));
        
        List<SelectOption> selStatus = new ArrayList<SelectOption>();
        selStatus.add(new SelectOption("to do","to do"));
        selStatus.add(new SelectOption("in progress","in progress"));
        ChoiceRenderer choiceRenderer = new ChoiceRenderer("value","key");
        DropDownChoice dropDown = new DropDownChoice("status",new PropertyModel(this,"selectedStatus"), selStatus,choiceRenderer);
        form.add(dropDown);
        
        List<UserDTO> list = userService.listUsers(1, userService.getPagesCount(1));
        List<SelectOption> selList = new ArrayList<SelectOption>();
        selList.add(new SelectOption("0","..."));
        if(list != null){
            Iterator<UserDTO> iter = list.iterator();
            while(iter.hasNext()){
                UserDTO userDto = iter.next();
                selList.add(new SelectOption(userDto.getLogin(),userDto.getFirstName()+" "+userDto.getLastName()));
            }
        }
        dropDown = new DropDownChoice("user",new PropertyModel(this,"selectedUser"), selList,choiceRenderer);
        form.add(dropDown);
        
        AjaxFormValidatingBehavior.addToAllFormComponents(form,"onkeyup", Duration.ONE_SECOND);
        updateButton = new AddButton("ajax-update-button","button.update",form);
        updateButton.setVisible(false);
        addButton = new AddButton("ajax-button","button.add",form);
        form.add(addButton);
        form.add(updateButton);
        
        Form form = new Form("filterForm");
        add(form);
        form.setOutputMarkupId(true);
       
        dropDown = new DropDownChoice("userFilter",new PropertyModel(this,"selected"), selList,choiceRenderer);
        dropDown.add(new AjaxFormComponentUpdatingBehavior("onchange"){
            protected void onUpdate(AjaxRequestTarget target){
                if(selected.equals(new SelectOption("0","..."))){
                    table = new AjaxFallbackDefaultDataTable<TaskDTO>("table", columns, new TaskDataProvider(),Constants.ROWS_ON_PAGE);
                }
                else{
                    table = new AjaxFallbackDefaultDataTable<TaskDTO>("table", columns, new TaskByUserDataProvider(selected.getKey()),Constants.ROWS_ON_PAGE);
                }
                panel.refreshTable(table);
                target.add(panel);
            }
        });
        form.add(dropDown);
    }
    
    
}
