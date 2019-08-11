import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-sign-in-ldap',
  templateUrl: './sign-in-ldap.component.html',
  styleUrls: ['./sign-in-ldap.component.scss']
})
export class SignInLdapComponent {

  @Output() changed = new EventEmitter<string>();
  public ldap: string;

  constructor() { }

  public signInEmit() {
    this.changed.emit(this.ldap);
  }
}
