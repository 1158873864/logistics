import { Moment } from 'moment';
import { IUserInfo } from 'app/shared/model//user-info.model';
import { ITopic } from 'app/shared/model//topic.model';

export const enum Status {
    ENABLE = 'ENABLE',
    DISABLE = 'DISABLE'
}

export interface ITopic {
    id?: number;
    title?: string;
    content?: string;
    published?: Moment;
    fabulousCount?: number;
    commentCount?: number;
    forwardCount?: number;
    viewedCount?: number;
    forwarded?: boolean;
    status?: Status;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
    userInfo?: IUserInfo;
    source?: ITopic;
}

export class Topic implements ITopic {
    constructor(
        public id?: number,
        public title?: string,
        public content?: string,
        public published?: Moment,
        public fabulousCount?: number,
        public commentCount?: number,
        public forwardCount?: number,
        public viewedCount?: number,
        public forwarded?: boolean,
        public status?: Status,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment,
        public userInfo?: IUserInfo,
        public source?: ITopic
    ) {
        this.forwarded = this.forwarded || false;
    }
}
