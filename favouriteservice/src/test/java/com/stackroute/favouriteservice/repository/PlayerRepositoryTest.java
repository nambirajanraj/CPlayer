package com.stackroute.favouriteservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.domain.Player;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class PlayerRepositoryTest {
	

	@Autowired
	private transient PlayerRepository playerRepo;

	public void setMovieRepository(PlayerRepository playerRepo) {
		this.playerRepo = playerRepo;
	}
	
	@Test
	public void testSavePlayer() throws Exception {
		Player pl =playerRepo.save(new Player(1, 2, "abcd","user"));
		final Player player=playerRepo.findById(pl.getId()).get();
		assertThat(player.getId()).isEqualTo(pl.getId());
	}
	
	
	
	@Test
	public void testDeletePlayer() throws Exception {
		Player pl = playerRepo.save(new Player(1,2,"abcd","user"));
		
		final Player player=playerRepo.findById(pl.getId()).get();		
		assertThat(player.getName()).isEqualTo("abcd");
		
		playerRepo.delete(player);
		Assert.assertEquals(Optional.empty(),playerRepo.findById(1));
	}
	
	@Test
	public void testGetPlayer() throws Exception {
		Player mov = playerRepo.save(new Player(1,2,"abcd","user"));
		final Player player= playerRepo.findById(mov.getId()).get();		
		Assert.assertEquals(player.getName(),"abcd");
	}
	
	@Test
	public void testGetMyPlayers() throws Exception {
		playerRepo.save(new Player(0,1,"abcd","user"));
		playerRepo.save(new Player(2,2,"efgh","user"));
		
		final List<Player> playerList= playerRepo.findByUserId("user");
		Assert.assertEquals(playerList.get(1).getName(),"efgh");
	}
}
