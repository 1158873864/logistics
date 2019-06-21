import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IIntegralRule } from 'app/shared/model/integral-rule.model';
import { IntegralRuleService } from './integral-rule.service';

@Component({
    selector: 'jhi-integral-rule-update',
    templateUrl: './integral-rule-update.component.html'
})
export class IntegralRuleUpdateComponent implements OnInit {
    private _integralRule: IIntegralRule;
    isSaving: boolean;
    createdDate: string;
    lastModifiedDate: string;

    constructor(private integralRuleService: IntegralRuleService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ integralRule }) => {
            this.integralRule = integralRule;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.integralRule.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.integralRule.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.integralRule.id !== undefined) {
            this.subscribeToSaveResponse(this.integralRuleService.update(this.integralRule));
        } else {
            this.subscribeToSaveResponse(this.integralRuleService.create(this.integralRule));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IIntegralRule>>) {
        result.subscribe((res: HttpResponse<IIntegralRule>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get integralRule() {
        return this._integralRule;
    }

    set integralRule(integralRule: IIntegralRule) {
        this._integralRule = integralRule;
        this.createdDate = moment(integralRule.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(integralRule.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
