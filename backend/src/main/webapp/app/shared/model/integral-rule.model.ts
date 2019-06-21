import { Moment } from 'moment';

export const enum IntegralRuleType {
    INC = 'INC',
    REDUCE = 'REDUCE'
}

export interface IIntegralRule {
    id?: number;
    code?: string;
    name?: string;
    integralRuleType?: IntegralRuleType;
    value?: number;
    remark?: string;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
}

export class IntegralRule implements IIntegralRule {
    constructor(
        public id?: number,
        public code?: string,
        public name?: string,
        public integralRuleType?: IntegralRuleType,
        public value?: number,
        public remark?: string,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment
    ) {}
}
