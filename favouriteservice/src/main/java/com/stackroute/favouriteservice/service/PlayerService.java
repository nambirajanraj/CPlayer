package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;

public interface PlayerService {
	
	public Player getPlayerById(final int id) throws PlayerNotFoundException;
	
	public boolean savePlayer(final Player player) throws PlayerAlreadyExistsException;
	
	
	public List<Player> getMyPlayers (String userId) ;
	
	public boolean deletePlayerById(final Integer id) throws PlayerNotFoundException;
	
	
	
	
	

}
