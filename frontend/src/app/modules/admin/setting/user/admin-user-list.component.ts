import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import {AppRegex} from '../../../../shared/constants/app-regex';
import {PasswordValidator} from '../../../../shared/validators/password.validator';
import {ModalService} from '../../../../shared/components/modal/modal.service';
import {AdminUserService} from '../../core/service/admin-user.service';
import {UserListModel} from '../../core/model/user-list.model';

@Component({
  selector: 'app-admin-user-list',
  templateUrl: './admin-user-list.component.html'
})
export class AdminUserListComponent implements OnInit {

  userList: UserListModel[] = [];

  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  mediumPassword = AppRegex.MEDIUM_PASSWORD;

  constructor(private formBuilder: FormBuilder,
              private modalService: ModalService,
              private userService: AdminUserService) {
  }

  ngOnInit() {
    this.getUserList();
  }

  getUserList() {
    this.userService.getAdminUserList()
      .subscribe(response => {
        this.userList = response;
        this._loading = false;
      });
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getUserList();
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
    this.modalService.close(id);
  }

  onDeleteConfirm() {
  }

  onDeleteCancel() {
  }
}
