import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITopicComment } from 'app/shared/model/topic-comment.model';
import { TopicCommentService } from './topic-comment.service';
import { ITopic } from 'app/shared/model/topic.model';
import { TopicService } from 'app/entities/topic';
import { IUserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from 'app/entities/user-info';

@Component({
    selector: 'jhi-topic-comment-update',
    templateUrl: './topic-comment-update.component.html'
})
export class TopicCommentUpdateComponent implements OnInit {
    private _topicComment: ITopicComment;
    isSaving: boolean;

    topics: ITopic[];

    userinfos: IUserInfo[];
    oDateTime: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private topicCommentService: TopicCommentService,
        private topicService: TopicService,
        private userInfoService: UserInfoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ topicComment }) => {
            this.topicComment = topicComment;
        });
        this.topicService.query().subscribe(
            (res: HttpResponse<ITopic[]>) => {
                this.topics = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userInfoService.query().subscribe(
            (res: HttpResponse<IUserInfo[]>) => {
                this.userinfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.topicComment.oDateTime = moment(this.oDateTime, DATE_TIME_FORMAT);
        if (this.topicComment.id !== undefined) {
            this.subscribeToSaveResponse(this.topicCommentService.update(this.topicComment));
        } else {
            this.subscribeToSaveResponse(this.topicCommentService.create(this.topicComment));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITopicComment>>) {
        result.subscribe((res: HttpResponse<ITopicComment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackTopicById(index: number, item: ITopic) {
        return item.id;
    }

    trackUserInfoById(index: number, item: IUserInfo) {
        return item.id;
    }
    get topicComment() {
        return this._topicComment;
    }

    set topicComment(topicComment: ITopicComment) {
        this._topicComment = topicComment;
        this.oDateTime = moment(topicComment.oDateTime).format(DATE_TIME_FORMAT);
    }
}
