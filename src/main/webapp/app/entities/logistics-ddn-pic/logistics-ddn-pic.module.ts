import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    LogisticsDdnPicComponent,
    LogisticsDdnPicDetailComponent,
    LogisticsDdnPicUpdateComponent,
    LogisticsDdnPicDeletePopupComponent,
    LogisticsDdnPicDeleteDialogComponent,
    logisticsDdnPicRoute,
    logisticsDdnPicPopupRoute
} from './';

const ENTITY_STATES = [...logisticsDdnPicRoute, ...logisticsDdnPicPopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LogisticsDdnPicComponent,
        LogisticsDdnPicDetailComponent,
        LogisticsDdnPicUpdateComponent,
        LogisticsDdnPicDeleteDialogComponent,
        LogisticsDdnPicDeletePopupComponent
    ],
    entryComponents: [
        LogisticsDdnPicComponent,
        LogisticsDdnPicUpdateComponent,
        LogisticsDdnPicDeleteDialogComponent,
        LogisticsDdnPicDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlLogisticsDdnPicModule {}
