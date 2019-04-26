import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchComponent } from './components/search/search.component';
import {SharedModule} from '../shared/shared.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PlayerRouterModule } from './player-router.module';
import { PlayerService } from './player.service';
import { ContainerComponent } from './components/container/container.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { FavoriteComponent } from './components/favorite/favorite.component';

@NgModule({
  declarations: [SearchComponent, ContainerComponent, ThumbnailComponent, FavoriteComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    PlayerRouterModule,
    SharedModule
  ],

  providers:[PlayerService],
  exports:[SearchComponent]
})
export class PlayerModule { }
