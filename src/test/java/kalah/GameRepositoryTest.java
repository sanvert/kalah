package kalah;

import static org.junit.Assert.assertSame;
import kalah.factory.GameFactory;
import kalah.model.GameBoard;
import kalah.repository.GameRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestContextConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class GameRepositoryTest {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private GameFactory gameFactory;
	
	@Test
	public void testGameRepository() {
		String userId = "user1";
		GameBoard gameBoard = gameFactory.generateNewGame();
		gameRepository.insertGameBoard(userId, gameBoard);
		assertSame(gameBoard, gameRepository.findGameBoard(userId));
	}
	
}
