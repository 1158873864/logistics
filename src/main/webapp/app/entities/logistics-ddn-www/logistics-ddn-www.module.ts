import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    LogisticsDdnWWWComponent,
    LogisticsDdnWWWDetailComponent,
    LogisticsDdnWWWUpdateComponent,
    LogisticsDdnWWWDeletePopupComponent,
    LogisticsDdnWWWDeleteDialogComponent,
    logisticsDdnWWWRoute,
    logisticsDdnWWWPopupRoute
} from './';

const ENTITY_STATES = [...logisticsDdnWWWRoute, ...logisticsDdnWWWPopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LogisticsDdnWWWComponent,
        LogisticsDdnWWWDetailComponent,
        LogisticsDdnWWWUpdateComponent,
        LogisticsDdnWWWDeleteDialogComponent,
        LogisticsDdnWWWDeletePopupComponent
    ],
    entryComponents: [
        LogisticsDdnWWWComponent,
        LogisticsDdnWWWUpdateComponent,
        LogisticsDdnWWWDeleteDialogComponent,
        LogisticsDdnWWWDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlLogisticsDdnWWWModule {}
