import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IUserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from './user-info.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-user-info-update',
    templateUrl: './user-info-update.component.html'
})
export class UserInfoUpdateComponent implements OnInit {
    private _userInfo: IUserInfo;
    isSaving: boolean;

    users: IUser[];
    registerDate: string;
    lastLoginedDate: string;
    createdDate: string;
    lastModifiedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private userInfoService: UserInfoService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ userInfo }) => {
            this.userInfo = userInfo;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.userInfo.registerDate = moment(this.registerDate, DATE_TIME_FORMAT);
        this.userInfo.lastLoginedDate = moment(this.lastLoginedDate, DATE_TIME_FORMAT);
        this.userInfo.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.userInfo.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.userInfo.id !== undefined) {
            this.subscribeToSaveResponse(this.userInfoService.update(this.userInfo));
        } else {
            this.subscribeToSaveResponse(this.userInfoService.create(this.userInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUserInfo>>) {
        result.subscribe((res: HttpResponse<IUserInfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
    get userInfo() {
        return this._userInfo;
    }

    set userInfo(userInfo: IUserInfo) {
        this._userInfo = userInfo;
        this.registerDate = moment(userInfo.registerDate).format(DATE_TIME_FORMAT);
        this.lastLoginedDate = moment(userInfo.lastLoginedDate).format(DATE_TIME_FORMAT);
        this.createdDate = moment(userInfo.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(userInfo.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
