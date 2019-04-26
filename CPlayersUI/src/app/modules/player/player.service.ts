import { Injectable } from '@angular/core';
import { HttpClient ,HttpHeaders} from '@angular/common/http';
import {map}  from 'rxjs/operators';
import {Observable} from 'rxjs/Observable';
import {retry} from 'rxjs/operators';
import {Player} from './player';
import { AuthenticationService } from '../authentication/authentication.service';
@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  apikey: string;
  cricapiEndpoint: string;
  imagePrefix: string;
  springEndpoint: string;
  constructor(private http:HttpClient , private authService:AuthenticationService) {
    this.cricapiEndpoint= "http://cricapi.com/api/playerFinder";
    this.apikey ="VigtGZYbP6ZscrufCRST4cEec1T2";
    this.imagePrefix ="https://www.cricapi.com/playerpic";
    this.springEndpoint ="http://localhost:8888/api/playerservice"
   }

  searchPlayer(searchKey: string): Observable<Array<Player>> {
    if (searchKey.length > 0) {
      const searchEndpoint = `${this.cricapiEndpoint}/?name=${searchKey}&apikey=${this.apikey}`;
      return this.http.get(searchEndpoint).pipe(
        
        retry(3),
        map(this.pickMovieResults),
        map(this.transformImagepath.bind(this))
      );
    }
  }

  pickMovieResults(response)
  {
    return response['data'];
  }

  transformImagepath(players): Observable<Array<Player>>
  {
      players.map(player =>{
         player.image_path =`${this.imagePrefix}/${player.pid}.jpg`;
           return player;
      });
      return players;
  }

  addPlayerToFavouriteList(player)
  {
      let token =this.authService.getToken();
      let uHeader = new HttpHeaders({'Authorization':`Bearer ${token}`});
      let options = {
        headers: uHeader
      }
      return this.http.post(this.springEndpoint,player,options);
  }

   getFavouriteListPlayers():Observable<Array<Player>>
   {
    let token =this.authService.getToken();
    let uHeader = new HttpHeaders({'Authorization':`Bearer ${token}`});
    let options = {
      headers: uHeader
    }

    return this.http.get<Array<Player>>(this.springEndpoint,options);
    
   }

   deleteFromMyFavouriteList(player:Player){
     
      const url = `${this.springEndpoint}/${player.id}`;
      let token =this.authService.getToken();
    let uHeader = new HttpHeaders({'Authorization':`Bearer ${token}`});
    let options = {
    
      headers: uHeader,
      responseType: 'text' as 'text'
    }

    return this.http.delete(url,options);

   }

  
}
