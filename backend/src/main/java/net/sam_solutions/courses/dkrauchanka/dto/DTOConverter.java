package net.sam_solutions.courses.dkrauchanka.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.domain.Report;
import net.sam_solutions.courses.dkrauchanka.domain.Task;
import net.sam_solutions.courses.dkrauchanka.domain.User;

public class DTOConverter {
    public static List<UserDTO> userToUserDTOList(List<User> list){
        try{
            List<UserDTO> temp = new ArrayList<UserDTO>();
            Iterator<User> iter = list.iterator();
            while(iter.hasNext()){
                UserDTO userDto = new UserDTO(iter.next());
                temp.add(userDto);
            }
            return temp;
        }
        catch(NullPointerException e){
            return null;
        }
    }
    
    public static List<ReportDTO> reportListToReportDTOList(List<Report> list){
        if(list != null){
            List<ReportDTO> temp = new ArrayList<ReportDTO>();
            Iterator<Report> iter = list.iterator();
            while(iter.hasNext()){
                Report report = iter.next();
                List<Task> tasks = report.getTasks();
                String taskStr = "";
                Iterator<Task> iterTask = tasks.iterator();
                while(iterTask.hasNext()){
                    Task task = iterTask.next();
                    taskStr += task.getTitle() + ", ";
                }
                if(tasks.size() >= 1)
                    taskStr.substring(0, taskStr.lastIndexOf(","));
                ReportDTO reportDto = new ReportDTO(report.getId(),report.getDateOfReport(),report.getWorgingHours(),report.getDoneHours(),report.getUser().getFirstName()+" "+report.getUser().getLastName(),taskStr);
                temp.add(reportDto);
            }
            return temp;
        }
        else{
            return null;
        }
    }
    
    public static List<TaskDTO> taskToTaskDTOList(List<Task> list){
        try{
            List<TaskDTO> temp = new ArrayList<TaskDTO>();
            Iterator<Task> iter = list.iterator();
            while(iter.hasNext()){
                TaskDTO taskDto = new TaskDTO(iter.next());
                temp.add(taskDto);
            }
            return temp;
        }
        catch(NullPointerException e){
            return null;
        }
    }
}
