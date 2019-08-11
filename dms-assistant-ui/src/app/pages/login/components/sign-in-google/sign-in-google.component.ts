import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-sign-in-google',
  templateUrl: './sign-in-google.component.html',
  styleUrls: ['./sign-in-google.component.scss']
})
export class SignInGoogleComponent {

  @Output() changed = new EventEmitter<string>();

  constructor() {
  }

  ngOnInit(): void {
  }

  public signInEmit(): void {
    this.changed.emit();
  }
}
