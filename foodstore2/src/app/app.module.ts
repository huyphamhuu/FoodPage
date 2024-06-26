import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { RatingModule } from 'ng-starrating';
import { CommonModule } from '@angular/common';
import { StarRatingComponent } from './starrating/starrating.component';
import { SearchComponent } from './search/search.component';
import { FormsModule } from '@angular/forms';
import { FoodPageComponent } from './food-page/food-page.component';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    StarRatingComponent,
    SearchComponent,
    FoodPageComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
