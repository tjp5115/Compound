import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';

import {NotFoundComponent} from "./not-found/not-found.component";
import {SearchStockComponent} from "./search-stock/search-stock.component";
import {CompoundStatsComponent} from './compound-stats/compound-stats.component';

const appRoutes: Routes = [
  { path: '',   redirectTo: '/search-stock', pathMatch: 'full' },
  { path: 'search-stock',      component: SearchStockComponent },
  { path: 'compound-stats', component: CompoundStatsComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes
      //{ enableTracing: true } // <-- debugging purposes only
    )
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {}
