import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIntegralChangeRecord } from 'app/shared/model/integral-change-record.model';

@Component({
    selector: 'jhi-integral-change-record-detail',
    templateUrl: './integral-change-record-detail.component.html'
})
export class IntegralChangeRecordDetailComponent implements OnInit {
    integralChangeRecord: IIntegralChangeRecord;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ integralChangeRecord }) => {
            this.integralChangeRecord = integralChangeRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
