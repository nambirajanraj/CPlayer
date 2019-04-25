package com.stackroute.favouriteservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.repository.PlayerRepository;



public class PlayerServiceImplTest {
	
	@Mock
	PlayerRepository playerRepository;
	
	private transient Player player;
	
	@InjectMocks
	private transient PlayerServiceImpl playerServiceImpl;
	
	private transient Optional<Player> optionalPlayer;	
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		player=new Player(1,2,"abcd","user");
		optionalPlayer=Optional.of(player);
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull("JpaRepository creation failed: use @InjectMocks on playerServiceImpl", player);
	}
	
	@Test
	public void testSavePlayerSuccess() throws PlayerAlreadyExistsException {
		when(playerRepository.save(player)).thenReturn(player);
		final boolean flag=playerServiceImpl.savePlayer(player);
		assertTrue("Player creation failed",flag);
		verify(playerRepository,times(1)).save(player);
		verify(playerRepository,times(1)).findById(player.getId());
	}
	
	@Test(expected = PlayerAlreadyExistsException.class)
	public void testSavePlayerFailure() throws PlayerAlreadyExistsException {
		when(playerRepository.findById(player.getId())).thenReturn(optionalPlayer);
		when(playerRepository.save(player)).thenReturn(player);	
		final boolean flag=playerServiceImpl.savePlayer(player);
		assertFalse("Saving player failed",flag);
		verify(playerRepository,times(1)).findById(player.getId());
	}


	
	@Test
	public void testDeletePlayerById() throws PlayerNotFoundException {
		when(playerRepository.findById(player.getId())).thenReturn(optionalPlayer);
		doNothing().when(playerRepository).delete(player);
		final boolean flag=playerServiceImpl.deletePlayerById(player.getId());
		assertTrue("Deleting Player failed",flag);
		verify(playerRepository,times(1)).delete(player);
		verify(playerRepository,times(1)).findById(player.getId());
	}

	@Test
	public void testGetPlayerById() throws PlayerNotFoundException {
		when(playerRepository.findById(player.getId())).thenReturn(optionalPlayer);
		final Player player1=playerServiceImpl.getPlayerById(1);
		assertEquals("Fetching movie by id failed",player1,player);
		verify(playerRepository,times(1)).findById(player.getId());	
	}

	@Test
	public void testGetAllPlayers() throws PlayerNotFoundException {
		
        List<Player> playerList=new ArrayList<Player>(1);
		
		when(playerRepository.findByUserId("user")).thenReturn(playerList);
		final List<Player> playerList1=playerServiceImpl.getMyPlayers("user");
		assertEquals("Fetching all playerss failed",playerList,playerList1);
		verify(playerRepository,times(1)).findByUserId("user");
	}


}
