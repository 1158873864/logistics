import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITopicFabulous } from 'app/shared/model/topic-fabulous.model';

@Component({
    selector: 'jhi-topic-fabulous-detail',
    templateUrl: './topic-fabulous-detail.component.html'
})
export class TopicFabulousDetailComponent implements OnInit {
    topicFabulous: ITopicFabulous;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ topicFabulous }) => {
            this.topicFabulous = topicFabulous;
        });
    }

    previousState() {
        window.history.back();
    }
}
