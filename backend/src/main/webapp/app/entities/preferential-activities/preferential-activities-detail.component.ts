import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPreferentialActivities } from 'app/shared/model/preferential-activities.model';

@Component({
    selector: 'jhi-preferential-activities-detail',
    templateUrl: './preferential-activities-detail.component.html'
})
export class PreferentialActivitiesDetailComponent implements OnInit {
    preferentialActivities: IPreferentialActivities;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ preferentialActivities }) => {
            this.preferentialActivities = preferentialActivities;
        });
    }

    previousState() {
        window.history.back();
    }
}
