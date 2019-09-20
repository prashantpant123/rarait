
import {Router} from "@angular/router";
import {Injectable} from "@angular/core";
import {StudentDetailModel} from "../core/model/student/student-detail.model";
import {print} from "util";


@Injectable({
  providedIn: 'root'
})
export class PrintService {
  isPrinting = false;
  studentProfile=StudentDetailModel;

  constructor(
  private printService: PrintService,
  private router: Router) { }

  printDocument(documentName: string, documentData: any) {
    console.log("print document");
    this.studentProfile=documentData;
    this.router.navigate(['/',
      { outlets: {
        'print':  ['print' ,documentName]
        }}]);
    console.log("after navigating");
  }
  onDataReady() {
      window.print();
      this.isPrinting = false;
      this.router.navigate([{ outlets: { print: null }}]);
  }
}
