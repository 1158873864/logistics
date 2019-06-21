import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ILogisticsDdnWWW } from 'app/shared/model/logistics-ddn-www.model';
import { LogisticsDdnWWWService } from './logistics-ddn-www.service';
import { ILogisticsDdn } from 'app/shared/model/logistics-ddn.model';
import { LogisticsDdnService } from 'app/entities/logistics-ddn';

@Component({
    selector: 'jhi-logistics-ddn-www-update',
    templateUrl: './logistics-ddn-www-update.component.html'
})
export class LogisticsDdnWWWUpdateComponent implements OnInit {
    private _logisticsDdnWWW: ILogisticsDdnWWW;
    isSaving: boolean;

    logisticsddns: ILogisticsDdn[];
    createdDate: string;
    lastModifiedDate: string;
    tmpLname: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private logisticsDdnWWWService: LogisticsDdnWWWService,
        private logisticsDdnService: LogisticsDdnService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ logisticsDdnWWW }) => {
            this.logisticsDdnWWW = logisticsDdnWWW;
        });
        this.logisticsDdnService.query(
            {
                page: 0,
                size: 5000
            }
        ).subscribe(
            (res: HttpResponse<ILogisticsDdn[]>) => {
                this.logisticsddns = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    searchLname(tmpLname) {
        this.logisticsddns = [];
        const tmpLnames = [];
        this.logisticsDdnService.query({
            page: 0,
            size: 5000
        }).subscribe(
            (res: HttpResponse<ILogisticsDdn[]>) => {
                this.logisticsddns = res.body;
                for ( let i = 0; i < this.logisticsddns.length ; i++ ) {
                    if (this.logisticsddns[i].title.indexOf(tmpLname) !== -1) {
                        tmpLnames.push(this.logisticsddns[i]);
                    }
                }
                this.logisticsddns = tmpLnames;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.logisticsDdnWWW.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.logisticsDdnWWW.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.logisticsDdnWWW.id !== undefined) {
            this.subscribeToSaveResponse(this.logisticsDdnWWWService.update(this.logisticsDdnWWW));
        } else {
            this.subscribeToSaveResponse(this.logisticsDdnWWWService.create(this.logisticsDdnWWW));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILogisticsDdnWWW>>) {
        result.subscribe((res: HttpResponse<ILogisticsDdnWWW>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get logisticsDdnWWW() {
        return this._logisticsDdnWWW;
    }

    set logisticsDdnWWW(logisticsDdnWWW: ILogisticsDdnWWW) {
        this._logisticsDdnWWW = logisticsDdnWWW;
        this.createdDate = moment(logisticsDdnWWW.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(logisticsDdnWWW.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
