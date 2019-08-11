import {Pipe, PipeTransform} from '@angular/core';
import {INSURANCE_TYPE_NAME_MAPPING} from "./constants";
import {InsuranceTypes} from '../types/insurance.type'

@Pipe({
  name: 'insuranceType'
})
export class InsuranceTypePipe implements PipeTransform {

  transform(name: string): string {
    return INSURANCE_TYPE_NAME_MAPPING.get(InsuranceTypes[name]);
  }
}
