package kalah;

import kalah.model.BoardFactory;
import kalah.model.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertNotNull;

/**
 * Created by sanver.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestContextConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class BoardFactoryTest {
    @Autowired
    private BoardFactory boardFactory;

    @Test
    public void testNewBoardCreation() {
        Board board = boardFactory.generate();
        assertNotNull(board);
    }
}
