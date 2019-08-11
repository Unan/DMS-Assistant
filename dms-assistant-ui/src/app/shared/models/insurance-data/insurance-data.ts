import { Rate } from '../rate/rate';

export class InsuranceData {
    public standardAmount: number;
    public businessAmount: number;
    public vipAmount: number;
    public vipChildAmount: number;
    public rates: Rate[];
}
