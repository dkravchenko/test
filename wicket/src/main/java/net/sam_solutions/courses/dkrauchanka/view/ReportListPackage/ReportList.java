package net.sam_solutions.courses.dkrauchanka.view.ReportListPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.dto.ReportDTO;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.providers.ReportByUserDataProvider;
import net.sam_solutions.courses.dkrauchanka.providers.ReportDataProvider;
import net.sam_solutions.courses.dkrauchanka.service.UserService;
import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import net.sam_solutions.courses.dkrauchanka.SelectOption;
import net.sam_solutions.courses.dkrauchanka.dto.TaskDTO;
import net.sam_solutions.courses.dkrauchanka.service.TaskService;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

@AuthorizeInstantiation("admin")
public class ReportList extends BasePage{
    final FeedbackPanel feedBack = new FeedbackPanel("feedback");
    private AjaxFallbackDefaultDataTable<ReportDTO> table;
    private List<IColumn<ReportDTO>> columns;
    class TablePanel extends Panel{
        private AjaxFallbackDefaultDataTable<ReportDTO> table;
        
        TablePanel(String name, AjaxFallbackDefaultDataTable<ReportDTO> table){
            super(name);
            this.table = table;
            this.add(table);
        }
        
        public void refreshTable(AjaxFallbackDefaultDataTable<ReportDTO> table){
            this.remove(this.table);
            this.table = table;
            this.add(table);
        }
    }
    public SelectOption selected = new SelectOption("0","...");
    public SelectOption selectedTask = new SelectOption("0","...");
    private TablePanel panel;
    
  
    public ReportList(){
        columns = new ArrayList<IColumn<ReportDTO>>();
        
        columns.add(new PropertyColumn<ReportDTO>(new ResourceModel("label.dateofreport"),"dateOfReport"));
        columns.add(new PropertyColumn<ReportDTO>(new ResourceModel("label.workinghours"),"workingHours"));
        columns.add(new PropertyColumn<ReportDTO>(new ResourceModel("label.donehours"),"doneHours"));
        columns.add(new PropertyColumn<ReportDTO>(new ResourceModel("label.user"),"user"));
        columns.add(new PropertyColumn<ReportDTO>(new ResourceModel("label.tasks"),"tasks"));
        
        table = new AjaxFallbackDefaultDataTable<ReportDTO>("table", columns, new ReportDataProvider(),10);
        panel = new TablePanel("tablePanel",table);
        panel.setOutputMarkupId(true);
        add(panel);
        
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
                    table = new AjaxFallbackDefaultDataTable<ReportDTO>("table", columns, new ReportDataProvider(),10);
                }
                else{
                    selectedTask = new SelectOption("0","...");
                    table = new AjaxFallbackDefaultDataTable<ReportDTO>("table", columns, new ReportByUserDataProvider(selected.getKey()),10);
                }
                panel.refreshTable(table);
                target.add(panel);
            }
        });
        form.add(dropDown);
    }
}
