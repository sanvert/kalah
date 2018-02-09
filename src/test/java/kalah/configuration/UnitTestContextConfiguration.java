package kalah.configuration;

import kalah.controller.GameController;
import kalah.model.BoardFactory;
import kalah.processor.BoardProcessor;
import kalah.repository.BoardRepository;
import kalah.service.GameService;
import kalah.validator.InputValidatorChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class UnitTestContextConfiguration {

	@Bean
	public BoardFactory boardFactory() {
		return new BoardFactory();
	}

	@Bean
	public BoardRepository boardRepository() { return new BoardRepository(boardFactory()); }

	@Bean
	public BoardProcessor boardProcessor() {
		return new BoardProcessor();
	}

	@Bean
	public GameService gameService() {
		return new GameService(boardRepository(), boardProcessor());
	}

	@Bean
	public InputValidatorChain inputValidatorChain() {
		return new InputValidatorChain();
	}
	
	@Bean
	public GameController gameController() {
		return new GameController(gameService(), inputValidatorChain());
	}

}
