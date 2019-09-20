import {
  HttpClient,
  HttpEvent,
  HttpRequest
} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../shared/constants/client-api';

@Injectable()
export class InstituteFileUploadService {
  constructor(private http: HttpClient) {
  }

  uploadFile(file: File, studentId: number, type: string): Observable<HttpEvent<{}>> {
    const data: FormData = new FormData();
    data.append('file', file);
    data.append('id', `${studentId}`);
    data.append('type', `${type}`);

    const req = new HttpRequest('POST', ClientApi.FILE, data, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }


}
