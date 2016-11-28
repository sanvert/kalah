package kalah.controller;

import kalah.model.GameBoard;
import kalah.model.Move;
import kalah.model.ValidationResult;
import kalah.service.GameService;
import kalah.validator.InputValidatorChain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class GameController {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private InputValidatorChain inputValidatorChain;
	
	@RequestMapping(value = "/currentGame", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<GameBoard> getGame(@RequestBody String user) {
		return ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body(gameService.getGame(user));
	}
	
	@RequestMapping(value = "/game/play", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity play(@RequestBody Move move) {
		
		ValidationResult validationResult = inputValidatorChain.validateInput(gameService.getUserBoard(move.getUser()), move);
		if(!validationResult.getMessage().equals(InputValidatorChain.VALID_MSG)) {
			return ResponseEntity//<String>(validationResult, HttpStatus.BAD_REQUEST);
					.status(HttpStatus.BAD_REQUEST)
					.body(validationResult);
			//JSONObject.stringToValue("\\{validationResult:" + validationResult + "\\}")
		}
		
		GameBoard afterPlay = gameService.play(move);
		
		if(afterPlay==null) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.build();
		}
		
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(gameService.play(move));
		
	}	
}
