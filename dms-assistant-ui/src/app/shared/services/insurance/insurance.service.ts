import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {TransportService} from '../transport.service';
import {ENDPOINTS} from '../../constants/endpoints';
import {Employee} from "../../models/employee/employee";


@Injectable({
  providedIn: 'root'
})
export class InsuranceService {

  constructor(private transport: TransportService) {
  }

  public getAll(): Observable<any> {
    const url = `${ENDPOINTS.SERVICE_PREFIX}/admin/employees`;
    return this.transport.get({url});
  }

  public getById(): Observable<any> {
    const url = `${ENDPOINTS.SERVICE_PREFIX}/user/survey`;
    return this.transport.get({url});
  }

  public edit(employee: any, email: string): Observable<Employee> {
    let employeeObject = this.createEmployeeJSON(employee, email);
    const url = `${ENDPOINTS.SERVICE_PREFIX}/user/save`;
    const body = JSON.stringify(employeeObject);

    return this.transport.put({url, body});
  }

  private createEmployeeJSON(survey: any, email): object {
    let relativesArr: Array<object> = [];
    let jsonObj: object = {
      email,
      fullName: `${survey.familyName} ${survey.firstName} ${survey.secondName}`,
      birthDate: survey.birthDate,
      address: survey.address,
      phoneNumber: survey.phoneNumber,
      insurance: {
        insuranceType: survey.insuranceType
      },
      hireDate: survey.hireDate,
      role: survey.role,
      relatives: relativesArr
    };

    for (let i = 0; i < survey.relatives.length; i++) {
      relativesArr.push(
        {
          id: survey.relatives[i].id,
          fullName: `${survey.relatives[i].familyName} ${survey.relatives[i].firstName} ${survey.relatives[i].secondName || ''}`,
          birthDate: survey.relatives[i].birthDate,
          address: survey.relatives[i].address,
          phoneNumber: survey.relatives[i].phoneNumber,
          insurance: {
            insuranceType: survey.relatives[i].insuranceType
          }
        }
      );
    }
    return jsonObj;
  }
}
