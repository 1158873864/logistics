import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ILogisticsDdnPic } from 'app/shared/model/logistics-ddn-pic.model';
import { LogisticsDdnPicService } from './logistics-ddn-pic.service';
import { ILogisticsDdn } from 'app/shared/model/logistics-ddn.model';
import { LogisticsDdnService } from 'app/entities/logistics-ddn';

@Component({
    selector: 'jhi-logistics-ddn-pic-update',
    templateUrl: './logistics-ddn-pic-update.component.html'
})
export class LogisticsDdnPicUpdateComponent implements OnInit {
    private _logisticsDdnPic: ILogisticsDdnPic;
    isSaving: boolean;

    logisticsddns: ILogisticsDdn[];
    createdDate: string;
    lastModifiedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private logisticsDdnPicService: LogisticsDdnPicService,
        private logisticsDdnService: LogisticsDdnService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ logisticsDdnPic }) => {
            this.logisticsDdnPic = logisticsDdnPic;
            this.uploadPic(this);
        });
        this.logisticsDdnService.query(
            {
                page: 0,
                size: 1000,
                sort: ''
            }
        ).subscribe(
            (res: HttpResponse<ILogisticsDdn[]>) => {
                this.logisticsddns = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    uploadPic(obj) {
        const uploader = window['WebUploader'].create({
            server: '/api/static/upload-logistics-ddn-pics',
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
            obj._logisticsDdnPic.path = ret.path;
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
        this.logisticsDdnPic.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.logisticsDdnPic.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.logisticsDdnPic.id !== undefined) {
            this.subscribeToSaveResponse(this.logisticsDdnPicService.update(this.logisticsDdnPic));
        } else {
            this.subscribeToSaveResponse(this.logisticsDdnPicService.create(this.logisticsDdnPic));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILogisticsDdnPic>>) {
        result.subscribe((res: HttpResponse<ILogisticsDdnPic>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackLogisticsDdnById(index: number, item: ILogisticsDdn) {
        return item.id;
    }
    get logisticsDdnPic() {
        return this._logisticsDdnPic;
    }

    set logisticsDdnPic(logisticsDdnPic: ILogisticsDdnPic) {
        this._logisticsDdnPic = logisticsDdnPic;
        this.createdDate = moment(logisticsDdnPic.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(logisticsDdnPic.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
