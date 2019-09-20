import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../shared/constants/client-api';
import {SectionPageModel} from '../model/class/section/section-page.model';
import {DropdownModel} from '../../../../shared/model/dropdown.model';
import {SectionDetailModel} from '../model/class/section/section-detail.model';
import {SectionCreateModel} from '../model/class/section/section-create.model';

@Injectable()
export class SectionService {

  constructor(private http: HttpClient) {
  }

  getSectionList(page: number, classId: number): Observable<SectionPageModel> {
    return this.http.get<SectionPageModel>(ClientApi.SECTION + '?page=' + page + '&class_id=' + classId);
  }

  createNewSession(model: SectionCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.SECTION, model);
  }

  getSessionDetail(sectionId: string): Observable<SectionDetailModel> {
    return this.http.get<SectionDetailModel>(ClientApi.SECTION_ID.replace(/{section_id}/gi, sectionId));
  }

  getSectionDropdownForClass(classId: number): Observable<DropdownModel[]> {
    return this.http.get<DropdownModel[]>(ClientApi.SECTION_DROPDOWN.replace(/{class_id}/gi, classId + ''));
  }
}
