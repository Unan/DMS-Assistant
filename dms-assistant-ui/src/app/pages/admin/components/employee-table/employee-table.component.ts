import { Component, OnInit, ViewChild, OnDestroy, Input, OnChanges, SimpleChanges } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Employee } from '../../../../shared/models/employee/employee';
import { DISPLAYED_COLUMNS } from '../../../../shared/constants/displayed-columns';
import { InsuranceTypes } from '../../../../shared/types/insurance.type';
import { Observable, Subscription } from 'rxjs';
import { AdminService } from 'src/app/shared/services/admin/admin.service';

@Component({
  selector: 'app-employee-table',
  templateUrl: './employee-table.component.html',
  styleUrls: ['./employee-table.component.scss']
})
export class EmployeeTableComponent implements OnInit, OnDestroy, OnChanges {
  @Input() selectedValue: string;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  public insurances: Observable<Employee[]>;
  public displayedColumns = DISPLAYED_COLUMNS;
  public dataSource: MatTableDataSource<Employee>;
  private subscriptions: Subscription[] = [];

  constructor(private adminService: AdminService) {}

  public ngOnInit() {
    this.insurances = this.adminService.insurances;
    this.adminService.getAllInsurances();
    const insSub = this.insurances.subscribe(ins => {
      this.dataSource = new MatTableDataSource<Employee>(ins);
      this.dataSource.paginator = this.paginator;
    });
    this.subscriptions.push(insSub);
  }

  public ngOnChanges(changes: SimpleChanges) {
    if (changes.selectedValue) {
      switch (this.selectedValue) {
        case 'all':
          this.adminService.getAllInsurances();
          break;
        case 'filled':
          this.adminService.getFilledInsurances();
          break;
        case 'not-filled':
          this.adminService.getByFilter(InsuranceTypes.NONE);
          break;
      }
    }
  }

  public ngOnDestroy() {
    this.subscriptions.forEach(s => s && s.unsubscribe());
  }
}
