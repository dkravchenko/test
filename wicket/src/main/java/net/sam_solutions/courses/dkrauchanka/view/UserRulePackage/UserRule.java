package net.sam_solutions.courses.dkrauchanka.view.UserRulePackage;

import java.util.ArrayList;
import java.util.List;
import net.sam_solutions.courses.dkrauchanka.dto.UserDTO;
import net.sam_solutions.courses.dkrauchanka.providers.UserDataProvider;
import net.sam_solutions.courses.dkrauchanka.service.UserService;
import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.validation.validator.StringValidator;

@AuthorizeInstantiation("admin")
public class UserRule extends BasePage{
    final FeedbackPanel feedBack = new FeedbackPanel("feedback");
    private AjaxFallbackDefaultDataTable<UserDTO> table;
    private Form<UserDTO> form;
    private FormComponent formLoginComponent;
    private AjaxButton updateButton;
    private AjaxButton addButton;
    private UserDTO user = new UserDTO();
    private UserDTO toDelete;
    private UserDTO toUpdate;
    private UserService userService = new UserService();
    class DeletePanel extends Panel{
        public DeletePanel(String id, IModel<UserDTO> model){
            super(id, model);
            add(new AjaxLink("delete"){
                public void onClick(AjaxRequestTarget target){
                    toDelete = (UserDTO)getParent().getDefaultModelObject();
                    userService.removeUser(toDelete.getLogin());
                    target.add(table);
                }
            });
        }
        
    }
    class UpdatePanel extends Panel{
        public UpdatePanel(String id, IModel<UserDTO> model){
            super(id, model);
            add(new AjaxLink("update"){
                public void onClick(AjaxRequestTarget target){
                    toUpdate = (UserDTO)getParent().getDefaultModelObject();
                    user.setLogin(toUpdate.getLogin());
                    user.setFirstName(toUpdate.getFirstName());
                    user.setLastName(toUpdate.getLastName());
                    addButton.setVisible(false);
                    updateButton.setVisible(true);
                    formLoginComponent.setEnabled(false);
                    target.add(form);
                }
            });
        }  
    }
    class AddButton extends AjaxButton{
        public AddButton(String name, String value, Form form){
            super(name,new ResourceModel(value),form);
        }
        protected void onSubmit(AjaxRequestTarget target, Form<?> form){
                target.add(feedBack);
                userService.addNewUser(user.getLogin(), user.getFirstName(), user.getLastName());
                user.setLogin("");
                user.setFirstName("");
                user.setLastName("");
                addButton.setVisible(true);
                updateButton.setVisible(false);
                formLoginComponent.setEnabled(true);
                target.add(table);
                target.add(form);
        }
        protected void onError(AjaxRequestTarget target, Form<?> form){
                target.add(feedBack);
        }
    }
    
    public UserRule(){ 
        List<IColumn<UserDTO>> columns = new ArrayList<IColumn<UserDTO>>();
        
        
        columns.add(new PropertyColumn<UserDTO>(new ResourceModel("label.login"),"login"));
        columns.add(new PropertyColumn<UserDTO>(new ResourceModel("label.firstname"),"firstName"));
        columns.add(new PropertyColumn<UserDTO>(new ResourceModel("label.lastname"),"lastName"));
        columns.add(new AbstractColumn<UserDTO>(new ResourceModel("link.delete")){
            public void populateItem(Item<ICellPopulator<UserDTO>> cellItem, String componentId, IModel<UserDTO> model){
                cellItem.add(new DeletePanel(componentId, model));
            }
        });
        columns.add(new AbstractColumn<UserDTO>(new ResourceModel("link.update")){
            public void populateItem(Item<ICellPopulator<UserDTO>> cellItem, String componentId, IModel<UserDTO> model){
                cellItem.add(new UpdatePanel(componentId, model));
            }
        });
        
        table = new AjaxFallbackDefaultDataTable<UserDTO>("table", columns, new UserDataProvider(),10);
        add(table);
        add(feedBack);
        feedBack.setOutputMarkupId(true);
        
        form = new Form<UserDTO>("addUserForm", new CompoundPropertyModel<UserDTO>(user));
        add(form);
        form.setOutputMarkupId(true);
        
        FormComponent formComponent;
        formLoginComponent = new RequiredTextField<String>("login");
        formLoginComponent.add(StringValidator.minimumLength(4));
        formLoginComponent.setLabel(new ResourceModel("label.login"));
        form.add(formLoginComponent);
        form.add(new SimpleFormComponentLabel("login-label",formLoginComponent));
        
        formComponent  = new RequiredTextField<String>("firstName");
        formComponent.setLabel(new ResourceModel("label.firstname"));
        form.add(formComponent);
        form.add(new SimpleFormComponentLabel("firstName-label",formComponent));
        
        formComponent  = new RequiredTextField<String>("lastName");
        formComponent.setLabel(new ResourceModel("label.lastname"));
        form.add(formComponent);
        form.add(new SimpleFormComponentLabel("lastName-label",formComponent));
        
        AjaxFormValidatingBehavior.addToAllFormComponents(form,"onkeyup", Duration.ONE_SECOND);
        updateButton = new AddButton("ajax-update-button","button.update",form);
        updateButton.setVisible(false);
        addButton = new AddButton("ajax-button","button.add",form);
        form.add(addButton);
        form.add(updateButton);
    }
   
}
