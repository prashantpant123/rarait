import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import {routerTransition} from '../../../router.animations';

@Component({
  selector: 'app-reset-login',
  templateUrl: './reset-login.component.html',
  styleUrls: ['./reset-login.component.scss'],
  animations: [routerTransition()]
})
export class ResetLoginComponent implements OnInit {

  recoverLoginForm: FormGroup;
  submitted = false;

  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit() {
    this.recoverLoginForm = this.formBuilder.group({
      recoverLoginId: [{value: '', disabled: true}, Validators.required],
      newPassword: ['', Validators.required],
      confirmNewPassword: ['', Validators.required]
    });
  }

  get f() {
    return this.recoverLoginForm.controls;
  }

  onSubmit() {
    alert('Password reset');
  }

  onCancel() {

  }
}
