package kalah.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public class UserGameRepository {
	private static HashMap<String, String> userGamesInMemory = new HashMap<String, String>();

	public String findGameBoard(String user) {
		return userGamesInMemory.get(user);
	}

	public void insertGameBoard(String user, String gameId) {
		if (user != null && gameId != null)
			userGamesInMemory.put(user, gameId);
	}
}
