package tech.getarrays.employeemanager.resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.model.Food;
import tech.getarrays.employeemanager.service.FoodService;
import java.util.Optional;
import java.util.function.Function;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class EmployeeResource {
    private final FoodService employeeService;

    public EmployeeResource(FoodService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foods = employeeService.getAllFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable("id") Long id) {
        Optional<Food> food = employeeService.getFoodById(id);
        return food.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        Food newFood = employeeService.createFood(food);
        return new ResponseEntity<>(newFood, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Food> updateFood(@RequestBody Food food) {
        Food updatedFood = employeeService.updateFood(food);
        return new ResponseEntity<>(updatedFood, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable("id") Long id) {
        employeeService.deleteFood(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}