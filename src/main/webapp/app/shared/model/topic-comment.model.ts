import { Moment } from 'moment';
import { ITopic } from 'app/shared/model//topic.model';
import { IUserInfo } from 'app/shared/model//user-info.model';

export interface ITopicComment {
    id?: number;
    content?: string;
    oDateTime?: Moment;
    topic?: ITopic;
    userInfo?: IUserInfo;
}

export class TopicComment implements ITopicComment {
    constructor(
        public id?: number,
        public content?: string,
        public oDateTime?: Moment,
        public topic?: ITopic,
        public userInfo?: IUserInfo
    ) {}
}
