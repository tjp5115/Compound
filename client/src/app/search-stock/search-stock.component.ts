import { Component, OnInit } from '@angular/core';
import { StockService } from '../shared'

@Component({
  selector: 'app-search-stock',
  templateUrl: './search-stock.component.html',
  styleUrls: ['./search-stock.component.css'],
  providers: [StockService]
})
export class SearchStockComponent implements OnInit {

  stock: Array<any>;

  constructor(private stockService: StockService) { }

  ngOnInit() {
    this.stockService.getAll().subscribe(
      data => {
        this.stock = data;
      },
      error => console.log(error)
    )
  }
}
