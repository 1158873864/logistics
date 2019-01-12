import { Moment } from 'moment';
import { ILogisticsDdn } from 'app/shared/model//logistics-ddn.model';
import { IUserInfo } from 'app/shared/model//user-info.model';

export interface IUserDdnFavorites {
    id?: number;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
    ddn?: ILogisticsDdn;
    userInfo?: IUserInfo;
}

export class UserDdnFavorites implements IUserDdnFavorites {
    constructor(
        public id?: number,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment,
        public ddn?: ILogisticsDdn,
        public userInfo?: IUserInfo
    ) {}
}
