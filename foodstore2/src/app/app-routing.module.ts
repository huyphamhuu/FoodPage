import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { RatingModule } from 'ng-starrating';
import { ActivatedRoute } from '@angular/router';
import {FoodPageComponent} from './food-page/food-page.component';
const routes: Routes = [
  {path: 'header', component: HeaderComponent},
  {path: '', component: HomeComponent},
  {path: 'search/:searchTerm', component: HomeComponent},
  {path: 'food/:id', component: FoodPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
