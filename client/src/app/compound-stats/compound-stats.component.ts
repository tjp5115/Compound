import { Component, OnInit } from '@angular/core';
import { CompoundStatsService } from '../shared/stock/compound-stats.service';
@Component({
  selector: 'app-compound-stats',
  templateUrl: './compound-stats.component.html',
  styleUrls: ['./compound-stats.component.css'],
  providers: [CompoundStatsService]
})
export class CompoundStatsComponent implements OnInit {
  public statOptions = [ 'yearOverYear' ];
  public symbol : string;
  public anova : Number

  constructor(private statService: CompoundStatsService) { }

  ngOnInit() {
  }

  searchSymbol(){

    this.statService.getSymbol(this.symbol).subscribe(
      data => {
      	this.anova = data.oneWayAnova;
	   },
      error => console.log(error)
    )
  }

}
