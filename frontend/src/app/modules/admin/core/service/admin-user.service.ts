import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/index';

import {AdminApi} from '../../../../shared/constants/admin-api';
import {UserListModel} from '../model/user-list.model';
import {UserCreateModel} from '../model/user-create.model';
import {UpdateUserStatusModel} from '../model/update-user-status.model';
import {UserInfoModel} from '../model/user-info.model';

@Injectable()
export class AdminUserService {

  constructor(private http: HttpClient) {
  }

  getAdminUserList(): Observable<UserListModel[]> {
    return this.http.get<UserListModel[]>(AdminApi.ADMIN_USER);
  }

  addAdminUser(userCreate: UserCreateModel): Observable<any> {
    return this.http.post<any>(AdminApi.ADMIN_USER, userCreate);
  }

  updateUserStatus(userUpdate: UpdateUserStatusModel): Observable<any> {
    return this.http.post<any>(AdminApi.ADMIN_USER_EDIT, userUpdate);
  }

  getAdminUserDetail(userId: string): Observable<UserInfoModel> {
    return this.http.get<UserInfoModel>(AdminApi.ADMIN_USER_INFO.replace(/{user_id}/gi, userId));
  }
}
