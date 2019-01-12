import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILogisticsDdn, Status  } from 'app/shared/model/logistics-ddn.model';
import { LogisticsDdnService } from './logistics-ddn.service';

@Component({
    selector: 'jhi-logistics-ddn-update',
    templateUrl: './logistics-ddn-update.component.html'
})
export class LogisticsDdnUpdateComponent implements OnInit {
    private _logisticsDdn: ILogisticsDdn;
    isSaving: boolean;
    createdDate: string;
    lastModifiedDate: string;

    constructor(private logisticsDdnService: LogisticsDdnService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ logisticsDdn }) => {
            this.logisticsDdn = logisticsDdn;

            if (this.logisticsDdn.id !== undefined) {
                // 编辑
            } else {
                // 创建
                this.logisticsDdn.status = Status.ENABLE;
            }

            this.uploadPic(this);
        });
    }

    previousState() {
        window.history.back();
    }

    uploadPic(obj) {
        const uploader = window['WebUploader'].create({
            server: '/api/static/upload-logistics-ddn-pic',
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
            obj._logisticsDdn.pic = ret.path;
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
        this.logisticsDdn.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.logisticsDdn.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.logisticsDdn.id !== undefined) {
            this.subscribeToSaveResponse(this.logisticsDdnService.update(this.logisticsDdn));
        } else {
            this.subscribeToSaveResponse(this.logisticsDdnService.create(this.logisticsDdn));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILogisticsDdn>>) {
        result.subscribe((res: HttpResponse<ILogisticsDdn>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get logisticsDdn() {
        return this._logisticsDdn;
    }

    set logisticsDdn(logisticsDdn: ILogisticsDdn) {
        this._logisticsDdn = logisticsDdn;
        this.createdDate = moment(logisticsDdn.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(logisticsDdn.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
