package net.sam_solutions.courses.dkrauchanka.view.TaskSelfPackage;

import java.util.ArrayList;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.SelectOption;
import net.sam_solutions.courses.dkrauchanka.SignInSession;
import net.sam_solutions.courses.dkrauchanka.panels.TablePanel;
import net.sam_solutions.courses.dkrauchanka.constants.Constants;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.providers.TaskByUserDataProvider;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
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
import org.apache.wicket.markup.html.basic.Label;
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
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.time.Duration;

@AuthorizeInstantiation("user")
public class TaskSelf extends BasePage{
    final FeedbackPanel feedBack = new FeedbackPanel("feedback");
    private AjaxFallbackDefaultDataTable<TaskDTO> table;
    private List<IColumn<TaskDTO>> columns;
    private Form<TaskDTO> form;
    private Form<Void> changeStatusForm;
    private AjaxButton addButton;
    private TaskDTO task = new TaskDTO();
    private TaskDTO toClose;
    private TaskDTO toStatusChange;
    private TaskService taskService = new TaskService();
    class ClosePanel extends Panel{
        public ClosePanel(String id, IModel<TaskDTO> model){
            super(id, model);
            add(new AjaxLink("close"){
                public void onClick(AjaxRequestTarget target){
                    toClose = (TaskDTO)getParent().getDefaultModelObject();
                    taskService.closeTask(toClose.getId());
                    target.add(table);
                }
            });
        }
        
    }
    class StatusChangePanel extends Panel{
        public StatusChangePanel(String id, IModel<TaskDTO> model){
            super(id, model);
            add(new AjaxLink("statusChange"){
                public void onClick(AjaxRequestTarget target){
                    toStatusChange = (TaskDTO)getParent().getDefaultModelObject();
                    selected = new SelectOption(toStatusChange.getStatus(),toStatusChange.getStatus());
                    taskTitle.setDefaultModelObject(toStatusChange.getTitle());
                    taskText.setDefaultModelObject(toStatusChange.getText());
                    taskHours.setDefaultModelObject(toStatusChange.getHoursToDo());
                    target.add(changeStatusForm);
                    dropDown.setVisible(true);
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
                task.setLogin(((SignInSession)this.getSession()).getLogin());
                taskService.addNewTask(null, task.getTitle(), task.getText(), task.getHoursToDo(), selectedStatus.getKey(), task.getLogin());
                task.setTitle("");
                task.setText("");
                task.setHoursToDo(null);
                task.setStatus("");
                selectedStatus = new SelectOption("to do","to do");
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
    private Label taskTitle;
    private Label taskText;
    private Label taskHours;
    private DropDownChoice dropDown;
    private String login = ((SignInSession)this.getSession()).getLogin();
    
    public TaskSelf(){
        columns = new ArrayList<IColumn<TaskDTO>>();
        
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.title"),"title"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.text"),"text"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.hours"),"hoursToDo"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.status"),"status"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.user"),"user"));
        columns.add(new AbstractColumn<TaskDTO>(new ResourceModel("link.close")){
            public void populateItem(Item<ICellPopulator<TaskDTO>> cellItem, String componentId, IModel<TaskDTO> model){
                cellItem.add(new ClosePanel(componentId, model));
            }
        });
        columns.add(new AbstractColumn<TaskDTO>(new ResourceModel("link.statusChange")){
            public void populateItem(Item<ICellPopulator<TaskDTO>> cellItem, String componentId, IModel<TaskDTO> model){
                cellItem.add(new StatusChangePanel(componentId, model));
            }
        });
        
        table = new AjaxFallbackDefaultDataTable<TaskDTO>("table", columns, new TaskByUserDataProvider(login),Constants.ROWS_ON_PAGE);
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
        dropDown = new DropDownChoice("status",new PropertyModel(this,"selectedStatus"), selStatus,choiceRenderer);
        form.add(dropDown);
        
        
        AjaxFormValidatingBehavior.addToAllFormComponents(form,"onkeyup", Duration.ONE_SECOND);
        addButton = new AddButton("ajax-button","button.add",form);
        form.add(addButton);
        
        changeStatusForm = new Form("changeStatusForm");
        add(changeStatusForm);
        changeStatusForm.setOutputMarkupId(true);
        taskTitle = new Label("taskTitle", new Model<String>(""));
        changeStatusForm.add(taskTitle);
        taskText = new Label("taskText", new Model<String>(""));
        changeStatusForm.add(taskText);
        taskHours = new Label("taskHours", new Model<String>(""));
        changeStatusForm.add(taskHours);
        dropDown = new DropDownChoice("taskStatus",new PropertyModel(this,"selected"), selStatus,choiceRenderer);
        dropDown.add(new AjaxFormComponentUpdatingBehavior("onchange"){
            protected void onUpdate(AjaxRequestTarget target){
                taskService.changeTaskStatus(toStatusChange.getId(), selected.getKey());
                table = new AjaxFallbackDefaultDataTable<TaskDTO>("table", columns, new TaskByUserDataProvider(login),10);
                panel.refreshTable(table);
                target.add(panel);
                taskTitle.setDefaultModelObject("");
                taskText.setDefaultModelObject("");
                taskHours.setDefaultModelObject("");
                dropDown.setVisible(false);
                target.add(changeStatusForm);
            }
        });
        dropDown.setVisible(false);
        changeStatusForm.add(dropDown);
    }
}
