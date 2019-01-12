import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    UserDdnFavoritesComponent,
    UserDdnFavoritesDetailComponent,
    UserDdnFavoritesUpdateComponent,
    UserDdnFavoritesDeletePopupComponent,
    UserDdnFavoritesDeleteDialogComponent,
    userDdnFavoritesRoute,
    userDdnFavoritesPopupRoute
} from './';

const ENTITY_STATES = [...userDdnFavoritesRoute, ...userDdnFavoritesPopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UserDdnFavoritesComponent,
        UserDdnFavoritesDetailComponent,
        UserDdnFavoritesUpdateComponent,
        UserDdnFavoritesDeleteDialogComponent,
        UserDdnFavoritesDeletePopupComponent
    ],
    entryComponents: [
        UserDdnFavoritesComponent,
        UserDdnFavoritesUpdateComponent,
        UserDdnFavoritesDeleteDialogComponent,
        UserDdnFavoritesDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlUserDdnFavoritesModule {}
