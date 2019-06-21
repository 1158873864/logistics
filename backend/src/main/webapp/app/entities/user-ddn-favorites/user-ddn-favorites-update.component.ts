import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IUserDdnFavorites } from 'app/shared/model/user-ddn-favorites.model';
import { UserDdnFavoritesService } from './user-ddn-favorites.service';
import { ILogisticsDdn } from 'app/shared/model/logistics-ddn.model';
import { LogisticsDdnService } from 'app/entities/logistics-ddn';
import { IUserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from 'app/entities/user-info';

@Component({
    selector: 'jhi-user-ddn-favorites-update',
    templateUrl: './user-ddn-favorites-update.component.html'
})
export class UserDdnFavoritesUpdateComponent implements OnInit {
    private _userDdnFavorites: IUserDdnFavorites;
    isSaving: boolean;

    logisticsddns: ILogisticsDdn[];

    userinfos: IUserInfo[];
    createdDate: string;
    lastModifiedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private userDdnFavoritesService: UserDdnFavoritesService,
        private logisticsDdnService: LogisticsDdnService,
        private userInfoService: UserInfoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ userDdnFavorites }) => {
            this.userDdnFavorites = userDdnFavorites;
        });
        this.logisticsDdnService.query().subscribe(
            (res: HttpResponse<ILogisticsDdn[]>) => {
                this.logisticsddns = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userInfoService.query().subscribe(
            (res: HttpResponse<IUserInfo[]>) => {
                this.userinfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.userDdnFavorites.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.userDdnFavorites.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.userDdnFavorites.id !== undefined) {
            this.subscribeToSaveResponse(this.userDdnFavoritesService.update(this.userDdnFavorites));
        } else {
            this.subscribeToSaveResponse(this.userDdnFavoritesService.create(this.userDdnFavorites));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUserDdnFavorites>>) {
        result.subscribe((res: HttpResponse<IUserDdnFavorites>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackLogisticsDdnById(index: number, item: ILogisticsDdn) {
        return item.id;
    }

    trackUserInfoById(index: number, item: IUserInfo) {
        return item.id;
    }
    get userDdnFavorites() {
        return this._userDdnFavorites;
    }

    set userDdnFavorites(userDdnFavorites: IUserDdnFavorites) {
        this._userDdnFavorites = userDdnFavorites;
        this.createdDate = moment(userDdnFavorites.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(userDdnFavorites.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
