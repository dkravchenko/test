package net.sam_solutions.courses.dkrauchanka.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.domain.Task;
import net.sam_solutions.courses.dkrauchanka.domain.User;

public class TaskDTOWicket {
    private Integer id;
    private String title;
    private String text;
    private Integer hoursToDo;
    private String status;
    private String user; 
    private String login;
    
    public TaskDTOWicket(){
        
    }
    
    public TaskDTOWicket(Task task){
        this.id = task.getId();
        this.title = task.getTitle();
        this.text = task.getText();
        this.hoursToDo = task.getHoursToDo();
        this.status = task.getStatus();
        this.user = task.getUser().getFirstName()+" "+task.getUser().getLastName();
        this.login = task.getUser().getLogin();
    }

    public TaskDTOWicket(Integer id, String title, String text, Integer hoursToDo, String status,String user) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.hoursToDo = hoursToDo;
        this.status = status;
        this.user = user;
    }
    
    public static List<TaskDTOWicket> taskToTaskDTOList(List<Task> list){
        try{
            List<TaskDTOWicket> temp = new ArrayList<TaskDTOWicket>();
            Iterator<Task> iter = list.iterator();
            while(iter.hasNext()){
                TaskDTOWicket taskDto = new TaskDTOWicket(iter.next());
                temp.add(taskDto);
            }
            return temp;
        }
        catch(NullPointerException e){
            return null;
        }
    }

    public Integer getHoursToDo() {
        return hoursToDo;
    }

    public void setHoursToDo(Integer hoursToDo) {
        this.hoursToDo = hoursToDo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaskDTOWicket other = (TaskDTOWicket) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if ((this.text == null) ? (other.text != null) : !this.text.equals(other.text)) {
            return false;
        }
        if (this.hoursToDo != other.hoursToDo && (this.hoursToDo == null || !this.hoursToDo.equals(other.hoursToDo))) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 97 * hash + (this.text != null ? this.text.hashCode() : 0);
        hash = 97 * hash + (this.hoursToDo != null ? this.hoursToDo.hashCode() : 0);
        hash = 97 * hash + (this.status != null ? this.status.hashCode() : 0);
        return hash;
    }
    
    
}
