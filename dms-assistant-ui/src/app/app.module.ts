import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { SocialLoginModule, AuthServiceConfig } from "angularx-social-login";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { MatButtonModule, MatCheckboxModule } from '@angular/material';
import { MatOptionModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { MatDatepickerModule } from '@angular/material';
import { MatPaginatorModule, MatProgressSpinnerModule,
         MatSortModule, MatTableModule } from '@angular/material';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';

import { LoginComponent } from './pages/login/login.component';
import { SurveyComponent } from './pages/survey/survey.component';
import { AdminComponent } from './pages/admin/admin.component';
import { SignInGoogleComponent } from './pages/login/components/sign-in-google/sign-in-google.component';
import { SignInLdapComponent } from './pages/login/components/sign-in-ldap/sign-in-ldap.component';
import { EmployeeFormComponent } from './pages/survey/components/employee-form/employee-form.component';
import { RelativeFormComponent } from './pages/survey/components/relative-form/relative-form.component';
import { EmployeeTableComponent } from './pages/admin/components/employee-table/employee-table.component';
import { ReportBtnComponent } from './pages/admin/components/report-btn/report-btn.component';
import { HttpClientModule } from '@angular/common/http';
import {InsuranceTypePipe} from './shared/pipes/insurance-type.pipe';
import { InsurancePricesComponent } from './pages/admin/components/insurance-prices/insurance-prices.component';
import {provideConfig} from "./auth.config";
import { PopUpComponent } from './pages/admin/components/pop-up/pop-up.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SurveyComponent,
    AdminComponent,
    SignInGoogleComponent,
    SignInLdapComponent,
    EmployeeFormComponent,
    RelativeFormComponent,
    EmployeeTableComponent,
    ReportBtnComponent,
    InsuranceTypePipe,
    InsurancePricesComponent,
    PopUpComponent
  ],
  entryComponents: [
    PopUpComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    HttpClientModule,
    MatOptionModule,
    MatSelectModule,
    MatIconModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    MatDatepickerModule,
    FormsModule,
    SocialLoginModule
  ],
  exports: [
    MatButtonModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule
  ],
  providers: [
    {
      provide: AuthServiceConfig,
      useFactory: provideConfig
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
