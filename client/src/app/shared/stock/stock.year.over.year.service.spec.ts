import { TestBed, inject } from '@angular/core/testing';

import { StockYearOverYearService } from './stock.year.over.year.service';

describe('StockYearOverYearService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StockYearOverYearService]
    });
  });

  it('should be created', inject([StockYearOverYearService], (service: StockYearOverYearService) => {
    expect(service).toBeTruthy();
  }));
});
