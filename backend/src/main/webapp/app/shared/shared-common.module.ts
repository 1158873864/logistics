import { NgModule } from '@angular/core';

import { WlSharedLibsModule, FindLanguageFromKeyPipe, SafeHtmlPipe, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [WlSharedLibsModule],
    declarations: [FindLanguageFromKeyPipe, SafeHtmlPipe, JhiAlertComponent, JhiAlertErrorComponent],
    exports: [WlSharedLibsModule, FindLanguageFromKeyPipe, SafeHtmlPipe, JhiAlertComponent, JhiAlertErrorComponent]
})
export class WlSharedCommonModule {}
