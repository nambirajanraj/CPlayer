import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { PlayerRouterRoutingModule } from './player-router-routing.module';
import { AuthGuardService } from 'src/app/auth-guard.service';
import { SearchComponent } from './components/search/search.component';
import { FavoriteComponent } from './components/favorite/favorite.component';

const playerRoutes: Routes=[
  {
      path: 'players',
      children:[
         {
           path:'',
           redirectTo:'/players/search',
           pathMatch: 'full',
           canActivate:[AuthGuardService]
         },
         {
           path:'search',
           component:SearchComponent,
           canActivate:[AuthGuardService],

         },
         {
           path:'favouriteList',
           component:FavoriteComponent,
           canActivate:[AuthGuardService]
         }
      ]

  }
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    PlayerRouterRoutingModule,
    RouterModule.forRoot(playerRoutes)
  ],
  exports:[RouterModule]
})
export class PlayerRouterModule { }
