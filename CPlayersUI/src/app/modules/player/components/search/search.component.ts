import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../../player.service';
import {Player} from '../../player';

@Component({
  selector: 'player-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  players: Array<Player>;
  constructor(private service:PlayerService) { }

  ngOnInit() {
     
  }

  onEnter(searchKey) {
    console.log(searchKey)
    this.service.searchPlayer(searchKey).subscribe(players => {
      this.players = players;
    })

    this.print();
  }

  print()
  {
          console.log(this.players);
  }
}
