package kalah;

import kalah.factory.BoardFactory;
import kalah.model.Board;
import kalah.repository.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestContextConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardFactory boardFactory;
	
	@Test
	public void testGameRepository() {
		String generatedBoardId = "b1";
		Board board = boardFactory.generate();
		boardRepository.insertBoard(generatedBoardId, board);
		assertSame(board, boardRepository.findBoard(generatedBoardId));
	}
	
}
