import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysRecruitmentInformation } from 'app/shared/model/sys-recruitment-information.model';

@Component({
    selector: 'jhi-sys-recruitment-information-detail',
    templateUrl: './sys-recruitment-information-detail.component.html'
})
export class SysRecruitmentInformationDetailComponent implements OnInit {
    sysRecruitmentInformation: ISysRecruitmentInformation;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysRecruitmentInformation }) => {
            this.sysRecruitmentInformation = sysRecruitmentInformation;
        });
    }

    previousState() {
        window.history.back();
    }
}
