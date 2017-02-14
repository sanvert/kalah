package kalah.factory;

import kalah.model.Board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BoardFactory {

	@Value("${initialStoneCount:6}")
	private int initialStoneCount;
	
	public Board generate() {
		String boardId = UUID.randomUUID().toString();
		return new Board(boardId, initialStoneCount);
	}
	
}
