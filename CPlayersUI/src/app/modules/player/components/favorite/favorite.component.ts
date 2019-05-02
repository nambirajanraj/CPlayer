import { Component, OnInit } from '@angular/core';
import {Player} from '../../player';
import { PlayerService } from '../../player.service';
@Component({
  selector: 'player-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit {

  players: Array<Player>;
  useFavouriteListApi = true;

  constructor(private playerService: PlayerService) { 
    this.players =[]
  }

  ngOnInit() {
    this.playerService.getFavouriteListPlayers().subscribe(players =>
       {
          this.players.push(...players);
       }
      
      )
  }

}
