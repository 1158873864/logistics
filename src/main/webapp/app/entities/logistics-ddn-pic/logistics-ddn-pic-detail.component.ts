import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILogisticsDdnPic } from 'app/shared/model/logistics-ddn-pic.model';

@Component({
    selector: 'jhi-logistics-ddn-pic-detail',
    templateUrl: './logistics-ddn-pic-detail.component.html'
})
export class LogisticsDdnPicDetailComponent implements OnInit {
    logisticsDdnPic: ILogisticsDdnPic;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ logisticsDdnPic }) => {
            this.logisticsDdnPic = logisticsDdnPic;
        });
    }

    previousState() {
        window.history.back();
    }
}
