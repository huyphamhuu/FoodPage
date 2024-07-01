package tech.getarrays.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.getarrays.employeemanager.model.Food;

import java.util.Optional;

@Repository
public interface FoodRepo extends JpaRepository<Food, Long> {
    void deleteEmployeeById(Long id);

    Optional<Food> findById(Long id);

}
