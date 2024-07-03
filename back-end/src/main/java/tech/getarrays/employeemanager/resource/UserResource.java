package tech.getarrays.employeemanager.resource;
import tech.getarrays.employeemanager.model.Users;
import tech.getarrays.employeemanager.model.ConfirmationToken;
import tech.getarrays.employeemanager.service.UserService;
import tech.getarrays.employeemanager.event.RegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.context.ApplicationEventPublisher;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@RestController

public class UserResource {

    @Autowired
    private UserService userService;
    @Autowired
    private  ApplicationEventPublisher publisher;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/api/registerNewUser"})
    public ResponseEntity<?> registerNewUser(@RequestBody Users user,final HttpServletRequest request) {
        try {
            //Users newUser = userService.registerNewUser(user);
            ConfirmationToken confirmationToken = userService.registerNewUser(user);
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request),confirmationToken));

            // ResponseEntity.ok(confirmationToken);
            return ResponseEntity.ok(new RegistrationCompleteEvent(user, applicationUrl(request),confirmationToken));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping({"/api/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/api/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}

