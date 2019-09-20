import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {ClientApi} from "../../../shared/constants/client-api";
import {StudentCreateModel} from "../core/model/student/student-create.model";
import {Observable} from "rxjs/Observable";
import {map, tap} from "rxjs/operators";
import {parseHttpResponse} from "selenium-webdriver/http";
import {any} from "codelyzer/util/function";
import {StaffDetailModel} from "../core/model/staff/staff-detail.model";
import {pipe} from "rxjs/internal-compatibility";

@Injectable({
  providedIn: 'root'
})
export class PdfService {

  constructor(
    private  http: HttpClient
  ) {
  }
  downloadPdf(staffId: string, filename: string): void {
    const token = 'my JWT';
    const headers = new HttpHeaders().set('authorization', 'Bearer ' + token);
    this.http.get(ClientApi.PDF.replace(/{staff_id}/gi,staffId), {headers, responseType: 'blob' as 'json'})
      .subscribe(
      (response: any) => {
        let dataType = response.type;
        let binaryData = [];
        binaryData.push(response);
        let downloadLink = document.createElement('a');
        downloadLink.href = window.URL.createObjectURL(new Blob(binaryData, {type: dataType}));
        // downloadLink.download = filename;
        if (filename)
          downloadLink.setAttribute('download', filename);
        document.body.appendChild(downloadLink);
        downloadLink.click();
      }
    )
  }
  downloadStudentPdf(studentId: string, filename: string): void {
    const token = 'my JWT';
    const headers = new HttpHeaders().set('authorization', 'Bearer ' + token);
    this.http.get(ClientApi.STUDENT_PDF.replace(/{student_id}/gi,studentId), {headers, responseType: 'blob' as 'json'})
      .subscribe(
        (response: any) => {
          let dataType = response.type;
          let binaryData = [];
          binaryData.push(response);
          let downloadLink = document.createElement('a');
          downloadLink.href = window.URL.createObjectURL(new Blob(binaryData, {type: dataType}));
          // downloadLink.download = filename;
          if (filename)
            downloadLink.setAttribute('download', filename);
          document.body.appendChild(downloadLink);
          downloadLink.click();
        }
      )
  }

}




