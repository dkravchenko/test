package net.sam_solutions.courses.dkrauchanka.view.ChangePassPackage;

import net.sam_solutions.courses.dkrauchanka.NewPass;
import net.sam_solutions.courses.dkrauchanka.SignInSession;
import net.sam_solutions.courses.dkrauchanka.service.UserService;
import net.sam_solutions.courses.dkrauchanka.validators.UserPasswordValidator;
import net.sam_solutions.courses.dkrauchanka.view.BasePagePackage.BasePage;
import net.sam_solutions.courses.dkrauchanka.view.HomePackage.Home;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.time.Duration;

@AuthorizeInstantiation({"admin", "user"})
public class ChangePass extends BasePage{
    final FeedbackPanel feedBack = new FeedbackPanel("feedback");
    private Form<NewPass> form;
    private NewPass pass = new NewPass();
    private UserService userService = new UserService();
    
    public ChangePass(){
        String login = ((SignInSession)this.getSession()).getLogin();
        feedBack.setOutputMarkupId(true);
        add(feedBack);
        form = new Form<NewPass>("newPassForm", new CompoundPropertyModel<NewPass>(pass));
        add(form);
        form.setOutputMarkupId(true);
        
        FormComponent formComponent;
        formComponent = new RequiredTextField<String>("pass");
        formComponent.setLabel(new ResourceModel("label.pass"));
        formComponent.add(new UserPasswordValidator(login));
        form.add(formComponent);
        form.add(new SimpleFormComponentLabel("pass-label",formComponent));
        
        formComponent  = new RequiredTextField<String>("newPass");
        formComponent.setLabel(new ResourceModel("label.newPass"));
        form.add(formComponent);
        form.add(new SimpleFormComponentLabel("newPass-label",formComponent));
        
        formComponent  = new RequiredTextField<String>("confirmPass");
        formComponent.setLabel(new ResourceModel("label.confirmPass"));
//        formComponent.add(new AbstractValidator(){
//            @Override
//            protected void onValidate(IValidatable iv) {
//                String value = (String) iv.getValue();
//                if(!value.equals(pass.getNewPass())){
//                    error(iv);
//                }
//            }
//    
//            @Override
//            protected String resourceKey() {
//                return "error.missmatch";
//            }
//        });
        
        form.add(formComponent);
        form.add(new SimpleFormComponentLabel("confirmPass-label",formComponent));
        
        AjaxFormValidatingBehavior.addToAllFormComponents(form,"onkeyup", Duration.ONE_SECOND);
        form.add(new AjaxButton("ajax-button",new ResourceModel("button.change"),form){
            protected void onSubmit(AjaxRequestTarget target, Form<?> form){
                target.add(feedBack);
                if(pass.getNewPass().equals(pass.getConfirmPass())){
                    String login = ((SignInSession)this.getSession()).getLogin();
                    userService.changePass(login, pass.getNewPass());
                    setResponsePage(Home.class);
                    //feedBack.info((new ResourceModel("info.changed")).toString());
                }
                else{
                    feedBack.error((new ResourceModel("error.missmatch")).toString());
                }
            }
            protected void onError(AjaxRequestTarget target, Form<?> form){
                target.add(feedBack);
            }
        });
    }
    
}
