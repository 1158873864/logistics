import { Moment } from 'moment';
import { ILogisticsDdn } from 'app/shared/model//logistics-ddn.model';

export const enum Status {
    ENABLE = 'ENABLE',
    DISABLE = 'DISABLE'
}

export interface ILogisticsDdnPic {
    id?: number;
    title?: string;
    path?: string;
    remark?: string;
    status?: Status;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
    logisticsDdn?: ILogisticsDdn;
}

export class LogisticsDdnPic implements ILogisticsDdnPic {
    constructor(
        public id?: number,
        public title?: string,
        public path?: string,
        public remark?: string,
        public status?: Status,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment,
        public logisticsDdn?: ILogisticsDdn
    ) {}
}
