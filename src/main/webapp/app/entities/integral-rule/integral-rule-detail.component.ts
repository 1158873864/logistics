import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIntegralRule } from 'app/shared/model/integral-rule.model';

@Component({
    selector: 'jhi-integral-rule-detail',
    templateUrl: './integral-rule-detail.component.html'
})
export class IntegralRuleDetailComponent implements OnInit {
    integralRule: IIntegralRule;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ integralRule }) => {
            this.integralRule = integralRule;
        });
    }

    previousState() {
        window.history.back();
    }
}
