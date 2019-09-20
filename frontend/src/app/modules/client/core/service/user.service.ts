import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/index';

import {ClientApi} from '../../../../shared/constants/client-api';
import {UserInfoModel} from '../../../admin/core/model/user-info.model';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  getAdminUserDetail(userId: string): Observable<UserInfoModel> {
    return this.http.get<UserInfoModel>(ClientApi.USER_INFO.replace(/{user_id}/gi, userId));
  }
}
