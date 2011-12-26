package net.sam_solutions.courses.dkrauchanka.tags;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.jsp.tagext.TagSupport;

public class LocaleTag extends TagSupport{
    private String value;
    private String file = "text";

    public int doStartTag(){
        Locale locale = pageContext.getRequest().getLocale();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(this.file, locale);
        String str = resourceBundle.getString(this.value);
         try { 
        pageContext.getOut().write(str); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
        return SKIP_BODY;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public void setFile(String file) {
        this.file = file;
    }
}
