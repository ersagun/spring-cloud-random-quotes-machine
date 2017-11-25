import {Component, OnInit, NgZone} from '@angular/core';
import {Quote} from "./Quote";
import {Observable} from "rxjs/Observable";
import {environment} from "../environments/environment";
import 'rxjs/Rx';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app works!';
  qts: Quote[];
  quotes: Observable<Quote[]>;
  EventSource: any = window['EventSource'];
  zone: NgZone;

  constructor() {
    this.qts = [];
    this.zone = new NgZone({enableLongStackTrace: false});
  }

  ngOnInit(): void {
    const eventSource = new this.EventSource(environment.url + "/subscribe/ers");
    this.qts = [];
    this.quotes = Observable.of(this.qts);
    eventSource.onmessage = event => {
      let data: Quote = JSON.parse(event.data);
      this.qts.push(new Quote(data.words, data.pict));
      this.zone.run(() => this.quotes = Observable.of(this.qts));
    };
  }
}
