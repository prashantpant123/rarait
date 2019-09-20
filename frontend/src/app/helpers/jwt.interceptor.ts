import {Injectable} from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import {Observable} from 'rxjs';

import {AuthenticationService} from '../shared/services/authentication.service';

@Injectable({providedIn: 'root'})
export class JwtInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService) {
  }

  intercept(request: HttpRequest<any>, handler: HttpHandler): Observable<HttpEvent<any>> {
    // add authorization header with jwt token if available
    const currentUser = this.authenticationService.currentUserValue();
    if (currentUser && currentUser.auth_token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${currentUser.auth_token}`
        }
      });
    }
    return handler.handle(request);
  }

}
