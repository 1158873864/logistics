import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITopicViewed } from 'app/shared/model/topic-viewed.model';
import { TopicViewedService } from './topic-viewed.service';
import { ITopic } from 'app/shared/model/topic.model';
import { TopicService } from 'app/entities/topic';
import { IUserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from 'app/entities/user-info';

@Component({
    selector: 'jhi-topic-viewed-update',
    templateUrl: './topic-viewed-update.component.html'
})
export class TopicViewedUpdateComponent implements OnInit {
    private _topicViewed: ITopicViewed;
    isSaving: boolean;

    topics: ITopic[];

    userinfos: IUserInfo[];
    oDateTime: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private topicViewedService: TopicViewedService,
        private topicService: TopicService,
        private userInfoService: UserInfoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ topicViewed }) => {
            this.topicViewed = topicViewed;
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
        this.topicViewed.oDateTime = moment(this.oDateTime, DATE_TIME_FORMAT);
        if (this.topicViewed.id !== undefined) {
            this.subscribeToSaveResponse(this.topicViewedService.update(this.topicViewed));
        } else {
            this.subscribeToSaveResponse(this.topicViewedService.create(this.topicViewed));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITopicViewed>>) {
        result.subscribe((res: HttpResponse<ITopicViewed>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get topicViewed() {
        return this._topicViewed;
    }

    set topicViewed(topicViewed: ITopicViewed) {
        this._topicViewed = topicViewed;
        this.oDateTime = moment(topicViewed.oDateTime).format(DATE_TIME_FORMAT);
    }
}
