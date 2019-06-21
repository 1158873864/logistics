import { Moment } from 'moment';
import { IUserInfo } from 'app/shared/model//user-info.model';
import { IIntegralRule } from 'app/shared/model//integral-rule.model';

export const enum IntegralRuleType {
    INC = 'INC',
    REDUCE = 'REDUCE'
}

export interface IIntegralChangeRecord {
    id?: number;
    integralRuleType?: IntegralRuleType;
    name?: string;
    value?: number;
    createdBy?: string;
    lastModifiedBy?: string;
    createdDate?: Moment;
    lastModifiedDate?: Moment;
    userInfo?: IUserInfo;
    integralRule?: IIntegralRule;
}

export class IntegralChangeRecord implements IIntegralChangeRecord {
    constructor(
        public id?: number,
        public integralRuleType?: IntegralRuleType,
        public name?: string,
        public value?: number,
        public createdBy?: string,
        public lastModifiedBy?: string,
        public createdDate?: Moment,
        public lastModifiedDate?: Moment,
        public userInfo?: IUserInfo,
        public integralRule?: IIntegralRule
    ) {}
}
