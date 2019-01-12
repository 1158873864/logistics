import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILogisticsDdnWWW } from 'app/shared/model/logistics-ddn-www.model';

@Component({
    selector: 'jhi-logistics-ddn-www-detail',
    templateUrl: './logistics-ddn-www-detail.component.html'
})
export class LogisticsDdnWWWDetailComponent implements OnInit {
    logisticsDdnWWW: ILogisticsDdnWWW;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ logisticsDdnWWW }) => {
            this.logisticsDdnWWW = logisticsDdnWWW;
        });
    }

    previousState() {
        window.history.back();
    }
}
