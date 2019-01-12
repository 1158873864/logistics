import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    GoodsSourceComponent,
    GoodsSourceDetailComponent,
    GoodsSourceUpdateComponent,
    GoodsSourceDeletePopupComponent,
    GoodsSourceDeleteDialogComponent,
    goodsSourceRoute,
    goodsSourcePopupRoute
} from './';

const ENTITY_STATES = [...goodsSourceRoute, ...goodsSourcePopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GoodsSourceComponent,
        GoodsSourceDetailComponent,
        GoodsSourceUpdateComponent,
        GoodsSourceDeleteDialogComponent,
        GoodsSourceDeletePopupComponent
    ],
    entryComponents: [GoodsSourceComponent, GoodsSourceUpdateComponent, GoodsSourceDeleteDialogComponent, GoodsSourceDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlGoodsSourceModule {}
