import { Component, OnInit } from '@angular/core';
import {InsuranceTypes} from "../../../../shared/types/insurance.type";

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.scss']
})

export class EmployeeFormComponent implements OnInit {
  public enumOptions: string[];
  public chosenValue: InsuranceTypes;
  public InsuranceTypes: typeof InsuranceTypes = InsuranceTypes;

  public constructor() {
  }

  public ngOnInit() {
    let enumOptions = Object.keys(InsuranceTypes);
    this.chosenValue = InsuranceTypes.STANDARD;
    this.enumOptions = enumOptions.slice(enumOptions.length / 2);
  }

  public parseValue(value: string){
    this.chosenValue = InsuranceTypes[value];
  }
}
