import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    LogisticsDdnComponent,
    LogisticsDdnDetailComponent,
    LogisticsDdnUpdateComponent,
    LogisticsDdnDeletePopupComponent,
    LogisticsDdnDeleteDialogComponent,
    logisticsDdnRoute,
    logisticsDdnPopupRoute
} from './';

const ENTITY_STATES = [...logisticsDdnRoute, ...logisticsDdnPopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LogisticsDdnComponent,
        LogisticsDdnDetailComponent,
        LogisticsDdnUpdateComponent,
        LogisticsDdnDeleteDialogComponent,
        LogisticsDdnDeletePopupComponent
    ],
    entryComponents: [
        LogisticsDdnComponent,
        LogisticsDdnUpdateComponent,
        LogisticsDdnDeleteDialogComponent,
        LogisticsDdnDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlLogisticsDdnModule {}
