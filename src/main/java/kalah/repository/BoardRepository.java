package kalah.repository;

import kalah.model.BoardFactory;
import kalah.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Repository keeps the game boards.
 * Boards are kept into ehcache cache.
 */
@Repository
@CacheConfig(cacheNames = "boards")
public class BoardRepository {

	private final BoardFactory boardFactory;

	@Autowired
	public BoardRepository(BoardFactory boardFactory) {
		this.boardFactory = boardFactory;
	}

	@Cacheable
	public Board findBoard(String boardId) {
		return boardFactory.generate();
	}

	@CacheEvict(value = "boards", allEntries = true)
	public void clearCache() {
		// Empty method, @CacheEvict annotation does everything
	}
}
