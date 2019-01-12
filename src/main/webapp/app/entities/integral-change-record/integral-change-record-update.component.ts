import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IIntegralChangeRecord } from 'app/shared/model/integral-change-record.model';
import { IntegralChangeRecordService } from './integral-change-record.service';
import { IIntegralRule } from 'app/shared/model/integral-rule.model';
import { IntegralRuleService } from 'app/entities/integral-rule';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-integral-change-record-update',
    templateUrl: './integral-change-record-update.component.html'
})
export class IntegralChangeRecordUpdateComponent implements OnInit {
    private _integralChangeRecord: IIntegralChangeRecord;
    isSaving: boolean;

    integralrules: IIntegralRule[];

    users: IUser[];
    createdDate: string;
    lastModifiedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private integralChangeRecordService: IntegralChangeRecordService,
        private integralRuleService: IntegralRuleService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ integralChangeRecord }) => {
            this.integralChangeRecord = integralChangeRecord;
        });
        this.integralRuleService.query().subscribe(
            (res: HttpResponse<IIntegralRule[]>) => {
                this.integralrules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        // this.userService.query().subscribe(
        //     (res: HttpResponse<IUser[]>) => {
        //         this.users = res.body;
        //     },
        //     (res: HttpErrorResponse) => this.onError(res.message)
        // );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.integralChangeRecord.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.integralChangeRecord.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.integralChangeRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.integralChangeRecordService.update(this.integralChangeRecord));
        } else {
            this.subscribeToSaveResponse(this.integralChangeRecordService.create(this.integralChangeRecord));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IIntegralChangeRecord>>) {
        result.subscribe(
            (res: HttpResponse<IIntegralChangeRecord>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackIntegralRuleById(index: number, item: IIntegralRule) {
        return item.id;
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
    get integralChangeRecord() {
        return this._integralChangeRecord;
    }

    set integralChangeRecord(integralChangeRecord: IIntegralChangeRecord) {
        this._integralChangeRecord = integralChangeRecord;
        this.createdDate = moment(integralChangeRecord.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(integralChangeRecord.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
