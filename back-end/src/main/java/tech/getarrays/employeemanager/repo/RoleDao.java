package tech.getarrays.employeemanager.repo;
import org.springframework.data.repository.CrudRepository;
import tech.getarrays.employeemanager.model.Role;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleDao extends CrudRepository<Role,String>{
}
