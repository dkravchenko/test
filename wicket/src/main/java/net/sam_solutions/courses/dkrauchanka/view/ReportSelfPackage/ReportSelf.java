package net.sam_solutions.courses.dkrauchanka.view.ReportSelfPackage;

import java.util.ArrayList;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.constants.Constants;
import net.sam_solutions.courses.dkrauchanka.dto.ReportDTO;
import net.sam_solutions.courses.dkrauchanka.panels.TablePanel;
import net.sam_solutions.courses.dkrauchanka.providers.ReportDataProvider;
import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.ResourceModel;

@AuthorizeInstantiation("user")
public class ReportSelf extends BasePage{
    final FeedbackPanel feedBack = new FeedbackPanel("feedback");
    private AjaxFallbackDefaultDataTable<ReportDTO> table;
    private List<IColumn<ReportDTO>> columns;
    private TablePanel<ReportDTO> panel;
  
    public ReportSelf(){
        columns = new ArrayList<IColumn<ReportDTO>>();
        
        columns.add(new PropertyColumn<ReportDTO>(new ResourceModel("label.dateofreport"),"dateOfReport"));
        columns.add(new PropertyColumn<ReportDTO>(new ResourceModel("label.workinghours"),"workingHours"));
        columns.add(new PropertyColumn<ReportDTO>(new ResourceModel("label.donehours"),"doneHours"));
        columns.add(new PropertyColumn<ReportDTO>(new ResourceModel("label.user"),"user"));
        columns.add(new PropertyColumn<ReportDTO>(new ResourceModel("label.tasks"),"tasks"));
        
        table = new AjaxFallbackDefaultDataTable<ReportDTO>("table", columns, new ReportDataProvider(),Constants.ROWS_ON_PAGE);
        panel = new TablePanel("tablePanel",table);
        panel.setOutputMarkupId(true);
        add(panel);
    }
}
