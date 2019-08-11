import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {AdminComponent} from "./pages/admin/admin.component";
import {SurveyComponent} from "./pages/survey/survey.component";
import {LoginComponent} from './pages/login/login.component';
import {AuthGuard} from "./auth.guard";
import {LoggedGuard} from "./logged.guard";
import {AdminGuard} from "./admin.guard";

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [LoggedGuard]
  },
  {
    path: 'survey',
    component: SurveyComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard, AdminGuard]
  },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
