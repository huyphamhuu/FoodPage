import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from '../service/cart/cart.service';
 import { FoodService } from '../service/food/food.service';
import { Food } from '../shared/models/Food';

@Component({
  selector: 'app-food-page',
  templateUrl: './food-page.component.html',
  styleUrls: ['./food-page.component.css']
})
export class FoodPageComponent implements OnInit {

  food!: Food;
  constructor(private activatedRoute:ActivatedRoute,
    private foodService: FoodService,
     private cartService: CartService,
    private router: Router) { 
    activatedRoute.params.subscribe((params) => {
      const id = params['id'];
      if(id)
      foodService.getFoodById(id).subscribe(serverFood => {
        this.food = serverFood;
      });
    })

  }

  ngOnInit(): void {
  }

  addToCart(){
    // this.cartService.addToCart(this.food);
    this.router.navigateByUrl('/cart-page');
  }

}