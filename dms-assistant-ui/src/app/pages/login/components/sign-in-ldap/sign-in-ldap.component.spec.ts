import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignInLdapComponent } from './sign-in-ldap.component';

describe('SignInLdapComponent', () => {
  let component: SignInLdapComponent;
  let fixture: ComponentFixture<SignInLdapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignInLdapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignInLdapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
