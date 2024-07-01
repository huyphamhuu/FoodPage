package tech.getarrays.employeemanager.resource;
import tech.getarrays.employeemanager.model.JWTRequest;

import tech.getarrays.employeemanager.model.JWTResponse;
import tech.getarrays.employeemanager.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JWTResource {

    @Autowired
    private JWTService jwtService;

    @PostMapping({"/authenticate"})
    public JWTResponse createJwtToken(@RequestBody JWTRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}