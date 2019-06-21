import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import { WlAdminModule } from 'app/admin/admin.module';
import {
    IntegralChangeRecordComponent,
    IntegralChangeRecordDetailComponent,
    IntegralChangeRecordUpdateComponent,
    IntegralChangeRecordDeletePopupComponent,
    IntegralChangeRecordDeleteDialogComponent,
    integralChangeRecordRoute,
    integralChangeRecordPopupRoute
} from './';

const ENTITY_STATES = [...integralChangeRecordRoute, ...integralChangeRecordPopupRoute];

@NgModule({
    imports: [WlSharedModule, WlAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        IntegralChangeRecordComponent,
        IntegralChangeRecordDetailComponent,
        IntegralChangeRecordUpdateComponent,
        IntegralChangeRecordDeleteDialogComponent,
        IntegralChangeRecordDeletePopupComponent
    ],
    entryComponents: [
        IntegralChangeRecordComponent,
        IntegralChangeRecordUpdateComponent,
        IntegralChangeRecordDeleteDialogComponent,
        IntegralChangeRecordDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlIntegralChangeRecordModule {}
