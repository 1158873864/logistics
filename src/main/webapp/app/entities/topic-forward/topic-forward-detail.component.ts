import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITopicForward } from 'app/shared/model/topic-forward.model';

@Component({
    selector: 'jhi-topic-forward-detail',
    templateUrl: './topic-forward-detail.component.html'
})
export class TopicForwardDetailComponent implements OnInit {
    topicForward: ITopicForward;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ topicForward }) => {
            this.topicForward = topicForward;
        });
    }

    previousState() {
        window.history.back();
    }
}
