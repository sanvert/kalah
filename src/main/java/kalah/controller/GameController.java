package kalah.controller;

import kalah.model.GameBoard;
import kalah.model.Move;
import kalah.service.GameService;

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
	
	@RequestMapping(value = "/game", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<GameBoard> getGame(@RequestBody String user) {
		return ResponseEntity
						.status(HttpStatus.ACCEPTED)
						.body(gameService.getGame(user));
	}
	
	@RequestMapping(value = "/game/play", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity play(@RequestBody Move move) {
		
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
