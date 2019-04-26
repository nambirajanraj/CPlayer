package com.stackroute.favouriteservice.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.service.PlayerService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api/playerservice")
@CrossOrigin
public class PlayerController {
	
	private PlayerService playerservice;
	
	@Autowired
	public PlayerController(PlayerService playerservice)
	{
		super();
		this.playerservice = playerservice;
	}
	
	//api for Post request of adding to favorites comes here
	@PostMapping
	public ResponseEntity<?> savePlayer(@RequestBody final Player player, final ServletRequest req, final ServletResponse res) {
		ResponseEntity<?> responseEntity;
		try {
			final HttpServletRequest request=(HttpServletRequest)req;
			final String authHeader=request.getHeader("authorization");
			final String token=authHeader.substring(7);
			final String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
			
			player.setUserId(userId);
			this.playerservice.savePlayer(player);
			responseEntity = new ResponseEntity<Player>(player, HttpStatus.CREATED);
		} catch (PlayerAlreadyExistsException e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}		
		return responseEntity;
		
	}
	
	
	//api for Get request of retrieving favorites of user
	@GetMapping
	public ResponseEntity<?> fetchAllPlayers(final ServletRequest req, final ServletResponse res)
	{
		ResponseEntity<?> responseEntity;
		final HttpServletRequest request=(HttpServletRequest)req;
		final String authHeader=request.getHeader("authorization");
		final String token=authHeader.split(" ")[1];
		final String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
	
		
		final List<Player> playerList=this.playerservice.getMyPlayers(userId);
		
		responseEntity=new ResponseEntity<List<Player>>(playerList,HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	// api for Get request of retrieving specific player by id 
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> fetchMovieById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		Player player=null;
		try {
			player = playerservice.getPlayerById(id);		
			responseEntity = new ResponseEntity<Player>(player, HttpStatus.OK);
		} 
		catch (PlayerNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
	}
	
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			playerservice.deletePlayerById(id);
			responseEntity = new ResponseEntity<String>("Player deleted successfully", HttpStatus.OK);
		} 
		catch (PlayerNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		
		return responseEntity;
	}
	
	

}
