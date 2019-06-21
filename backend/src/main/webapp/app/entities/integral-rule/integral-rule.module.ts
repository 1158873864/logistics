import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    IntegralRuleComponent,
    IntegralRuleDetailComponent,
    IntegralRuleUpdateComponent,
    IntegralRuleDeletePopupComponent,
    IntegralRuleDeleteDialogComponent,
    integralRuleRoute,
    integralRulePopupRoute
} from './';
import {WlGoodsSourceModule} from '../goods-source/goods-source.module';

const ENTITY_STATES = [...integralRuleRoute, ...integralRulePopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        IntegralRuleComponent,
        IntegralRuleDetailComponent,
        IntegralRuleUpdateComponent,
        IntegralRuleDeleteDialogComponent,
        IntegralRuleDeletePopupComponent
    ],
    entryComponents: [
        IntegralRuleComponent,
        IntegralRuleUpdateComponent,
        IntegralRuleDeleteDialogComponent,
        IntegralRuleDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlIntegralRuleModule {}
