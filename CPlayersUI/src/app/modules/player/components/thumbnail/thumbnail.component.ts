import { Component, OnInit, Input, Output } from '@angular/core';
import { Player } from '../../player';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'player-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  player:Player;

  @Output()
  addPlayer = new EventEmitter();
  
  @Input()
  useFavouriteListApi:boolean;

  @Output()
  deletePlayer = new EventEmitter();

  constructor() { 
    
  }

  ngOnInit() {
  }

  addToFavouriteList(){
    this.addPlayer.emit(this.player);
  }

  deleteFromFavouriteList(){
     this.deletePlayer.emit(this.player);
  }

}
