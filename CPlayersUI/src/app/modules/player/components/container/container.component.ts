import { Component, OnInit, Input } from '@angular/core';
import { Player } from '@angular/core/src/render3/interfaces/player';
import {PlayerService} from '../../player.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'player-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  players:Array<Player>;

  @Input()
  useFavouriteListApi: boolean;

  constructor(private playerService:PlayerService , private matSnackBar:MatSnackBar) { }

  ngOnInit() {
  }

  addToFavouriteList(player)
  {
    console.log(player);
  
    this.playerService.addPlayerToFavouriteList(player).subscribe((player)=>{
   this.matSnackBar.open("Player Added To Favouritelist",'',{
      duration:1000
    });
    })
    
  }

  deleteFromFavouriteList(player){
    console.log(player);
    let message = `${player.name} deleted from your watchlist`;
    
    this.playerService.deleteFromMyFavouriteList(player).subscribe((player)=>{
      this.matSnackBar.open(message,'',{
        duration:1000
      });
    });

     const index =this.players.indexOf(player);
     this.players.splice(index,1);
    
  }

}
