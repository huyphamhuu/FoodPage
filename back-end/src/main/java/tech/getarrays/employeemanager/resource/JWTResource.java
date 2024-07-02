package tech.getarrays.employeemanager.resource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.model.JWTRequest;

import tech.getarrays.employeemanager.model.JWTResponse;
import tech.getarrays.employeemanager.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin
public class JWTResource {

    @Autowired
    private JWTService jwtService;

    @PostMapping({"/api/authenticate"})
    public JWTResponse createJwtToken(@RequestBody JWTRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
//    @PostMapping("/user")
//    public String createUser() {
//        // Xử lý logic tạo user ở đây
//        // Ví dụ: trả về một thông báo thành công
//        return "User has been created successfully!";
//    }
    @GetMapping("/user")
    public String createUser(@AuthenticationPrincipal OAuth2User user, Model model){
        model.addAttribute("body", user.getAttribute("name"));
        return "response";
    }

}