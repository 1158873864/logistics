import { Moment } from 'moment';
import { ITopic } from 'app/shared/model//topic.model';
import { IUserInfo } from 'app/shared/model//user-info.model';

export interface ITopicForward {
    id?: number;
    oDateTime?: Moment;
    topic?: ITopic;
    userInfo?: IUserInfo;
}

export class TopicForward implements ITopicForward {
    constructor(public id?: number, public oDateTime?: Moment, public topic?: ITopic, public userInfo?: IUserInfo) {}
}
