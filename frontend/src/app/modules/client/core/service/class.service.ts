import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../shared/constants/client-api';
import {ClassCreateModel} from '../model/class/class-create.model';
import {ClassPageModel} from '../model/class/class-page.model';
import {ClassDetailModel} from '../model/class/class-detail.model';
import {ClassDropdownModel} from '../model/class/class-dropdown.model';
import {DropdownModel} from '../../../../shared/model/dropdown.model';

@Injectable()
export class ClassService {

  constructor(private http: HttpClient) {
  }

  getClassList(page: number, sortField: string, sortOrder: string): Observable<ClassPageModel> {
    let params = new HttpParams()
      .append('page', `${page}`);
    if (sortField !== null) {
      params = params.append('sort_field', sortField);
    }
    if (sortOrder !== null) {
      params = params.append('ascend', sortOrder === 'ascend' ? true.toString() : false.toString());
    }
    return this.http.get<ClassPageModel>(`${ClientApi.CLASS}`, {params});
  }

  createNewClass(classModel: ClassCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.CLASS, classModel);
  }

  updateClassDetail(classModel: ClassCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.CLASS_EDIT, classModel);
  }

  getClassDetail(classId: string): Observable<ClassDetailModel> {
    return this.http.get<ClassDetailModel>(ClientApi.CLASS_ID.replace(/{class_id}/gi, classId));
  }

  getClassDropdown(): Observable<ClassDropdownModel[]> {
    return this.http.get<ClassDropdownModel[]>(ClientApi.CLASS_LIST);
  }

  getDepartmentDropdown(): Observable<DropdownModel[]> {
    return this.http.get<DropdownModel[]>(ClientApi.DEPARTMENT_LIST);
  }
}
