package kalah.repository;

import kalah.factory.BoardFactory;
import kalah.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "boards")
public class BoardRepository {

	@Autowired
	private BoardFactory boardFactory;

	@Cacheable
	public Board findBoard(String boardId) {
		return boardFactory.generate();
	}

	@CacheEvict(value = "boards", allEntries = true)
	public void clearCache() {
		// Empty method, @CacheEvict annotation does everything
	}
}
