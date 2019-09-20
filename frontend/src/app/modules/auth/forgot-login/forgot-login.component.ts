import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import {routerTransition} from '../../../router.animations';

@Component({
  selector: 'app-forgot-login',
  templateUrl: './forgot-login.component.html',
  styleUrls: ['./forgot-login.component.scss'],
  animations: [routerTransition()]
})
export class ForgotLoginComponent implements OnInit {

  forgotLoginForm: FormGroup;
  submitted = false;

  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit() {
    this.forgotLoginForm = this.formBuilder.group({
      forgotLoginId: ['', Validators.required, Validators.minLength(3), Validators.maxLength(25)]
    });
  }

  get f() {
    return this.forgotLoginForm.controls;
  }

  onSubmit() {

  }
}
