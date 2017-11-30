import {Component, OnInit, NgZone} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {environment} from "../environments/environment";
import 'rxjs/Rx';
import {Http} from "@angular/http";
import {Tweet} from "./model/tweet";
import {ActivatedRoute} from "@angular/router";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor() {
    //this.qts = [];
    //this.zone = new NgZone({enableLongStackTrace: false});
  }

  ngOnInit(): void {
   /* this.route.params.subscribe(params => {
      if(params) {
        console.log("hello");
        console.log(params.get('key'));
      }
    })*/
  /*  this.route.queryParams.subscribe(params => {
      console.log("hello");
      console.log(params);
    })*/
/*      if (key && name && searchQuery) {
        const eventSource = new this.EventSource(environment.url + "/api/stream/" + key + "/" + name + "/" + searchQuery);
        this.http.get(environment.url + "/api/subscribe/tweet/" + key + "/" + name + "/" + searchQuery).subscribe(user => console.log(user));
        this.qts = [];
        this.quotes = Observable.of(this.qts);

        eventSource.onmessage = event => {
          let data: Tweet = JSON.parse(event.data);
          console.log(event.data);
          //this.qts.push(new Tweet(data., data.pict));
          //this.zone.run(() => this.quotes = Observable.of(this.qts));
          this.zone.run(() => console.log(event.data));
        };
      }*/
  }

}
