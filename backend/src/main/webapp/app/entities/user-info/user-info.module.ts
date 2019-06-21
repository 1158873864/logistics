import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import { WlAdminModule } from 'app/admin/admin.module';
import {
    UserInfoComponent,
    UserInfoDetailComponent,
    UserInfoUpdateComponent,
    UserInfoDeletePopupComponent,
    UserInfoDeleteDialogComponent,
    userInfoRoute,
    userInfoPopupRoute
} from './';

const ENTITY_STATES = [...userInfoRoute, ...userInfoPopupRoute];

@NgModule({
    imports: [WlSharedModule, WlAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UserInfoComponent,
        UserInfoDetailComponent,
        UserInfoUpdateComponent,
        UserInfoDeleteDialogComponent,
        UserInfoDeletePopupComponent
    ],
    entryComponents: [UserInfoComponent, UserInfoUpdateComponent, UserInfoDeleteDialogComponent, UserInfoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlUserInfoModule {}
