import { TestBed, inject } from '@angular/core/testing';

import { CompoundStatsService } from './compound-stats.service';

describe('CompoundStatsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CompoundStatsService]
    });
  });

  it('should be created', inject([CompoundStatsService], (service: CompoundStatsService) => {
    expect(service).toBeTruthy();
  }));
});
