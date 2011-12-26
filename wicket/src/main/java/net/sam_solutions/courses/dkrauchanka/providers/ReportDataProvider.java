package net.sam_solutions.courses.dkrauchanka.providers;

import java.util.Iterator;
import net.sam_solutions.courses.dkrauchanka.detachable_models.DetachableReportModel;
import net.sam_solutions.courses.dkrauchanka.dto.ReportDTO;
import net.sam_solutions.courses.dkrauchanka.service.ReportService;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class ReportDataProvider extends SortableDataProvider<ReportDTO>{
    private ReportService reportService = new ReportService();

    public Iterator<ReportDTO> iterator(int first, int count) {
        Integer page = (int)Math.ceil(first/10)+1;
        return reportService.listReport(page, 10).iterator();
    }

    public int size() {
        return reportService.getPagesCount(1);
    }

    public IModel<ReportDTO> model(ReportDTO object) {
        return new DetachableReportModel(object);
    }

    public void detach() {
        
    }
}
