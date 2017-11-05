import { Component, OnInit } from '@angular/core';
import { StockYearOverYearService } from '../shared';

@Component({
  selector: 'app-search-stock',
  templateUrl: './search-stock.component.html',
  styleUrls: ['./search-stock.component.css'],
  providers: [StockYearOverYearService]
})
export class SearchStockComponent implements OnInit {
  public lineChartData:Array<any> = new Array;
  public lineChartLabels:Array<any> = new Array;

  constructor(private stockService: StockYearOverYearService) { }

  ngOnInit() {
    this.stockService.getAll("MSFT").subscribe(
      stockDatadata => {
        var json = stockDatadata.dateStock;
        var chartData : Array<any> = new Array;
        Object.keys(json).forEach( key =>{
          this.lineChartLabels.push(key);
          chartData.push(json[key].value);
        });
        this.lineChartData.push({data : chartData, label : 'Stock' });

      },
      error => console.log(error)
    )
  }



  // lineChart
  public lineChartOptions:any = {
    responsive: false
  };
  public lineChartColors:Array<any> = [
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // dark grey
      backgroundColor: 'rgba(77,83,96,0.2)',
      borderColor: 'rgba(77,83,96,1)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    },
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
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
