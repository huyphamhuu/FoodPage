package tech.getarrays.employeemanager.service;

import lombok.AllArgsConstructor;
import tech.getarrays.employeemanager.repo.RoleDao;
import tech.getarrays.employeemanager.repo.UserDao;
import tech.getarrays.employeemanager.model.Role;
import tech.getarrays.employeemanager.model.Users;
import tech.getarrays.employeemanager.resource.UserResource;
import tech.getarrays.employeemanager.service.ConfirmationTokenService;
import tech.getarrays.employeemanager.model.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Optional;
import java.util.UUID;
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private  ConfirmationTokenService confirmationTokenService;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);
        Users adminUser = new Users();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }
    @Transactional
    public String confirmToken(String token){
        System.out.println(token);
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));
        if(confirmationToken.getConfirmedAt()!=null){
            throw new IllegalStateException("email already confirmed");

        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        enableAppUser(
                confirmationToken.getAppUser().getUserName());
        return "confirmed";
    }
    public ConfirmationToken registerNewUser(Users user) {
        Optional<Users> existingUser = userDao.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
           throw new RuntimeException("Email already registered");
        }

        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        String token = UUID.randomUUID().toString();
        userDao.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        //return userDao.save(user);

        return confirmationToken;
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    public int enableAppUser(String email){
        return userDao.enableAppUser(email);
    }

}
