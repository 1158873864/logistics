import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITopicFabulous } from 'app/shared/model/topic-fabulous.model';
import { TopicFabulousService } from './topic-fabulous.service';
import { ITopic } from 'app/shared/model/topic.model';
import { TopicService } from 'app/entities/topic';
import { IUserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from 'app/entities/user-info';

@Component({
    selector: 'jhi-topic-fabulous-update',
    templateUrl: './topic-fabulous-update.component.html'
})
export class TopicFabulousUpdateComponent implements OnInit {
    private _topicFabulous: ITopicFabulous;
    isSaving: boolean;

    topics: ITopic[];

    userinfos: IUserInfo[];
    oDateTime: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private topicFabulousService: TopicFabulousService,
        private topicService: TopicService,
        private userInfoService: UserInfoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ topicFabulous }) => {
            this.topicFabulous = topicFabulous;
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
        this.topicFabulous.oDateTime = moment(this.oDateTime, DATE_TIME_FORMAT);
        if (this.topicFabulous.id !== undefined) {
            this.subscribeToSaveResponse(this.topicFabulousService.update(this.topicFabulous));
        } else {
            this.subscribeToSaveResponse(this.topicFabulousService.create(this.topicFabulous));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITopicFabulous>>) {
        result.subscribe((res: HttpResponse<ITopicFabulous>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get topicFabulous() {
        return this._topicFabulous;
    }

    set topicFabulous(topicFabulous: ITopicFabulous) {
        this._topicFabulous = topicFabulous;
        this.oDateTime = moment(topicFabulous.oDateTime).format(DATE_TIME_FORMAT);
    }
}
