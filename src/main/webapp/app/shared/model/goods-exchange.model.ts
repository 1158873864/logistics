import { Moment } from 'moment';
import { IGoods } from 'app/shared/model//goods.model';
import { IUserInfo } from 'app/shared/model//user-info.model';

export const enum ExchangeStatus {
    PROCESSING = 'PROCESSING',
    FAIL = 'FAIL',
    SUCCESS = 'SUCCESS'
}

export interface IGoodsExchange {
    id?: number;
    mobilePhone?: string;
    fullname?: string;
    address?: string;
    oddNumbers?: string;
    exchangeCount?: number;
    exchangeStatus?: ExchangeStatus;
    remark?: string;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
    goods?: IGoods;
    userInfo?: IUserInfo;
}

export class GoodsExchange implements IGoodsExchange {
    constructor(
        public id?: number,
        public mobilePhone?: string,
        public fullname?: string,
        public address?: string,
        public oddNumbers?: string,
        public exchangeCount?: number,
        public exchangeStatus?: ExchangeStatus,
        public remark?: string,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment,
        public goods?: IGoods,
        public userInfo?: IUserInfo
    ) {}
}
