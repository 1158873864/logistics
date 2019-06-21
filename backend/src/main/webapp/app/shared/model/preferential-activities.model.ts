import { Moment } from 'moment';

export const enum Status {
    ENABLE = 'ENABLE',
    DISABLE = 'DISABLE'
}

export interface IPreferentialActivities {
    id?: number;
    name?: string;
    cover?: string;
    startDate?: Moment;
    endDate?: Moment;
    content?: string;
    status?: Status;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
}

export class PreferentialActivities implements IPreferentialActivities {
    constructor(
        public id?: number,
        public name?: string,
        public cover?: string,
        public startDate?: Moment,
        public endDate?: Moment,
        public content?: string,
        public status?: Status,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment
    ) {}
}
