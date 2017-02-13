package kalah.factory;

import kalah.model.Board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BoardFactory {

	@Value("${initialStoneCount}")
	private int initialStoneCount;
	
	public Board generate() {
		return new Board(initialStoneCount);
	}
	
}
