import {Component, HostListener, NgZone, OnDestroy, OnInit} from '@angular/core';
import {Http} from "@angular/http";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {Tweet} from "../model/tweet";
import {environment} from "../../environments/environment";
import {TweetStats} from "../model/tweetStats";

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css']
})
export class TweetComponent implements OnInit, OnDestroy {
  ngOnDestroy(): void {
    this.http.get(environment.url + "/api/subscribe/tweet/" + this.key + "/" + this.name + "/" + this.searchQuery+"/stop").subscribe(user => console.log(user));
    this.eventSource.close();
  }


  //title = 'app works!';
  //tweet: Tweet[];
  tweetObservable: Observable<Tweet[]>;
  tweetStatsObservable: Observable<TweetStats[]>;
  EventSource: any = window['EventSource'];
  eventSource: any;
  zone: NgZone;
  percentageSentiment: Observable<number>;
  score: number;
  magnitude: number;

  key: string = "";
  name: string = "";
  searchQuery: string = "";


  constructor(private http: Http, private route: ActivatedRoute) {
    this.zone = new NgZone({enableLongStackTrace: false});
    this.tweetObservable = new Observable<Tweet[]>();
    this.tweetStatsObservable = new Observable<TweetStats[]>();
  }

  ngOnInit(): void {
    this.key = "";
    this.name = "";
    this.searchQuery = "";
    //let eventSource: any;
    this.route.paramMap.subscribe(params => {
      this.key = params.get('key');
      this.name = params.get('name');
      this.searchQuery = params.get('searchQuery');
    });
    if (this.key && this.name && this.searchQuery) {
      this.eventSource = new this.EventSource(environment.url + "/api/stream/" + this.key + "/" + this.name + "/" + this.searchQuery);
      if (this.eventSource) {
        this.tweetObservable =  this.getTweetStream()
        this.tweetStatsObservable = this.getTweetStatsStream()

        this.eventSource.oncomplete = (complete) => {
          this.http.get(environment.url + "/api/subscribe/tweet/" + this.key + "/" + this.name + "/" + this.searchQuery+"/stop").subscribe(user => console.log(user));
          this.eventSource.close();
        };
        this.eventSource.onerror = (error) => {
          console.log(error);
          this.http.get(environment.url + "/api/subscribe/tweet/" + this.key + "/" + this.name + "/" + this.searchQuery+"/stop").subscribe(user => console.log(user));
          this.eventSource.close();
        };

        this.eventSource.onopen = (open) => {
          this.http.get(environment.url + "/api/subscribe/tweet/" + this.key + "/" + this.name + "/" + this.searchQuery).subscribe(user => console.log(user));
        }


      }

      /*this.eventSource = new this.EventSource(environment.url + "/api/stream/" + key + "/" + name + "/" + searchQuery);
      this.eventSource.onmessage = (event) => {
        let tweet: Tweet = JSON.parse(event.data);
        let tweetStats: TweetStats = JSON.parse(event.data);
        console.log(event.data);
        this.tweet.push(tweet);
        this.zone.run(() => {
          this.tweetObservable = Observable.of(this.tweet);
          if (tweetStats.score && tweetStats.magnitude) {
            this.score = tweetStats.score;
            this.magnitude = tweetStats.magnitude;
          }
        });
      };

      this.eventSource.oncomplete = (complete) => {
        this.eventSource.close();
      };
      this.eventSource.onerror = (error) => {
        console.log(error);
        this.eventSource.close();
      };

      if (this.eventSource) {
        setTimeout(() => {
          this.http.get(environment.url + "/api/subscribe/tweet/" + key + "/" + name + "/" + searchQuery).subscribe(user => console.log(user));
        }, 2000);
      }
    }*/

    }
  }

  getTweetStream(): Observable<Tweet[]> {
    console.log("stream");
    console.log(this.tweetObservable)
    return Observable.create((observer) => {
      this.eventSource.onmessage = (event) => {
        let tweet: Tweet = JSON.parse(event.data);
        if (tweet) {
          this.zone.run(() => {
            observer.next(JSON.parse(event.data));
          });
        }
      };
    });
  }


  getTweetStatsStream(): Observable<TweetStats[]> {
    return Observable.create((observer) => {
      this.eventSource.onmessage = (event) => {
        let tweetStats: TweetStats = JSON.parse(event.data);
        if (tweetStats) {
          this.zone.run(() => {
            this.score = tweetStats.score;
            this.magnitude = tweetStats.magnitude;
            observer.next(JSON.parse(event.data));
          });
        }
      };
    });
  }

  @HostListener('window:unload', ['$event'])
  beforeunloadHandler(event) {
    this.http.get(environment.url + "/api/subscribe/tweet/" + this.key + "/" + this.name + "/" + this.searchQuery+"/stop").subscribe(user => console.log(user));
    this.eventSource.close();
  }
}
