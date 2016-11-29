package kalah;

import static org.junit.Assert.*;

import java.util.Map.Entry;

import kalah.factory.GameFactory;
import kalah.model.GameBoard;
import kalah.model.Player;
import kalah.model.Section;
import kalah.processor.GameProcessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestContextConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class GameProcessorTest {

	@Autowired
	private GameProcessor gameProcessor;
	
	@Autowired
	private GameFactory gameFactory;
	
	@Test
	public void testGameEnding() {
		GameBoard gameBoard = gameFactory.generateNewGame();
		//Update pit stone counts to zero
		for(Entry<Integer, Section[]> entry: gameBoard.getPitMap().entrySet()) {
			for(Section s: entry.getValue())
				s.setCountToZero();
		}
		gameBoard.getPitMap().get(Player._1.getId())[5].setNumOfStones(1);
		gameProcessor.process(gameBoard, Player._1.getId(), 5);
		
		assertTrue(gameBoard.isGameEnded());
	}
}
