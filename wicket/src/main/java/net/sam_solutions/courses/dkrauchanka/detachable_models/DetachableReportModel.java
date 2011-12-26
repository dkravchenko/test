package net.sam_solutions.courses.dkrauchanka.detachable_models;

import net.sam_solutions.courses.dkrauchanka.dto.ReportDTO;
import net.sam_solutions.courses.dkrauchanka.service.ReportService;
import org.apache.wicket.model.LoadableDetachableModel;

public class DetachableReportModel extends LoadableDetachableModel<ReportDTO>{
    private final Integer id;
    
    public DetachableReportModel(Integer id){
        if(id == null){
            throw new IllegalArgumentException();
        }
        this.id = id;
    }
    
    public DetachableReportModel(ReportDTO report){
        this(report.getId());
    }
    
    @Override
    protected ReportDTO load() {
        ReportService reportService = new ReportService();
        return reportService.getReport(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetachableReportModel other = (DetachableReportModel) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
}
