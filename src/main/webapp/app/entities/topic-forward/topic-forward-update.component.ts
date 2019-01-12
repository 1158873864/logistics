import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITopicForward } from 'app/shared/model/topic-forward.model';
import { TopicForwardService } from './topic-forward.service';
import { ITopic } from 'app/shared/model/topic.model';
import { TopicService } from 'app/entities/topic';
import { IUserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from 'app/entities/user-info';

@Component({
    selector: 'jhi-topic-forward-update',
    templateUrl: './topic-forward-update.component.html'
})
export class TopicForwardUpdateComponent implements OnInit {
    private _topicForward: ITopicForward;
    isSaving: boolean;

    topics: ITopic[];

    userinfos: IUserInfo[];
    oDateTime: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private topicForwardService: TopicForwardService,
        private topicService: TopicService,
        private userInfoService: UserInfoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ topicForward }) => {
            this.topicForward = topicForward;
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
        this.topicForward.oDateTime = moment(this.oDateTime, DATE_TIME_FORMAT);
        if (this.topicForward.id !== undefined) {
            this.subscribeToSaveResponse(this.topicForwardService.update(this.topicForward));
        } else {
            this.subscribeToSaveResponse(this.topicForwardService.create(this.topicForward));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITopicForward>>) {
        result.subscribe((res: HttpResponse<ITopicForward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get topicForward() {
        return this._topicForward;
    }

    set topicForward(topicForward: ITopicForward) {
        this._topicForward = topicForward;
        this.oDateTime = moment(topicForward.oDateTime).format(DATE_TIME_FORMAT);
    }
}
