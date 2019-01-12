import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITopicComment } from 'app/shared/model/topic-comment.model';

@Component({
    selector: 'jhi-topic-comment-detail',
    templateUrl: './topic-comment-detail.component.html'
})
export class TopicCommentDetailComponent implements OnInit {
    topicComment: ITopicComment;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ topicComment }) => {
            this.topicComment = topicComment;
        });
    }

    previousState() {
        window.history.back();
    }
}
