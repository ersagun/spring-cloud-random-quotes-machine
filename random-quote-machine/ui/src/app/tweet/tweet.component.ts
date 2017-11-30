import {Component, NgZone, OnInit} from '@angular/core';
import {Http} from "@angular/http";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {Tweet} from "../model/tweet";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css']
})
export class TweetComponent implements OnInit {

  title = 'app works!';
  tweet: Tweet[];
  tweetObservable: Observable<Tweet[]>;
  EventSource: any = window['EventSource'];
  zone: NgZone;

  constructor(private http: Http, private route: ActivatedRoute) {
    this.zone = new NgZone({enableLongStackTrace: false});
    this.tweet = [];
  }

  ngOnInit(): void {
    let key = "";
    let name = "";
    let searchQuery = "";
    let eventSource: any;
    this.route.paramMap.subscribe(params => {
      key = params.get('key');
      name = params.get('name');
      searchQuery = params.get('searchQuery');
    });

    if (key && name && searchQuery) {
      console.log(key, name, searchQuery);
      eventSource = new this.EventSource(environment.url + "/api/stream/" + key + "/" + name + "/" + searchQuery);
      eventSource.open = (open) => {
        console.log("open");
      };
      eventSource.onmessage = (event) => {
        let data: Tweet = JSON.parse(event.data);
        console.log(event.data);
        this.tweet.push(data);
        this.zone.run(() => this.tweetObservable = Observable.of(this.tweet));
      };

      eventSource.oncomplete = (complete) => {
        eventSource.close();
      };
      eventSource.onerror = (error) => {
        console.log(error);
        eventSource.close();
      };

      if (eventSource) {
        setTimeout(() => {
          this.http.get(environment.url + "/api/subscribe/tweet/" + key + "/" + name + "/" + searchQuery).subscribe(user => console.log(user));
        }, 1000);
      }
    }

  }
}
