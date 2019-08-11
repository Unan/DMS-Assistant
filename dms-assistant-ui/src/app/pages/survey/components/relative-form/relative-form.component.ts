import { Component, OnInit } from '@angular/core';
import {InsuranceTypes} from "../../../../shared/types/insurance.type";

@Component({
  selector: 'app-relative-form',
  templateUrl: './relative-form.component.html',
  styleUrls: ['./relative-form.component.scss']
})

export class RelativeFormComponent implements OnInit {
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

  public parseValue(value: string): void {
    this.chosenValue = InsuranceTypes[value];
  }

  public removeAdditionalPerson(): void {
  }
}
