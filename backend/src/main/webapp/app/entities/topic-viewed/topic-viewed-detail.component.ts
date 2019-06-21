import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITopicViewed } from 'app/shared/model/topic-viewed.model';

@Component({
    selector: 'jhi-topic-viewed-detail',
    templateUrl: './topic-viewed-detail.component.html'
})
export class TopicViewedDetailComponent implements OnInit {
    topicViewed: ITopicViewed;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ topicViewed }) => {
            this.topicViewed = topicViewed;
        });
    }

    previousState() {
        window.history.back();
    }
}
