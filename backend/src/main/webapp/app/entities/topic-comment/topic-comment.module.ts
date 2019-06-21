import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WlSharedModule } from 'app/shared';
import {
    TopicCommentComponent,
    TopicCommentDetailComponent,
    TopicCommentUpdateComponent,
    TopicCommentDeletePopupComponent,
    TopicCommentDeleteDialogComponent,
    topicCommentRoute,
    topicCommentPopupRoute
} from './';

const ENTITY_STATES = [...topicCommentRoute, ...topicCommentPopupRoute];

@NgModule({
    imports: [WlSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TopicCommentComponent,
        TopicCommentDetailComponent,
        TopicCommentUpdateComponent,
        TopicCommentDeleteDialogComponent,
        TopicCommentDeletePopupComponent
    ],
    entryComponents: [
        TopicCommentComponent,
        TopicCommentUpdateComponent,
        TopicCommentDeleteDialogComponent,
        TopicCommentDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WlTopicCommentModule {}
