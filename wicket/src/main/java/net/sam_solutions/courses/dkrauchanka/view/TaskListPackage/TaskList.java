package net.sam_solutions.courses.dkrauchanka.view.TaskListPackage;

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
import net.sam_solutions.courses.dkrauchanka.service.UserService;
import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

@AuthorizeInstantiation("user")
public class TaskList extends BasePage{
    final FeedbackPanel feedBack = new FeedbackPanel("feedback");
    private AjaxFallbackDefaultDataTable<TaskDTO> table;
    private List<IColumn<TaskDTO>> columns;
    private TablePanel<TaskDTO> panel;
    public SelectOption selected = new SelectOption("0","...");
    
    public TaskList(){
        columns = new ArrayList<IColumn<TaskDTO>>();
        
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.title"),"title"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.text"),"text"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.hours"),"hoursToDo"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.status"),"status"));
        columns.add(new PropertyColumn<TaskDTO>(new ResourceModel("label.user"),"user"));
        
        table = new AjaxFallbackDefaultDataTable<TaskDTO>("table", columns, new TaskDataProvider(),Constants.ROWS_ON_PAGE);
        panel = new TablePanel("tablePanel",table);
        panel.setOutputMarkupId(true);
        add(panel);
        add(feedBack);
        feedBack.setOutputMarkupId(true);
        
        Form form = new Form("filterForm");
        add(form);
        form.setOutputMarkupId(true);
       
        UserService userService = new UserService();
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
        
        ChoiceRenderer choiceRenderer = new ChoiceRenderer("value","key");
        DropDownChoice dropDown = new DropDownChoice("userFilter",new PropertyModel(this,"selected"), selList,choiceRenderer);
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
