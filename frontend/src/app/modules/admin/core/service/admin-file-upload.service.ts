import {Injectable} from '@angular/core';
import {
  HttpClient,
  HttpEvent,
  HttpRequest
} from '@angular/common/http';
import {Observable} from 'rxjs';

import {AdminApi} from '../../../../shared/constants/admin-api';

@Injectable()
export class AdminFileUploadService {

  constructor(private http: HttpClient) {
  }

  uploadFile(file: File, instituteId: number): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();
    formdata.append('logo', file);
    formdata.append('id', `${instituteId}`);

    const req = new HttpRequest('POST', AdminApi.FILE, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }
}
