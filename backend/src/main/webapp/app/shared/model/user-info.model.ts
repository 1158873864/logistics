import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export const enum Status {
    ENABLE = 'ENABLE',
    DISABLE = 'DISABLE'
}

export interface IUserInfo {
    id?: number;
    photo?: string;
    fullname?: string;
    nickName?: string;
    mobilePhone?: string;
    integral?: number;
    registerDate?: Moment;
    registerSum?: string;
    lastLoginedDate?: Moment;
    goodsSourceCount?: number;
    openId?: string;
    status?: Status;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
    user?: IUser;
}

export class UserInfo implements IUserInfo {
    constructor(
        public id?: number,
        public photo?: string,
        public fullname?: string,
        public nickName?: string,
        public mobilePhone?: string,
        public integral?: number,
        public registerDate?: Moment,
        public registerSum?: string,
        public lastLoginedDate?: Moment,
        public goodsSourceCount?: number,
        public openId?: string,
        public status?: Status,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment,
        public user?: IUser
    ) {}
}
