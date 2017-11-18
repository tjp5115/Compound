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
        var json  = stockData.Year;
        Object.keys(json).forEach( year =>{
          var chartData : Array<number> = new Array(52);
          var yearData = json[year].WeekOfYearToStock;
          Object.keys(yearData).forEach( weekNumber =>{
            chartData[parseInt(weekNumber)-1] = yearData[weekNumber].value;
          });
          this.lineChartData.push({data : chartData, label : year, fill : false });
          this.lineChartLabels = Array.from(new Array(52),(val,index)=>index+1);
        });
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
