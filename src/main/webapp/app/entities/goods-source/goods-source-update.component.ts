import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IGoodsSource } from 'app/shared/model/goods-source.model';
import { GoodsSourceService } from './goods-source.service';
import { IUserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from 'app/entities/user-info';

@Component({
    selector: 'jhi-goods-source-update',
    templateUrl: './goods-source-update.component.html'
})
export class GoodsSourceUpdateComponent implements OnInit {
    private _goodsSource: IGoodsSource;
    isSaving: boolean;

    userinfos: IUserInfo[];
    effectiveDate: string;
    layTime: string;
    createdDate: string;
    lastModifiedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private goodsSourceService: GoodsSourceService,
        private userInfoService: UserInfoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ goodsSource }) => {
            this.goodsSource = goodsSource;
        });
        // this.userInfoService.query().subscribe(
        //     (res: HttpResponse<IUserInfo[]>) => {
        //         this.userinfos = res.body;
        //     },
        //     (res: HttpErrorResponse) => this.onError(res.message)
        // );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.goodsSource.effectiveDate = moment(this.effectiveDate, DATE_TIME_FORMAT);
        this.goodsSource.layTime = moment(this.layTime, DATE_TIME_FORMAT);
        this.goodsSource.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.goodsSource.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.goodsSource.id !== undefined) {
            this.subscribeToSaveResponse(this.goodsSourceService.update(this.goodsSource));
        } else {
            this.subscribeToSaveResponse(this.goodsSourceService.create(this.goodsSource));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGoodsSource>>) {
        result.subscribe((res: HttpResponse<IGoodsSource>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserInfoById(index: number, item: IUserInfo) {
        return item.id;
    }
    get goodsSource() {
        return this._goodsSource;
    }

    set goodsSource(goodsSource: IGoodsSource) {
        this._goodsSource = goodsSource;
        this.effectiveDate = moment(goodsSource.effectiveDate).format(DATE_TIME_FORMAT);
        this.layTime = moment(goodsSource.layTime).format(DATE_TIME_FORMAT);
        this.createdDate = moment(goodsSource.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(goodsSource.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
