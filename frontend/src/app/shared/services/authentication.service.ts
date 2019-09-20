import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {
  BehaviorSubject,
  Observable
} from 'rxjs';
import {map} from 'rxjs/operators';

import {User} from '../model/user';
import {Login} from '../model/login';
import {AuthApi} from '../constants/auth-api';
import {Logout} from '../model/logout';
import {NzMessageService} from 'ng-zorro-antd';

@Injectable({providedIn: 'root'})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  TOKEN_NAME = 'user_auth_info';


  constructor(private http: HttpClient,
              private router: Router,
              private messageService: NzMessageService) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem(this.TOKEN_NAME)));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  currentToken(): string {
    return JSON.parse(localStorage.getItem(this.TOKEN_NAME));
  }

  login(username: string, password: string): Observable<User> {

    return this.http.post<User>(AuthApi.SIGNIN, new Login(username, password))
      .pipe(map(user => {
        if (user && user.auth_token) {
          localStorage.setItem(this.TOKEN_NAME, JSON.stringify(user));
          this.currentUserSubject.next(user);
        }
        return user;
      }));
  }

  logout() {
    const cu = this.currentUserValue();
    if (cu && cu.auth_token) {
      this.http.post<any>(AuthApi.SIGNOUT, new Logout(cu.auth_token))
        .subscribe(res => {
            // console.log(res.message);
            this.messageService.error(res && res.message ? res.message : 'Sorry, some error occurred.', {
              nzDuration: 5000,
              nzPauseOnHover: true
            });
          }, error => {
            // console.log('Error ' + error);
            this.messageService.error(error && error.error ? error.error.message : 'Sorry, some error occurred.', {
              nzDuration: 5000,
              nzPauseOnHover: true
            });
          }
        );
    }
    localStorage.removeItem(this.TOKEN_NAME);
    this.currentUserSubject.next(null);
    // location.reload(true);
    this.router.navigateByUrl('/login');
  }
}
