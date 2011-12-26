package net.sam_solutions.courses.dkrauchanka.providers;

import java.util.Iterator;
import net.sam_solutions.courses.dkrauchanka.detachable_models.DetachableReportModel;
import net.sam_solutions.courses.dkrauchanka.dto.ReportDTO;
import net.sam_solutions.courses.dkrauchanka.service.ReportService;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class ReportByUserDataProvider extends SortableDataProvider<ReportDTO>{
    private ReportService reportService = new ReportService();
    private String login;
    
    public ReportByUserDataProvider(String login){
        this.login = login;
    }

    public Iterator<ReportDTO> iterator(int first, int count) {
        Integer page = (int)Math.ceil(first/10)+1;
        return reportService.listReportByUser(page, 10, login).iterator();
    }

    public int size() {
        return reportService.getPagesCountByUser(1, login);
    }

    public IModel<ReportDTO> model(ReportDTO object) {
        return new DetachableReportModel(object);
    }

    public void detach() {
        
    }
}
