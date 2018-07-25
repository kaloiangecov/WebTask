import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { LocationStrategy, PathLocationStrategy } from '@angular/common';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor(private injector: Injector) { }

  handleError(error) {
    const location = this.injector.get(LocationStrategy);
    const message = error.message ? error.message : error.toString();
    const url = location instanceof PathLocationStrategy ? location.path() : '';

    console.log({ message, url });
    // alert(JSON.stringify(error));
    // throw error;
  }
}
