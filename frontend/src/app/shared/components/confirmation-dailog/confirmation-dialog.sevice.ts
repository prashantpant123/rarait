import {Injectable} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

import {ConfirmationDialogComponent} from './confirmation-dialog.component';
import {DisplayListModel} from './display-list.model';

@Injectable()
export class ConfirmationDialogSevice {

  constructor(private modalService: NgbModal) {
  }

  public confirm(btnOkText: string = 'OK',
                 btnCancelText: string = 'Cancel',
                 detailList: DisplayListModel[],
                 dialogSize: 'sm' | 'lg' = 'sm'): Promise<boolean> {
    const modalRef = this.modalService.open(ConfirmationDialogComponent, {size: 'lg'});
    modalRef.componentInstance.displayList = detailList;
    modalRef.componentInstance.btnOkText = btnOkText;
    modalRef.componentInstance.btnCancelText = btnCancelText;

    return modalRef.result;
  }

  public showDialog(detailList: DisplayListModel[]): Promise<boolean> {
    return this.confirm('Confirm', 'Cancel', detailList);
  }

}
