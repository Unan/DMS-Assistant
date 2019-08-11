import { Injectable } from '@angular/core';
import { TransportService } from '../transport.service';
import { HttpHeaders } from '@angular/common/http';
import { ENDPOINTS } from '../../constants/endpoints';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { Employee } from '../../models/employee/employee';
import { InsuranceService } from '../insurance/insurance.service';
import { InsuranceTypes } from '../../types/insurance.type';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  public insurances: Observable<Employee[]>;
  public authToken: string = localStorage.getItem('token');

  private _insurances: BehaviorSubject<Employee[]>;
  private dataStore: {
    insurances: Employee[]
  };

  constructor(private insuranceService: InsuranceService,
              private transportService: TransportService) {
    this.dataStore = { insurances: [] };
    this._insurances = <BehaviorSubject<Employee[]>>new BehaviorSubject([]);
    this.insurances = this._insurances.asObservable();
  }

  public getAllInsurances(): void {
    this.insuranceService.getAll().subscribe((data: Employee[]) => {
      this.dataStore.insurances = data;
      this._insurances.next(Object.assign({}, this.dataStore).insurances);
    }, error => {
      console.log('Could not load insurances');
      throw error;
    });
  }

  public getFilledInsurances(): void {
    const filledInsurances = this.dataStore.insurances
      .filter(i => i.insurance.insuranceType !== InsuranceTypes.NONE);
    this._insurances.next(filledInsurances)
  }

  public getByFilter(insuranceType: InsuranceTypes): void {
    const filteredInsurances = this.dataStore.insurances
      .filter(i => i.insurance.insuranceType === insuranceType);
    this._insurances.next(filteredInsurances)
  }

  public notifyUnfilled(): Observable<any> {
    const url = `${ENDPOINTS.SERVICE_PREFIX}/admin/remindEmployeesWithoutInsurance`;
    return this.transportService.get({url});
  }

  public notifyAll(): Observable<any> {
    const url = `${ENDPOINTS.SERVICE_PREFIX}/admin/notifyAllEmployees`;
    return this.transportService.get({url});
  }
}
