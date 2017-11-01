import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { SearchStockComponent } from './search-stock/search-stock.component';
import { StockService } from './shared/stock/stock.service';
import { ChartsModule } from 'ng2-charts';
@NgModule({
  declarations: [
    AppComponent,
    SearchStockComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    ChartsModule
  ],
  providers: [StockService],
  bootstrap: [AppComponent]
})
export class AppModule { }
