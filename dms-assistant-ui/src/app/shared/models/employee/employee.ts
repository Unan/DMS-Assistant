import { Relative } from '../relative/relative';
import { Insurance } from '../insurance/insurance';

export class Employee {

  public fullName: string;

  public email: string;

  public birthDate: string;

  public address: string;

  public phoneNumber: string;

  public insurance: Insurance;

  public hireDate: Date;

  public role: string;

  public relatives: Relative[];
}


