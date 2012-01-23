package net.sam_solutions.courses.dkrauchanka.view.ReportAddPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.SelectOption;
import net.sam_solutions.courses.dkrauchanka.SignInSession;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.service.ReportService;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

@AuthorizeInstantiation("user")
public class ReportAdd extends BasePage{
    final FeedbackPanel feedBack = new FeedbackPanel("feedback");
    private Form form;
    private List<RequiredTextField<Integer>> textFieldList;
    private RequiredTextField<Integer> hoursField;
    private List<Label> labelList;
    private List<String> taskList;
    private List<String> valueList;
    private DropDownChoice dropDown;
    public SelectOption selected = new SelectOption("0","...");
    private Integer count = 0;
    private ReportService reportService = new ReportService();
    private String login = ((SignInSession)this.getSession()).getLogin();
    
    public ReportAdd(){
        form = new Form("reportAddForm");
        add(form);
        form.setOutputMarkupId(true);
        
        textFieldList = new ArrayList<RequiredTextField<Integer>>(5);
        labelList = new ArrayList<Label>(5);
        taskList = new ArrayList<String>(5);
        valueList = new ArrayList<String>(5);
        
        hoursField = new RequiredTextField<Integer>("hoursField", new Model<Integer>());
        form.add(hoursField);
        
        for(int i=0;i<5;i++){
            RequiredTextField<Integer> field = new RequiredTextField<Integer>("textField"+i, new Model<Integer>());
            field.setVisible(false);
            textFieldList.add(field);
            form.add(field);
            Label label = new Label("label"+i,new Model<String>(""));
            label.setVisible(false);
            labelList.add(label);
            form.add(label);
        }
        
        ChoiceRenderer choiceRenderer = new ChoiceRenderer("value","key");
        TaskService taskService = new TaskService();
        List<TaskDTO> list = taskService.showUserUnclosedTasks(((SignInSession)this.getSession()).getLogin(),1, taskService.getPagesCount(1,null,null));
        List<SelectOption> selList = new ArrayList<SelectOption>();
        selList.add(new SelectOption("0","..."));
        if(list != null){
            Iterator<TaskDTO> iter = list.iterator();
            while(iter.hasNext()){
                TaskDTO taskDto = iter.next();
                selList.add(new SelectOption(String.valueOf(taskDto.getId()),taskDto.getTitle()));
            }
        }
        
        dropDown = new DropDownChoice("task",new PropertyModel(this,"selected"), selList,choiceRenderer);
        dropDown.add(new AjaxFormComponentUpdatingBehavior("onchange"){
            protected void onUpdate(AjaxRequestTarget target){
                if(!selected.equals(new SelectOption("0","...")) && count < 5 && !isAdded(selected)){
                    count++;
                    makeVisible(count,selected);
                }
                target.add(form);
            }
        });
        form.add(dropDown);
        form.add(new AjaxButton("ajax-button",new ResourceModel("button.addReport"),form){

            @Override
            protected void onSubmit(AjaxRequestTarget art, Form<?> form) {
                getFields();
                reportService.addReport(taskList, valueList, hoursField.getDefaultModelObjectAsString(), login);
                makeUnvisible();
                art.add(form);
            }

            @Override
            protected void onError(AjaxRequestTarget art, Form<?> form) {
                art.add(feedBack);
            }
            
        });
        add(feedBack);
    }
    
    private void makeVisible(Integer count, SelectOption option){
        try{
            Iterator<RequiredTextField<Integer>> iterField = textFieldList.iterator();
            Iterator<Label> iterLabel = labelList.iterator();
            RequiredTextField<Integer> field = null;
            Label label = null;
            for(int i=0;i<count;i++){
                field = iterField.next();
                label = iterLabel.next();
            }
            field.setVisible(true);
            label.setVisible(true);
            label.setDefaultModel(new Model(count+". "+option.getValue()));
            taskList.add(option.getKey());
        }
        catch(NullPointerException e){
            
        }
    }
    
    private void getFields(){
        try{
            Iterator<RequiredTextField<Integer>> iterField = textFieldList.iterator();
            RequiredTextField<Integer> field = null;
            for(int i=0;i<count;i++){
                field = iterField.next();
                valueList.add(field.getDefaultModelObjectAsString());
            }
        }
        catch(NullPointerException e){
            
        }
    }
    
    private void makeUnvisible(){
        try{
            Iterator<RequiredTextField<Integer>> iterField = textFieldList.iterator();
            Iterator<Label> iterLabel = labelList.iterator();
            RequiredTextField<Integer> field = null;
            Label label = null;
            for(int i=0;i<5;i++){
                field = iterField.next();
                label = iterLabel.next();
                field.setVisible(false);
                label.setVisible(false);
                label.setDefaultModel(new Model(""));
            }
            hoursField.setModel(new Model<Integer>());
            taskList = new ArrayList<String>();
            valueList = new ArrayList<String>();
            selected = new SelectOption("0","...");
        }
        catch(NullPointerException e){
            
        }
    }
    
    private boolean isAdded(SelectOption option){
        Iterator<String> iter = taskList.iterator();
        if(iter != null){
            while(iter.hasNext()){
                if(iter.next().equals(option.getValue()))
                    return true;
            }
        }
        return false;
    }
}