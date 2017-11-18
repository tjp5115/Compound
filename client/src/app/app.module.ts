import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { SearchStockComponent } from './search-stock/search-stock.component';
import { StockYearOverYearService } from './shared/stock/stock.year.over.year.service';
import { ChartsModule } from 'ng2-charts';
import {NotFoundComponent} from "./not-found/not-found.component";
import {AppRoutingModule} from "./app-routing.module";
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    AppComponent,
    SearchStockComponent,
    NotFoundComponent

  ],
  imports: [
    BrowserModule,
    HttpModule,
    ChartsModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [StockYearOverYearService],
  bootstrap: [AppComponent]
})


export class AppModule { }
