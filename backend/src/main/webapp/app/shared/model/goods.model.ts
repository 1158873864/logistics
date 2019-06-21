import { Moment } from 'moment';

export const enum Status {
    ENABLE = 'ENABLE',
    DISABLE = 'DISABLE'
}

export interface IGoods {
    id?: number;
    name?: string;
    cover?: string;
    introduce?: string;
    payment?: string;
    integral?: number;
    total?: number;
    peopleCount?: number;
    status?: Status;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
}

export class Goods implements IGoods {
    constructor(
        public id?: number,
        public name?: string,
        public cover?: string,
        public introduce?: string,
        public payment?: string,
        public integral?: number,
        public total?: number,
        public peopleCount?: number,
        public status?: Status,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment
    ) {}
}
