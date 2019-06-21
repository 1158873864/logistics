import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    TopicFabulousComponent,
    TopicFabulousDetailComponent,
    TopicFabulousUpdateComponent,
    TopicFabulousDeletePopupComponent,
    TopicFabulousDeleteDialogComponent,
    topicFabulousRoute,
    topicFabulousPopupRoute
} from './';

const ENTITY_STATES = [...topicFabulousRoute, ...topicFabulousPopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TopicFabulousComponent,
        TopicFabulousDetailComponent,
        TopicFabulousUpdateComponent,
        TopicFabulousDeleteDialogComponent,
        TopicFabulousDeletePopupComponent
    ],
    entryComponents: [
        TopicFabulousComponent,
        TopicFabulousUpdateComponent,
        TopicFabulousDeleteDialogComponent,
        TopicFabulousDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlTopicFabulousModule {}
