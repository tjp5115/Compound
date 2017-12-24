import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompoundStatsComponent } from './compound-stats.component';

describe('CompoundStatsComponent', () => {
  let component: CompoundStatsComponent;
  let fixture: ComponentFixture<CompoundStatsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompoundStatsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompoundStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
