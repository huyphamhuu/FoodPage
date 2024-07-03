package tech.getarrays.employeemanager.event.listener;

import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import tech.getarrays.employeemanager.event.RegistrationCompleteEvent;
import tech.getarrays.employeemanager.model.Users;
import tech.getarrays.employeemanager.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author Sampson Alfred
 */
@Slf4j
@Component

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private  UserService userService;
    @Autowired
    private  JavaMailSender mailSender;
    private  Users theUser;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. Get the newly registered user
        theUser = event.getUser();
        String verifyToken = event.getToken().getToken();
        //2. Create a verification token for the user
        //String verificationToken = UUID.randomUUID().toString();
        //3. Save the verification token for the user
        //userService.saveUserVerificationToken(theUser, verificationToken);
        //4 Build the verification url to be sent to the user
        String url = event.getApplicationUrl()+"/confirm?token="+verifyToken;
        //5. Send the email.
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        //log.info("Click the link to verify your registration :  {}", url);
    }
    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p> Hi, "+ theUser.getUserName()+ ", </p>"+
                "<p>Thank you for registering with us,"+"" +
                "Please, follow the link below to complete your registration.</p>"+
                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                "<p> Thank you <br> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("dailycodework@gmail.com", senderName);
        messageHelper.setTo(theUser.getUserName());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
