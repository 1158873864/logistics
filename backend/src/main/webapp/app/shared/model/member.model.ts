import { Moment } from 'moment';
import { IUserInfo } from 'app/shared/model//user-info.model';

export interface IMember {
    id?: number;
    startDate?: Moment;
    endDate?: Moment;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
    userInfo?: IUserInfo;
}

export class Member implements IMember {
    constructor(
        public id?: number,
        public startDate?: Moment,
        public endDate?: Moment,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment,
        public userInfo?: IUserInfo
    ) {}
}
