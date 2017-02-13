package kalah.controller;

import kalah.model.Board;
import kalah.model.Move;
import kalah.model.ValidationResult;
import kalah.service.GameService;
import kalah.validator.InputValidatorChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private InputValidatorChain inputValidatorChain;
	
	@RequestMapping(value="/newBoard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Board> newBoard(@RequestBody String user) {
		return ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body(gameService.newBoard(user));
	}
	
	@RequestMapping(value="/currentBoard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Board> currentBoard(@RequestBody String user) {
		return ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body(gameService.getBoard(user));
	}
	
	@RequestMapping(value="/play", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity play(@RequestBody Move move) {
		
		ValidationResult validationResult = inputValidatorChain.validateInput(gameService.getBoard(move.getUser()), move);
		if(!validationResult.getMessage().equals(InputValidatorChain.VALID_MSG)) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(validationResult);
		}
		
		Board afterMove = gameService.play(move);
		
		if(afterMove==null) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.build();
		}
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(afterMove);
		
	}	
}
