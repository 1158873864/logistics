import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGoodsSource } from 'app/shared/model/goods-source.model';

@Component({
    selector: 'jhi-goods-source-detail',
    templateUrl: './goods-source-detail.component.html'
})
export class GoodsSourceDetailComponent implements OnInit {
    goodsSource: IGoodsSource;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ goodsSource }) => {
            this.goodsSource = goodsSource;
        });
    }

    previousState() {
        window.history.back();
    }
}
