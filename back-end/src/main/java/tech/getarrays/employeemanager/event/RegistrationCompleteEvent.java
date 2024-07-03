package tech.getarrays.employeemanager.event;


import tech.getarrays.employeemanager.model.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import tech.getarrays.employeemanager.model.ConfirmationToken;
/**
 * @author Sampson Alfred
 */

public class RegistrationCompleteEvent extends ApplicationEvent {
    private Users user;
    private String applicationUrl;
    private ConfirmationToken Token;
    public RegistrationCompleteEvent(Users user, String applicationUrl,ConfirmationToken Token) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
        this.Token = Token;
    }

    public ConfirmationToken getToken() {
        return Token;
    }

    public void setToken(ConfirmationToken token) {
        Token = token;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
}
