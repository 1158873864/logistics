import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    TopicForwardComponent,
    TopicForwardDetailComponent,
    TopicForwardUpdateComponent,
    TopicForwardDeletePopupComponent,
    TopicForwardDeleteDialogComponent,
    topicForwardRoute,
    topicForwardPopupRoute
} from './';

const ENTITY_STATES = [...topicForwardRoute, ...topicForwardPopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TopicForwardComponent,
        TopicForwardDetailComponent,
        TopicForwardUpdateComponent,
        TopicForwardDeleteDialogComponent,
        TopicForwardDeletePopupComponent
    ],
    entryComponents: [
        TopicForwardComponent,
        TopicForwardUpdateComponent,
        TopicForwardDeleteDialogComponent,
        TopicForwardDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlTopicForwardModule {}
