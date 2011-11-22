package net.sam_solutions.courses.dkrauchanka.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import net.sam_solutions.courses.dkrauchanka.framework.Action;


public class ActionFactory {
     public ActionFactory() {
          super();
     }
     
     public Action create(String actionName) {
    	 Action actionInstance = null;
    	 try {
    		 ApplicationContext context = new ClassPathXmlApplicationContext("controller.xml");
    		 actionInstance = (Action) context.getBean(actionName);
    	 }
    	 catch(CannotLoadBeanClassException e){
    		 actionInstance = new ErrorAction404();
    	 }
    	 catch(NoSuchBeanDefinitionException e){
    		 actionInstance = new ErrorAction404();
    	 }
          return actionInstance;
     }
}
