import {InsuranceTypes} from '../types/insurance.type';

export const INSURANCE_TYPE_NAMES: any = {
  STANDARD: 'Basic',
  BUSINESS: 'Business',
  VIP: 'V.I.P.',
  VIP_CHILD: 'Child V.I.P.'
};

export const INSURANCE_TYPE_NAME_MAPPING: Map<InsuranceTypes, string> = new Map([
  [InsuranceTypes.STANDARD, INSURANCE_TYPE_NAMES.STANDARD],
  [InsuranceTypes.BUSINESS, INSURANCE_TYPE_NAMES.BUSINESS],
  [InsuranceTypes.VIP, INSURANCE_TYPE_NAMES.VIP],
  [InsuranceTypes.VIP_CHILD, INSURANCE_TYPE_NAMES.VIP_CHILD]
]);
