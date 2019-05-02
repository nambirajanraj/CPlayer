package com.stackroute.favouriteservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.domain.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {

	List<Player> findByUserId(String userId);
	
	Optional<Player> findByPidAndUserId(int pid,String userId);
}
