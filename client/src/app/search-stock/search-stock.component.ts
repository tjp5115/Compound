import { Component, OnInit } from '@angular/core';
import { StockYearOverYearService } from '../shared';

@Component({
  selector: 'app-search-stock',
  templateUrl: './search-stock.component.html',
  styleUrls: ['./search-stock.component.css'],
  providers: [StockYearOverYearService]
})
export class SearchStockComponent implements OnInit {
  public lineChartData:Array<any>;
  public lineChartLabels:Array<any>;
  symbol : string;
  constructor(private stockService: StockYearOverYearService) { }

  ngOnInit() {
  }

  searchSymbol(){

    this.lineChartData = new Array;
    this.lineChartLabels = new Array();
    this.stockService.getSymbol(this.symbol).subscribe(
      stockData => {
        var chartData = new Map();
        stockData.forEach( stock =>{
          var weekNumber = parseInt(stock.date.week);
          var year = parseInt(stock.date.year);
          if ( chartData.has( year ) == false ){
            chartData.set(year, new Array(53));
          }
          chartData.get(year)[weekNumber-1] = stock.close;
        });

        chartData.forEach( (weeks, year) => {
          this.lineChartData.push({data : weeks, label : year, fill : false });
        });
        this.lineChartLabels = Array.from(new Array(52),(val,index)=>index+1);
      },
      error => console.log(error)
    )
  }

  // lineChart
  public lineChartOptions:any = {
    responsive: false,
  };

  public lineChartLegend:boolean = true;
  public lineChartType:string = 'line';

  // events
  public chartClicked(e:any):void {
    console.log(e);
  }

  public chartHovered(e:any):void {
    console.log(e);
  }

}
