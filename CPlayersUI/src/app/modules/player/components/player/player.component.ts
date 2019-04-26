import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PARAMETERS } from '@angular/core/src/util/decorators';
import { PlayerService } from '../../player.service';
import { parseIntAutoRadix } from '@angular/common/src/i18n/format_number';
import { PlayerDetails } from '../../playerdetails';

@Component({
  selector: 'player-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {

   id:number;
   playerDetail: PlayerDetails;
  constructor(private route:ActivatedRoute, private router:Router,private playerService:PlayerService) { }

   
  ngOnInit() {
    this.route.params.subscribe(params =>
      
         this.id = params['player.pid']   
      )
   console.log(this.id) ;
    
   this.getPlayerDetails();
 
  }

  getPlayerDetails()
  {
      this.playerService.getPlayerDetails(this.id).subscribe(
        playerDetail =>{
          
           console.log(playerDetail);
        }
      )   
    
  }
 
  
 



}
