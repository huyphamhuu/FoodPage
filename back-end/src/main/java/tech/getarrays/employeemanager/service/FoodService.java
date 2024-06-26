package tech.getarrays.employeemanager.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.exception.UserNotFoundException;
import tech.getarrays.employeemanager.model.Food;
import tech.getarrays.employeemanager.repo.FoodRepo;
import java.util.Optional;
import java.util.function.Function;

import java.util.List;
@Service
@Transactional
public class FoodService {
    private final FoodRepo foodRepo;

    @Autowired
    public FoodService(FoodRepo foodRepo) {
        this.foodRepo = foodRepo;
    }



    public Food createFood(Food food) {
        return foodRepo.save(food);
    }

    public List<Food> getAllFoods() {
        return foodRepo.findAll();
    }

    public Food updateFood(Food food) {
        return foodRepo.save(food);
    }

    public Optional<Food> getFoodById(Long id) {
        return foodRepo.findById(id);
    }

    public void deleteFood(Long id) {
        foodRepo.deleteById(id);
    }
}
