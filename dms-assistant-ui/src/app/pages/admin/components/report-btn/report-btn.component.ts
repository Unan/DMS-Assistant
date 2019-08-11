import { Component, Inject, OnInit } from '@angular/core';
import { AdminService } from 'src/app/shared/services/admin/admin.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material';
import { PopUpComponent } from '../pop-up/pop-up.component';

@Component({
  selector: 'app-report-btn',
  templateUrl: './report-btn.component.html',
  styleUrls: ['./report-btn.component.scss']
})

export class ReportBtnComponent {

  public isUnfilledProcessed: boolean = false;
  public isAllProcessed: boolean = false;

  constructor(private adminService: AdminService,
              public dialog: MatDialog) { }

  public notifyUnfilled(): void {
    this.isUnfilledProcessed = true;
    this.adminService.notifyUnfilled().subscribe(
      () => {
        this.isUnfilledProcessed = false;
        this.dialog.open(PopUpComponent, {
          data: {
            message: "Notifications has been sent to unfilled users"
          }
        })
      },
      error => { 
        this.isUnfilledProcessed = false;
        throw error;
      }
    );
  }

  public notifyAll(): void {
    this.isAllProcessed = true;
    this.adminService.notifyAll().subscribe(
      () => {
        this.isAllProcessed = false;
        this.dialog.open(PopUpComponent, {
          data: {
            message: "Notifications has been sent to all users"
          }
        })
      },
      error => {
        this.isAllProcessed = false;
        throw error;
      }
    );
  }
}
