package tech.getarrays.employeemanager.repo;
import org.springframework.data.repository.CrudRepository;
import tech.getarrays.employeemanager.model.Users;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserDao extends CrudRepository<Users,String>{
    Optional<Users> findByUserName(String userName);
    @Transactional
    @Modifying
    @Query("UPDATE Users a " +
            "SET a.enabled = TRUE WHERE a.userName = ?1")
    int enableAppUser(String email);

}
