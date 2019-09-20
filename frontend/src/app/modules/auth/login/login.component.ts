import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import {routerTransition} from '../../../router.animations';
import {AuthenticationService} from '../../../shared/services/authentication.service';
import {Role} from '../../../shared/model/role';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [routerTransition()]
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;
  returnUrl: string;
  error = '';

  constructor(
    private translate: TranslateService,
    public router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService
  ) {
    this.translate.addLangs(['en', 'np']);
    this.translate.setDefaultLang('en');
    const browserLang = this.translate.getBrowserLang();
    this.translate.use(browserLang.match(/en|fr|ur|es|it|fa|de|zh-CHS/) ? browserLang : 'en');

    if (this.authenticationService.currentUserValue()) {
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
    this.onCancel();

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    // get return url from route parameters or default to '/'
    // console.log('return url: ' + this.returnUrl);
    // this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }
    this.authenticationService.login(this.f.username.value, this.f.password.value)
      .subscribe(data => {
          if (data.role === Role.ADMIN) {
            this.router.navigate(['/admin']);
          } else if (data.role === Role.SCHOOL) {
            this.router.navigate(['/client']);
          }
          // this.router.navigate([this.returnUrl]);
        },
        error => {
          this.error = error;
        });
    this.onCancel();
  }

  onCancel() {
    this.submitted = false;
  }
}
