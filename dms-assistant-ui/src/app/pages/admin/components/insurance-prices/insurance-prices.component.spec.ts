import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsurancePricesComponent } from './insurance-prices.component';

describe('InsurancePricesComponent', () => {
  let component: InsurancePricesComponent;
  let fixture: ComponentFixture<InsurancePricesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsurancePricesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsurancePricesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
