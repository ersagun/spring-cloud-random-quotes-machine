import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {TweetComponent} from "./tweet/tweet.component";

const routes : Routes = [
  {path: 'tweet/:key/:name/:searchQuery', component: TweetComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})

export class AppRoutingModule { }
