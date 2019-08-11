import {Component} from '@angular/core';
import {LoginService} from '../../shared/services/login/login.service';
import {Router} from '@angular/router';
import {GoogleLoginProvider, SocialUser, AuthService} from "angularx-social-login";
import {Subscription} from "rxjs";
import {InsuranceService} from "../../shared/services/insurance/insurance.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  public subscriptions: Subscription[] = [];

  public errorMessage: string;
  public isLdap: boolean = false;
  private user: SocialUser;
  private loggedIn: boolean;

  constructor(private loginService: LoginService,
              private router: Router,
              private  authService: AuthService,
              private insuranceService: InsuranceService,
  ) {
  }

  public toggleAuthType(): void {
    this.isLdap = !this.isLdap;
  }

  public ngOnInit(): void {
  }

  public signInLdap(ldap: string) {
    this.errorMessage = null;
    const checkLoginSub = this.loginService.checkLogin(ldap)
      .subscribe(() => {
        const getByIDSub = this.insuranceService.getById().subscribe(data => {
          if (data.internalRole === 'ADMIN') {
            this.loginService.isAdmin = true;
            this.router.navigate(['/admin']);
          } else {
            this.router.navigate(['/survey']);
          }
        });
        this.subscriptions.push(getByIDSub);
      }, () => {
        this.errorMessage = 'Incorrect login! Please, try again';
      });
    this.subscriptions.push(checkLoginSub);
  }

  public onChangedGoogle(): void {
    this.errorMessage = null;
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
    const authStateSub = this.authService.authState.subscribe((user) => {
      this.user = user;
      this.loggedIn = (user !== null);
      if (this.loggedIn) {
        const tokenSub = this.loginService.sendToken(user.authToken).subscribe(() => {
          const getByIDSub = this.insuranceService.getById().subscribe(data => {
            if (data.internalRole === 'ADMIN') {
              this.loginService.isAdmin = true;
              this.router.navigate(['/admin']);
            } else {
              this.router.navigate(['/survey']);
            }
          });
          this.subscriptions.push(getByIDSub);
        });
        this.subscriptions.push(tokenSub);
      }
    }, () => {
      this.errorMessage = 'Please, use corporate account';
    });
    this.subscriptions.push(authStateSub)
  }

  public onChangedLdap(ldap: string): void {
    this.signInLdap(ldap);
  }

  public ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub && sub.unsubscribe());
  }
}
