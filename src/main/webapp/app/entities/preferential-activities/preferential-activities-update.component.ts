import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPreferentialActivities } from 'app/shared/model/preferential-activities.model';
import { PreferentialActivitiesService } from './preferential-activities.service';

@Component({
    selector: 'jhi-preferential-activities-update',
    templateUrl: './preferential-activities-update.component.html'
})
export class PreferentialActivitiesUpdateComponent implements OnInit {
    private _preferentialActivities: IPreferentialActivities;
    isSaving: boolean;
    startDate: string;
    endDate: string;
    createdDate: string;
    lastModifiedDate: string;
    descriptionEditor: any;

    constructor(private preferentialActivitiesService: PreferentialActivitiesService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ preferentialActivities }) => {
            this.preferentialActivities = preferentialActivities;
            this.initDescriptionEditor(this);
            this.uploadPic(this);
        });
    }

    previousState() {
        window.history.back();
    }

    initDescriptionEditor(obj) {
        const E = window['wangEditor'];
        obj.descriptionEditor = new E('#description-editor');
        obj.descriptionEditor.customConfig.uploadImgServer = '/api/static/upload-sys-preferential-activities-pics';
        obj.descriptionEditor.customConfig.uploadFileName = 'file';
        obj.descriptionEditor.customConfig.uploadImgMaxSize = 1 * 1024 * 1024;
        obj.descriptionEditor.create();
    }
    uploadPic(obj) {
        const uploader = window['WebUploader'].create({
            server: '/api/static/upload-preferential-activities-cover',
            pick: '#picker',
            auto: true,
            resize: false,
            fileNumLimit: 1,
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        uploader.on('uploadSuccess', function(file, ret) {
            obj._preferentialActivities.cover = ret.path;
            uploader.destroy();
            obj.uploadPic(obj);
        });
        uploader.on('uploadError', function(file, ret) {
            uploader.destroy();
            obj.uploadPic(obj);
        });
    }
    save() {
        this.isSaving = true;
        this.preferentialActivities.content = this.descriptionEditor.txt.html();
        this.preferentialActivities.startDate = moment(this.startDate, DATE_TIME_FORMAT);
        this.preferentialActivities.endDate = moment(this.endDate, DATE_TIME_FORMAT);
        this.preferentialActivities.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.preferentialActivities.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.preferentialActivities.id !== undefined) {
            this.subscribeToSaveResponse(this.preferentialActivitiesService.update(this.preferentialActivities));
        } else {
            this.subscribeToSaveResponse(this.preferentialActivitiesService.create(this.preferentialActivities));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPreferentialActivities>>) {
        result.subscribe(
            (res: HttpResponse<IPreferentialActivities>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get preferentialActivities() {
        return this._preferentialActivities;
    }

    set preferentialActivities(preferentialActivities: IPreferentialActivities) {
        this._preferentialActivities = preferentialActivities;
        this.startDate = moment(preferentialActivities.startDate).format(DATE_TIME_FORMAT);
        this.endDate = moment(preferentialActivities.endDate).format(DATE_TIME_FORMAT);
        this.createdDate = moment(preferentialActivities.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(preferentialActivities.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
