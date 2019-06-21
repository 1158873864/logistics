import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    PreferentialActivitiesComponent,
    PreferentialActivitiesDetailComponent,
    PreferentialActivitiesUpdateComponent,
    PreferentialActivitiesDeletePopupComponent,
    PreferentialActivitiesDeleteDialogComponent,
    preferentialActivitiesRoute,
    preferentialActivitiesPopupRoute
} from './';

const ENTITY_STATES = [...preferentialActivitiesRoute, ...preferentialActivitiesPopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PreferentialActivitiesComponent,
        PreferentialActivitiesDetailComponent,
        PreferentialActivitiesUpdateComponent,
        PreferentialActivitiesDeleteDialogComponent,
        PreferentialActivitiesDeletePopupComponent
    ],
    entryComponents: [
        PreferentialActivitiesComponent,
        PreferentialActivitiesUpdateComponent,
        PreferentialActivitiesDeleteDialogComponent,
        PreferentialActivitiesDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlPreferentialActivitiesModule {}
