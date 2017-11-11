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
  public json : any;
  constructor(private stockService: StockYearOverYearService) { }

  ngOnInit() {
    this.stockService.getAll("MSFT").subscribe(
      stockDatadata => {
        this.json = stockDatadata.Year;
        Object.keys(this.json).forEach( year =>{
          var chartData : Array<number> = new Array(52);
          var yearData = this.json[year].WeekOfYearToStock;
          Object.keys(yearData).forEach( weekNumber =>{
            chartData[parseInt(weekNumber)-1] = yearData[weekNumber].value;
          });
          this.lineChartData.push({data : chartData, label : year, fill : false });
        });
        this.lineChartLabels = Array.from(new Array(52),(val,index)=>index+1);
        this.json = JSON.stringify(this.json);
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
