import { Moment } from 'moment';

export const enum Status {
    ENABLE = 'ENABLE',
    DISABLE = 'DISABLE'
}

export interface ILogisticsDdn {
    id?: number;
    title?: string;
    managerFullname?: string;
    managerMobilePhone?: string;
    managerPhone?: string;
    businessPhone?: string;
    locationCity?: string;
    address?: string;
    pic?: string;
    coverCity?: string;
    throughCity?: string;
    www?: string;
    specialTransport?: boolean;
    good?: boolean;
    goThereAndback?: boolean;
    auth?: boolean;
    home?: boolean;
    banner?: boolean;
    vip?: boolean;
    status?: Status;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
}

export class LogisticsDdn implements ILogisticsDdn {
    constructor(
        public id?: number,
        public title?: string,
        public managerFullname?: string,
        public managerMobilePhone?: string,
        public managerPhone?: string,
        public businessPhone?: string,
        public locationCity?: string,
        public address?: string,
        public pic?: string,
        public coverCity?: string,
        public throughCity?: string,
        public www?: string,
        public specialTransport?: boolean,
        public good?: boolean,
        public goThereAndback?: boolean,
        public auth?: boolean,
        public home?: boolean,
        public banner?: boolean,
        public vip?: boolean,
        public status?: Status,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment
    ) {
        this.specialTransport = this.specialTransport || false;
        this.good = this.good || false;
        this.goThereAndback = this.goThereAndback || false;
        this.auth = this.auth || false;
        this.home = this.home || false;
        this.banner = this.banner || false;
        this.vip = this.vip || false;
    }
}
