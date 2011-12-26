package net.sam_solutions.courses.dkrauchanka.tags;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

public class Paginator {
    
    public static String generateString(Integer pages, Integer currentPage, String path, String filterName, String filterValue){
        String str = "";
        String filter = "";
        if(filterName != null && !filterName.equals("") && filterValue != null && !filterValue.equals("")){
            filter = "&"+filterName+"="+filterValue;
        }
            if(pages <= 7){
                for(int i = 0; i < pages; i ++){
                    if(i+1 == currentPage)
                        str += "<a style=\"margin:10px; color:#00FF00\" href=\""+path+"?page="+String.valueOf(i+1)+filter+"\">"+String.valueOf(i+1)+"</a>";
                    else
                        str += "<a style=\"margin:10px\" href=\""+path+"?page="+String.valueOf(i+1)+filter+"\">"+String.valueOf(i+1)+"</a>";
                }
            }
            else if(currentPage-3 <= 0){
                for(int i = 0; i < 7; i ++){
                    if(i+1 == currentPage)
                        str += "<a style=\"margin:10px; color:#00FF00\" href=\""+path+"?page="+String.valueOf(i+1)+filter+"\">"+String.valueOf(i+1)+"</a>";
                    else
                        str += "<a style=\"margin:10px\" href=\""+path+"?page="+String.valueOf(i+1)+filter+"\">"+String.valueOf(i+1)+"</a>";
                }
                str += "...";
            }
            else if(pages-currentPage <= 3)
            {
                str += "...";
                for(int i = pages-8; i < pages; i ++){
                    if(i+1 == currentPage)
                        str += "<a style=\"margin:10px; color:#00FF00\" href=\""+path+"?page="+String.valueOf(i+1)+filter+"\">"+String.valueOf(i+1)+"</a>";
                    else
                        str += "<a style=\"margin:10px\" href=\""+path+"?page="+String.valueOf(i+1)+filter+"\">"+String.valueOf(i+1)+"</a>";
                }
            }
            else{
               str += "...";
                for(int i = currentPage - 4; i < currentPage+3; i ++){
                    if(i+1 == currentPage)
                        str += "<a style=\"margin:10px; color:#00FF00\" href=\""+path+"?page="+String.valueOf(i+1)+filter+"\">"+String.valueOf(i+1)+"</a>";
                    else
                        str += "<a style=\"margin:10px\" href=\""+path+"?page="+String.valueOf(i+1)+filter+"\">"+String.valueOf(i+1)+"</a>";
                } 
                str += "...";
            }
        return str;
    }
}
