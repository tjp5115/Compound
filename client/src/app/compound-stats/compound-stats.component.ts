import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-compound-stats',
  templateUrl: './compound-stats.component.html',
  styleUrls: ['./compound-stats.component.css']
})
export class CompoundStatsComponent implements OnInit {
  public statOptions = [ 'yearOverYear' ];
  constructor() { }

  ngOnInit() {
  }

}
