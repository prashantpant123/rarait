import {Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import {
  Observable,
  throwError
} from 'rxjs';
import {
  catchError,
  map
} from 'rxjs/operators';
import 'rxjs/add/operator/do';

import {AuthenticationService} from '../shared/services/authentication.service';
import {NzMessageService} from 'ng-zorro-antd';

@Injectable()
export class HttpConfigInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService,
              private message: NzMessageService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      map((event: HttpEvent<any>) => {
        // if (event instanceof HttpResponse) {
        //   console.log('event--->>>', event);
        // }
        return event;
      }),
      catchError((error: HttpErrorResponse) => {
        // console.log('ERR ' + error.message + ' - ' + error.error + ' - ' + error.status + ' -' + error.error.message);
        this.message.error(error && error.error ? error.error.message : 'Sorry, some error occurred.', {
          nzDuration: 5000,
          nzPauseOnHover: true
        });
        if (error.status === 401 || error.status === 403) {
          this.authenticationService.logout();
        }
        return throwError(error);
      })
    );
  }
}
