import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGoodsExchange } from 'app/shared/model/goods-exchange.model';

@Component({
    selector: 'jhi-goods-exchange-detail',
    templateUrl: './goods-exchange-detail.component.html'
})
export class GoodsExchangeDetailComponent implements OnInit {
    goodsExchange: IGoodsExchange;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ goodsExchange }) => {
            this.goodsExchange = goodsExchange;
        });
    }

    previousState() {
        window.history.back();
    }
}
