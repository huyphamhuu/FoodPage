package tech.getarrays.employeemanager.repo;
import org.springframework.data.repository.CrudRepository;
import tech.getarrays.employeemanager.model.Users;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDao extends CrudRepository<Users,String>{
}
