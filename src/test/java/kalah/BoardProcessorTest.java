package kalah;

import kalah.model.BoardFactory;
import kalah.model.Board;
import kalah.model.Player;
import kalah.model.Section;
import kalah.processor.BoardProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Map.Entry;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestContextConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class BoardProcessorTest {

	@Autowired
	private BoardProcessor boardProcessor;
	
	@Autowired
	private BoardFactory boardFactory;
	
	@Test
	public void testEndingGame() {
		Board board = boardFactory.generate();
		//Update pit stone counts to zero
		for(Entry<Integer, Section[]> entry: board.getPitMap().entrySet()) {
			for(Section s: entry.getValue())
				s.setCountToZero();
		}
		board.getPitMap().get(Player._1.getId())[5].setNumOfStones(1);
		boardProcessor.process(board, Player._1.getId(), 5);
		
		assertTrue(board.isGameEnded());
	}
}
