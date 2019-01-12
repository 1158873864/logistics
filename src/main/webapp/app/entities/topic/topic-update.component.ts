import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITopic } from 'app/shared/model/topic.model';
import { TopicService } from './topic.service';
import { IUserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from 'app/entities/user-info';

@Component({
    selector: 'jhi-topic-update',
    templateUrl: './topic-update.component.html'
})
export class TopicUpdateComponent implements OnInit {
    private _topic: ITopic;
    isSaving: boolean;

    userinfos: IUserInfo[];

    topics: ITopic[];
    published: string;
    createdDate: string;
    lastModifiedDate: string;

    descriptionEditor: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private topicService: TopicService,
        private userInfoService: UserInfoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ topic }) => {
            this.topic = topic;
            this.initDescriptionEditor(this);
        });
        // this.userInfoService.query().subscribe(
        //     (res: HttpResponse<IUserInfo[]>) => {
        //         this.userinfos = res.body;
        //     },
        //     (res: HttpErrorResponse) => this.onError(res.message)
        // );
        // this.topicService.query().subscribe(
        //     (res: HttpResponse<ITopic[]>) => {
        //         this.topics = res.body;
        //     },
        //     (res: HttpErrorResponse) => this.onError(res.message)
        // );
    }
    initDescriptionEditor(obj) {
        const E = window['wangEditor'];
        obj.descriptionEditor = new E('#description-editor');
        obj.descriptionEditor.customConfig.uploadImgServer = '/api/static/upload-topic-pics';
        obj.descriptionEditor.customConfig.uploadFileName = 'file';
        obj.descriptionEditor.customConfig.uploadImgMaxSize = 1 * 1024 * 1024;
        obj.descriptionEditor.create();
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.topic.content = this.descriptionEditor.txt.html();
        this.topic.published = moment(this.published, DATE_TIME_FORMAT);
        this.topic.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.topic.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.topic.id !== undefined) {
            this.subscribeToSaveResponse(this.topicService.update(this.topic));
        } else {
            this.subscribeToSaveResponse(this.topicService.create(this.topic));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITopic>>) {
        result.subscribe((res: HttpResponse<ITopic>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserInfoById(index: number, item: IUserInfo) {
        return item.id;
    }

    trackTopicById(index: number, item: ITopic) {
        return item.id;
    }
    get topic() {
        return this._topic;
    }

    set topic(topic: ITopic) {
        this._topic = topic;
        this.published = moment(topic.published).format(DATE_TIME_FORMAT);
        this.createdDate = moment(topic.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(topic.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
