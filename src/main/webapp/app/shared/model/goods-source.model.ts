import { Moment } from 'moment';
import { IUserInfo } from 'app/shared/model//user-info.model';

export const enum GoodsSourcePacking {
    MX = 'MX',
    MJ = 'MJ',
    ZX = 'ZX',
    TP = 'TP',
    KZH = 'KZH'
}

export const enum GoodsSourceFreight {
    CKYJ = 'CKYJ',
    DY = 'DY'
}

export const enum GoodsSourceProperty {
    ZH = 'ZH',
    QH = 'QH',
    SB = 'SB',
    HG = 'HG',
    OTHER = 'OTHER'
}

export const enum Status {
    ENABLE = 'ENABLE',
    DISABLE = 'DISABLE'
}

export interface IGoodsSource {
    id?: number;
    name?: string;
    start?: string;
    end?: string;
    ton?: string;
    volume?: string;
    goodsSourcePacking?: GoodsSourcePacking;
    mobilePhone?: string;
    goodsSourceFreight?: GoodsSourceFreight;
    goodsSourceProperty?: GoodsSourceProperty;
    effectiveDate?: Moment;
    layTime?: Moment;
    status?: Status;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
    userInfo?: IUserInfo;
}

export class GoodsSource implements IGoodsSource {
    constructor(
        public id?: number,
        public name?: string,
        public start?: string,
        public end?: string,
        public ton?: string,
        public volume?: string,
        public goodsSourcePacking?: GoodsSourcePacking,
        public mobilePhone?: string,
        public goodsSourceFreight?: GoodsSourceFreight,
        public goodsSourceProperty?: GoodsSourceProperty,
        public effectiveDate?: Moment,
        public layTime?: Moment,
        public status?: Status,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment,
        public userInfo?: IUserInfo
    ) {}
}
