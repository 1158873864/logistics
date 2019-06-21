import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISysRecruitmentInformation } from 'app/shared/model/sys-recruitment-information.model';
import { SysRecruitmentInformationService } from './sys-recruitment-information.service';

@Component({
    selector: 'jhi-sys-recruitment-information-update',
    templateUrl: './sys-recruitment-information-update.component.html'
})
export class SysRecruitmentInformationUpdateComponent implements OnInit {
    private _sysRecruitmentInformation: ISysRecruitmentInformation;
    isSaving: boolean;

    createdDate: string;
    lastModifiedDate: string;
    descriptionEditor: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private sysRecruitmentInformationService: SysRecruitmentInformationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysRecruitmentInformation }) => {
            this.sysRecruitmentInformation = sysRecruitmentInformation;
            this.initDescriptionEditor(this);
        });
    }

    initDescriptionEditor(obj) {
        const E = window['wangEditor'];
        obj.descriptionEditor = new E('#description-editor');
        obj.descriptionEditor.customConfig.uploadImgServer = '/api/static/upload-sys-recruitment-information-pics';
        obj.descriptionEditor.customConfig.uploadFileName = 'file';
        obj.descriptionEditor.customConfig.uploadImgMaxSize = 1 * 1024 * 1024;
        obj.descriptionEditor.create();
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.sysRecruitmentInformation.description = this.descriptionEditor.txt.html();
        this.isSaving = true;
        this.sysRecruitmentInformation.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.sysRecruitmentInformation.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.sysRecruitmentInformation.id !== undefined) {
            this.subscribeToSaveResponse(this.sysRecruitmentInformationService.update(this.sysRecruitmentInformation));
        } else {
            this.subscribeToSaveResponse(this.sysRecruitmentInformationService.create(this.sysRecruitmentInformation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISysRecruitmentInformation>>) {
        result.subscribe(
            (res: HttpResponse<ISysRecruitmentInformation>) => this.onSaveSuccess(),
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    get sysRecruitmentInformation() {
        return this._sysRecruitmentInformation;
    }

    set sysRecruitmentInformation(sysRecruitmentInformation: ISysRecruitmentInformation) {
        this._sysRecruitmentInformation = sysRecruitmentInformation;
        this.createdDate = moment(sysRecruitmentInformation.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(sysRecruitmentInformation.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
