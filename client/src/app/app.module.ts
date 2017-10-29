import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { SearchStockComponent } from './search-stock/search-stock.component';
import { StockService } from './shared/stock/stock.service';

@NgModule({
  declarations: [
    AppComponent,
    SearchStockComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [StockService],
  bootstrap: [AppComponent]
})
export class AppModule { }
