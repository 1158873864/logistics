import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IMember } from 'app/shared/model/member.model';
import { MemberService } from './member.service';
import { IUserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from 'app/entities/user-info';

@Component({
    selector: 'jhi-member-update',
    templateUrl: './member-update.component.html'
})
export class MemberUpdateComponent implements OnInit {
    private _member: IMember;
    isSaving: boolean;

    userinfos: IUserInfo[];
    startDate: string;
    endDate: string;
    createdDate: string;
    lastModifiedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private memberService: MemberService,
        private userInfoService: UserInfoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ member }) => {
            this.member = member;
        });
        this.userInfoService.query({ filter: 'member-is-null' }).subscribe(
            (res: HttpResponse<IUserInfo[]>) => {
                if (!this.member.userInfo || !this.member.userInfo.id) {
                    this.userinfos = res.body;
                } else {
                    this.userInfoService.find(this.member.userInfo.id).subscribe(
                        (subRes: HttpResponse<IUserInfo>) => {
                            this.userinfos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.member.startDate = moment(this.startDate, DATE_TIME_FORMAT);
        this.member.endDate = moment(this.endDate, DATE_TIME_FORMAT);
        this.member.createdDate = moment(this.createdDate, DATE_TIME_FORMAT);
        this.member.lastModifiedDate = moment(this.lastModifiedDate, DATE_TIME_FORMAT);
        if (this.member.id !== undefined) {
            this.subscribeToSaveResponse(this.memberService.update(this.member));
        } else {
            this.subscribeToSaveResponse(this.memberService.create(this.member));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMember>>) {
        result.subscribe((res: HttpResponse<IMember>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get member() {
        return this._member;
    }

    set member(member: IMember) {
        this._member = member;
        this.startDate = moment(member.startDate).format(DATE_TIME_FORMAT);
        this.endDate = moment(member.endDate).format(DATE_TIME_FORMAT);
        this.createdDate = moment(member.createdDate).format(DATE_TIME_FORMAT);
        this.lastModifiedDate = moment(member.lastModifiedDate).format(DATE_TIME_FORMAT);
    }
}
