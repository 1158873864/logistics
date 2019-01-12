import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { IntegralRule } from 'app/shared/model/integral-rule.model';
import { IntegralRuleService } from './integral-rule.service';
import { IntegralRuleComponent } from './integral-rule.component';
import { IntegralRuleDetailComponent } from './integral-rule-detail.component';
import { IntegralRuleUpdateComponent } from './integral-rule-update.component';
import { IntegralRuleDeletePopupComponent } from './integral-rule-delete-dialog.component';
import { IIntegralRule } from 'app/shared/model/integral-rule.model';

@Injectable({ providedIn: 'root' })
export class IntegralRuleResolve implements Resolve<IIntegralRule> {
    constructor(private service: IntegralRuleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((integralRule: HttpResponse<IntegralRule>) => integralRule.body));
        }
        return of(new IntegralRule());
    }
}

export const integralRuleRoute: Routes = [
    {
        path: 'integral-rule',
        component: IntegralRuleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.integralRule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'integral-rule/:id/view',
        component: IntegralRuleDetailComponent,
        resolve: {
            integralRule: IntegralRuleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.integralRule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'integral-rule/new',
        component: IntegralRuleUpdateComponent,
        resolve: {
            integralRule: IntegralRuleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.integralRule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'integral-rule/:id/edit',
        component: IntegralRuleUpdateComponent,
        resolve: {
            integralRule: IntegralRuleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.integralRule.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const integralRulePopupRoute: Routes = [
    {
        path: 'integral-rule/:id/delete',
        component: IntegralRuleDeletePopupComponent,
        resolve: {
            integralRule: IntegralRuleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wlApp.integralRule.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
