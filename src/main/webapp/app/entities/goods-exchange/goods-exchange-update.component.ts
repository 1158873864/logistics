import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IGoodsExchange } from 'app/shared/model/goods-exchange.model';
import { GoodsExchangeService } from './goods-exchange.service';
import { IGoods } from 'app/shared/model/goods.model';
import { GoodsService } from 'app/entities/goods';
import { IUserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from 'app/entities/user-info';

@Component({
    selector: 'jhi-goods-exchange-update',
    templateUrl: './goods-exchange-update.component.html'
})
export class GoodsExchangeUpdateComponent implements OnInit {
    private _goodsExchange: IGoodsExchange;
    isSaving: boolean;

    goods: IGoods[];

    userinfos: IUserInfo[];
    createdDate: string;
    lastModifiedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private goodsExchangeService: GoodsExchangeService,
        private goodsService: GoodsService,
        private userInfoService: UserInfoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ goodsExchange }) => {
            this.goodsExchange = goodsExchange;
        });
        this.goodsService.query().subscribe(
            (res: HttpResponse<IGoods[]>) => {
                this.goods = res.body;
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
        this.goodsExchange.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.goodsExchange.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.goodsExchange.id !== undefined) {
            this.subscribeToSaveResponse(this.goodsExchangeService.update(this.goodsExchange));
        } else {
            this.subscribeToSaveResponse(this.goodsExchangeService.create(this.goodsExchange));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGoodsExchange>>) {
        result.subscribe((res: HttpResponse<IGoodsExchange>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackGoodsById(index: number, item: IGoods) {
        return item.id;
    }

    trackUserInfoById(index: number, item: IUserInfo) {
        return item.id;
    }
    get goodsExchange() {
        return this._goodsExchange;
    }

    set goodsExchange(goodsExchange: IGoodsExchange) {
        this._goodsExchange = goodsExchange;
        this.createdDate = moment(goodsExchange.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(goodsExchange.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
