import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignInGoogleComponent } from './sign-in-google.component';

describe('SignInGoogleComponent', () => {
  let component: SignInGoogleComponent;
  let fixture: ComponentFixture<SignInGoogleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignInGoogleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignInGoogleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
