package com.stackroute.favouriteservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.FavouriteServiceApplication;
import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.service.PlayerService;



@RunWith(SpringRunner.class)
@WebMvcTest(PlayerController.class)
@ContextConfiguration(classes=FavouriteServiceApplication.class)
public class PlayerControllerTest {
	
	public PlayerControllerTest() {
		// TODO Auto-generated constructor stub
	}
	

	@Autowired
	private transient MockMvc mvc;

	@MockBean
	private transient PlayerService playerService;

	private  Player player;

	@InjectMocks
	private PlayerController controller;
	private String token;
	private List<Player> movieList=new ArrayList<Player>();

	@Before
	public void setUp() {
		player=new Player(1,1234,"abcd","user");
		movieList.add(player);
		player=new Player(2,12345,"efgh","user");
		movieList.add(player);	
		
		token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE1NTYwOTM4MzN9.ReZpwlMPyP28cbKGskfLSaYD3fcSb6_xysVNOHYO1PQ";
	}

	@Test
	public void testSavePlayerSuccess() throws Exception {
		when(playerService.savePlayer(player)).thenReturn(true);
		mvc.perform(post("/api/playerservice").header("Authorization",token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(player))).andExpect(status().isCreated());
		verify(playerService,times(1)).savePlayer(Mockito.any(Player.class));
		verifyNoMoreInteractions(playerService);
	}


	@Test
	public void testDeletePlayerById() throws Exception {
		when(playerService.deletePlayerById(1)).thenReturn(true);
		mvc.perform(delete("/api/playerservice/{id}",1).header("Authorization", token)).andExpect(status().isOk());
		verify(playerService,times(1)).deletePlayerById(1);
		verifyNoMoreInteractions(playerService);
	}

	@Test
	public void testGetPlayerById() throws Exception {
		when(playerService.getPlayerById(1)).thenReturn(movieList.get(0));
		mvc.perform(get("/api/playerservice/{id}",1).header("Authorization", token)).andExpect(status().isOk());
		verify(playerService,times(1)).getPlayerById(1);
		verifyNoMoreInteractions(playerService);
	}

	@Test
	public void testGetMyPlayers() throws Exception {
		when(playerService.getMyPlayers("user")).thenReturn(movieList);
		mvc.perform(get("/api/playerservice").header("Authorization", token)).andExpect(status().isOk()).andDo(print());
		verify(playerService,times(1)).getMyPlayers("101");
		verifyNoMoreInteractions(playerService);
	}

	private String jsonToString(final Object obj) {
		String result;
		try{
			final ObjectMapper mapper=new ObjectMapper();
			final String  jsonContent=mapper.writeValueAsString(obj);
			result=jsonContent;
		}
		catch(JsonProcessingException e)
		{
			result="JsonProcessingException";
		}
		return  result;
	}

}
