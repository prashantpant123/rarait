import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

import {routerTransition} from '../../../../router.animations';
import {PasswordValidator} from '../../../../shared/validators/password.validator';
import {AppRegex} from '../../../../shared/constants/app-regex';
import {AdminUserService} from '../../core/service/admin-user.service';
import {UserCreateModel} from '../../core/model/user-create.model';
import {AdminURL} from '../../../../shared/constants/admin-url';

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  animations: [routerTransition()]
})
export class AdminUserComponent implements OnInit {

  userAddForm: FormGroup;
  submitted = false;
  error = '';

  constructor(private formBuilder: FormBuilder,
              private userService: AdminUserService,
              private router: Router) {
  }

  ngOnInit() {
    this.userAddForm = this.formBuilder.group({

      adminUsername: ['', [Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30)
        // Validators.pattern(this.usernamePattern)
      ]],
      adminUserPassword: ['', [
        Validators.required
        // Validators.pattern(AppRegex.MEDIUM_PASSWORD)
      ]
      ],
      adminUserConfirmPassword: ['', Validators.required]
    }, {
      validators: PasswordValidator('adminUserPassword', 'adminUserConfirmPassword')
    });
  }

  get f() {
    return this.userAddForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.userAddForm.invalid) {
      // this.submitted = false;
      return;
    }

    const userModel = new UserCreateModel(this.userAddForm.value.adminUsername, this.userAddForm.value.adminUserPassword);
    this.userService.addAdminUser(userModel)
      .subscribe(data => {
          this.router.navigateByUrl(AdminURL.PROFILE_USER);
        },
        error => {
          this.error = error;
        });
  }

  onCancel() {
    this.userAddForm.reset();
  }
}
