import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    SysRecruitmentInformationComponent,
    SysRecruitmentInformationDetailComponent,
    SysRecruitmentInformationUpdateComponent,
    SysRecruitmentInformationDeletePopupComponent,
    SysRecruitmentInformationDeleteDialogComponent,
    sysRecruitmentInformationRoute,
    sysRecruitmentInformationPopupRoute
} from './';

const ENTITY_STATES = [...sysRecruitmentInformationRoute, ...sysRecruitmentInformationPopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysRecruitmentInformationComponent,
        SysRecruitmentInformationDetailComponent,
        SysRecruitmentInformationUpdateComponent,
        SysRecruitmentInformationDeleteDialogComponent,
        SysRecruitmentInformationDeletePopupComponent
    ],
    entryComponents: [
        SysRecruitmentInformationComponent,
        SysRecruitmentInformationUpdateComponent,
        SysRecruitmentInformationDeleteDialogComponent,
        SysRecruitmentInformationDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlSysRecruitmentInformationModule {}
