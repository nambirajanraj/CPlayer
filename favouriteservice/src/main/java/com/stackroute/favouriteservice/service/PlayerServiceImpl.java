package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.repository.PlayerRepository;


@Service
public class PlayerServiceImpl implements PlayerService {

	
	private final transient PlayerRepository playerrepository;
	
	@Autowired
	public PlayerServiceImpl(final PlayerRepository playerrepository)
	{
		super();
		this.playerrepository=playerrepository;
	}
	
	
	
	
	
	//Service method to get player by id
	@Override
	public Player getPlayerById(final int id) throws PlayerNotFoundException {
		
		
		final Player player =playerrepository.findById(id).orElse(null);
		
		if(player == null)
		{
			throw new PlayerNotFoundException("Player Not found");
			
		}
		
		return player;
		
	}

	
	//Save Player details to database
	@Override
	public boolean savePlayer(Player player) throws PlayerAlreadyExistsException {
		// TODO Auto-generated method stub
		final Optional<Player> playerobj=playerrepository.findById(player.getId());
		
		if(playerobj.isPresent())
		{
			throw new PlayerAlreadyExistsException("Could not save player ,player already exists");
			
		}
		
		playerrepository.save(player);
		
		return true;
	}

	
    //Get all players from database
	@Override
	public List<Player> getMyPlayers(String userId) {
		// TODO Auto-generated method stub
		return this.playerrepository.findByUserId(userId);
	}
	

	//Remove player from database
	@Override
	public boolean deletePlayerById(Integer id) throws PlayerNotFoundException {
		
		final Player player = playerrepository.findById(id).orElse(null);
		
		if(player == null){
			
			throw new PlayerNotFoundException("Could not delete , Player not found");
		}
		
		playerrepository.delete(player);
		
		return true;
	}

}
