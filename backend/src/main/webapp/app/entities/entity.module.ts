import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { WlLogisticsDdnModule } from './logistics-ddn/logistics-ddn.module';
import { WlLogisticsDdnPicModule } from './logistics-ddn-pic/logistics-ddn-pic.module';
import { WlLogisticsDdnWWWModule } from './logistics-ddn-www/logistics-ddn-www.module';
import { WlGoodsSourceModule } from './goods-source/goods-source.module';
import { WlUserInfoModule } from './user-info/user-info.module';
import { WlMemberModule } from './member/member.module';
import { WlIntegralRuleModule } from './integral-rule/integral-rule.module';
import { WlIntegralChangeRecordModule } from './integral-change-record/integral-change-record.module';
import { WlGoodsModule } from './goods/goods.module';
import { WlGoodsExchangeModule } from './goods-exchange/goods-exchange.module';
import { WlSysRecruitmentInformationModule } from './sys-recruitment-information/sys-recruitment-information.module';
import { WlPreferentialActivitiesModule } from './preferential-activities/preferential-activities.module';
import { WlTopicModule } from './topic/topic.module';
import { WlTopicFabulousModule } from './topic-fabulous/topic-fabulous.module';
import { WlTopicCommentModule } from './topic-comment/topic-comment.module';
import { WlTopicForwardModule } from './topic-forward/topic-forward.module';
import { WlTopicViewedModule } from './topic-viewed/topic-viewed.module';
import { WlUserDdnFavoritesModule } from './user-ddn-favorites/user-ddn-favorites.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        WlLogisticsDdnModule,
        WlLogisticsDdnPicModule,
        WlLogisticsDdnWWWModule,
        WlGoodsSourceModule,
        WlUserInfoModule,
        WlMemberModule,
        WlIntegralRuleModule,
        WlIntegralChangeRecordModule,
        WlGoodsModule,
        WlGoodsExchangeModule,
        WlSysRecruitmentInformationModule,
        WlPreferentialActivitiesModule,
        WlTopicModule,
        WlTopicFabulousModule,
        WlTopicCommentModule,
        WlTopicForwardModule,
        WlTopicViewedModule,
        WlUserDdnFavoritesModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlEntityModule {}
