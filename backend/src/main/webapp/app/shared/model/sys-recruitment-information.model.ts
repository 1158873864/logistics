import { Moment } from 'moment';

export const enum Status {
    ENABLE = 'ENABLE',
    DISABLE = 'DISABLE'
}

export interface ISysRecruitmentInformation {
    id?: number;
    category?: string;
    categoryName?: string;
    nature?: string;
    salaryStart?: string;
    salaryEnd?: string;
    addrCity?: string;
    experience?: string;
    education?: string;
    peopleCount?: number;
    description?: string;
    status?: Status;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
}

export class SysRecruitmentInformation implements ISysRecruitmentInformation {
    constructor(
        public id?: number,
        public category?: string,
        public categoryName?: string,
        public nature?: string,
        public salaryStart?: string,
        public salaryEnd?: string,
        public addrCity?: string,
        public experience?: string,
        public education?: string,
        public peopleCount?: number,
        public description?: string,
        public status?: Status,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment
    ) {}
}
