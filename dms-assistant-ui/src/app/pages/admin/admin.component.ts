import { Component, EventEmitter, Output, Input } from '@angular/core';
import { AdminService } from 'src/app/shared/services/admin/admin.service';
import {Observable, Subscription} from 'rxjs';
import { map } from 'rxjs/operators'
import { Employee } from 'src/app/shared/models/employee/employee';
import {InsuranceService} from "../../shared/services/insurance/insurance.service";
import {LoginService} from "../../shared/services/login/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent {

  public selectedValue: string;

  public conditions: Condition[] = [
    {value: 'all', viewValue: 'All'},
    {value: 'filled', viewValue: 'Filled'},
    {value: 'not-filled', viewValue: 'Not filled'}
  ];

  public subscriptions: Subscription[] = [];

  constructor(private adminService: AdminService,
              private insuranceService: InsuranceService,
              private loginService: LoginService,
              private router: Router
  ) {
  }

  public redirectToSurvey() {
    const getByIDSub = this.insuranceService.getById().subscribe(data => {
      this.router.navigate(['/survey']);
    });
    this.subscriptions.push(getByIDSub);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub && sub.unsubscribe());
  }
}
export interface Condition {
  value: string;
  viewValue: string;
}

