import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    GoodsExchangeComponent,
    GoodsExchangeDetailComponent,
    GoodsExchangeUpdateComponent,
    GoodsExchangeDeletePopupComponent,
    GoodsExchangeDeleteDialogComponent,
    goodsExchangeRoute,
    goodsExchangePopupRoute
} from './';

const ENTITY_STATES = [...goodsExchangeRoute, ...goodsExchangePopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GoodsExchangeComponent,
        GoodsExchangeDetailComponent,
        GoodsExchangeUpdateComponent,
        GoodsExchangeDeleteDialogComponent,
        GoodsExchangeDeletePopupComponent
    ],
    entryComponents: [
        GoodsExchangeComponent,
        GoodsExchangeUpdateComponent,
        GoodsExchangeDeleteDialogComponent,
        GoodsExchangeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlGoodsExchangeModule {}
