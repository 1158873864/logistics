import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    TopicViewedComponent,
    TopicViewedDetailComponent,
    TopicViewedUpdateComponent,
    TopicViewedDeletePopupComponent,
    TopicViewedDeleteDialogComponent,
    topicViewedRoute,
    topicViewedPopupRoute
} from './';

const ENTITY_STATES = [...topicViewedRoute, ...topicViewedPopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TopicViewedComponent,
        TopicViewedDetailComponent,
        TopicViewedUpdateComponent,
        TopicViewedDeleteDialogComponent,
        TopicViewedDeletePopupComponent
    ],
    entryComponents: [TopicViewedComponent, TopicViewedUpdateComponent, TopicViewedDeleteDialogComponent, TopicViewedDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlTopicViewedModule {}
