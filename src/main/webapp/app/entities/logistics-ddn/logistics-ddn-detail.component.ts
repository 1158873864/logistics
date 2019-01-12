import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILogisticsDdn } from 'app/shared/model/logistics-ddn.model';

@Component({
    selector: 'jhi-logistics-ddn-detail',
    templateUrl: './logistics-ddn-detail.component.html'
})
export class LogisticsDdnDetailComponent implements OnInit {
    logisticsDdn: ILogisticsDdn;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ logisticsDdn }) => {
            this.logisticsDdn = logisticsDdn;
        });
    }

    previousState() {
        window.history.back();
    }
}
