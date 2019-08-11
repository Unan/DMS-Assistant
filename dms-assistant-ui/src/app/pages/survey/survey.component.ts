import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {InsuranceService} from '../../shared/services/insurance/insurance.service';
import {InsuranceTypes} from '../../shared/types/insurance.type';
import {AuthService} from "angularx-social-login";
import {Subscription} from "rxjs";


@Component({
  selector: 'app-survey',
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.scss']
})
export class SurveyComponent implements OnInit {
  public subscriptions: Subscription[] = [];

  public enumOptions: string[];

  email: string = '';

  maxDate = new Date(Date.now());

  public survey = this.fb.group({
    familyName: [''],
    firstName: [''],
    secondName: [''],
    birthDate: [''],
    phoneNumber: [''],
    address: [''],
    role: [''],
    hireDate: [''],
    insuranceType: [InsuranceTypes['STANDARD'], Validators.required],
    relatives: this.fb.array([])
  });

  public constructor(private  authService: AuthService,
                     private fb: FormBuilder,
                     private insuranceService: InsuranceService,
  ) {
  }

  public ngOnInit() {
    this.email = '';
    const getByIdSub = this.insuranceService.getById().subscribe(data => {
      this.email = data.email || '';
      this.surveyParser(data);
    });
    this.subscriptions.push(getByIdSub);
    let enumOptions = Object.keys(InsuranceTypes);
    this.enumOptions = enumOptions.slice(1);
  }

  private surveyParser(personData: any): void {
    const fullNameArr: string[] = this.nameFromBackendParser(personData.fullName);
    this.survey.controls.familyName.setValue(fullNameArr[0]);
    this.survey.controls.firstName.setValue(fullNameArr[1]);
    this.survey.controls.secondName.setValue(fullNameArr[2]);
    this.survey.controls.birthDate.setValue(new Date(personData.birthDate));
    this.survey.controls.phoneNumber.setValue(personData.phoneNumber);
    this.survey.controls.address.setValue(personData.address);
    this.survey.controls.role.setValue(personData.role);
    this.survey.controls.hireDate.setValue(new Date(personData.hireDate));
    this.survey.controls.insuranceType.setValue(personData.insurance.insuranceType);
    if (personData.insurance.insuranceType === 'NONE') {
      this.survey.controls.insuranceType.setValue(InsuranceTypes['STANDARD']);
    }

    for (let i = 0; i < personData.relatives.length; i++) {
      this.relatives.push(this.relativeParser(personData.relatives[i]));
    }
  }

  public relativeParser(relative: any): FormGroup {
    const newRelative = this.createNewPersonForm();
    newRelative.controls.id.setValue(relative.id);
    const fullNameArr: string[] = this.nameFromBackendParser(relative.fullName);
    newRelative.controls.familyName.setValue(fullNameArr[0]);
    newRelative.controls.firstName.setValue(fullNameArr[1]);
    newRelative.controls.secondName.setValue(fullNameArr[2]);
    newRelative.controls.birthDate.setValue(new Date(relative.birthDate));
    newRelative.controls.phoneNumber.setValue(relative.phoneNumber);
    newRelative.controls.address.setValue(relative.address);
    newRelative.controls.insuranceType.setValue(relative.insurance.insuranceType);
    if (relative.insurance.insuranceType === 'NONE') {
      newRelative.controls.insuranceType.setValue(InsuranceTypes['STANDARD']);
    }
    return newRelative;
  }

  public nameFromBackendParser(fullName): string[] {
    return fullName.split(' ');
  }

  public createNewPersonForm(): FormGroup {
    return this.fb.group({
      id: [],
      familyName: ['', Validators.required],
      firstName: ['', Validators.required],
      secondName: [''],
      birthDate: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      address: ['', Validators.required],
      insuranceType: [InsuranceTypes['STANDARD'], Validators.required],
    });
  }

  public addAdditionalPerson(): void {
    this.relatives.push(this.createNewPersonForm());
  }

  get relatives() {
    return this.survey.get('relatives') as FormArray;
  }

  public removeAdditionalPerson(personIndex: number): void {
    this.relatives.value.splice(personIndex, 1);
    this.relatives.controls.splice(personIndex, 1);
  }

  public onSubmit(): void {
    let editSub;
    if (this.survey.valid) {
      editSub = this.insuranceService.edit(this.survey.value, this.email).subscribe();
    }
    this.subscriptions.push(editSub);
  }

  public ngOnDestroy() {
    this.subscriptions.forEach(sub => sub && sub.unsubscribe());
  }
}
