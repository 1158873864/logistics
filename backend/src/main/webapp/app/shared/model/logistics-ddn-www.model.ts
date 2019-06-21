import { Moment } from 'moment';
import { ILogisticsDdn } from 'app/shared/model//logistics-ddn.model';

export const enum Status {
    ENABLE = 'ENABLE',
    DISABLE = 'DISABLE'
}

export interface ILogisticsDdnWWW {
    id?: number;
    name?: string;
    mobilePhone?: string;
    phone?: string;
    remark?: string;
    status?: Status;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
    logisticsDdn?: ILogisticsDdn;
    contacts?: string;
}

export class LogisticsDdnWWW implements ILogisticsDdnWWW {
    constructor(
        public id?: number,
        public name?: string,
        public mobilePhone?: string,
        public phone?: string,
        public remark?: string,
        public status?: Status,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment,
        public logisticsDdn?: ILogisticsDdn,
        public contacts?: string
    ) {}
}
