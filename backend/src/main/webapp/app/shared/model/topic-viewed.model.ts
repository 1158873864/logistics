import { Moment } from 'moment';
import { ITopic } from 'app/shared/model//topic.model';
import { IUserInfo } from 'app/shared/model//user-info.model';

export interface ITopicViewed {
    id?: number;
    oDateTime?: Moment;
    topic?: ITopic;
    userInfo?: IUserInfo;
}

export class TopicViewed implements ITopicViewed {
    constructor(public id?: number, public oDateTime?: Moment, public topic?: ITopic, public userInfo?: IUserInfo) {}
}
