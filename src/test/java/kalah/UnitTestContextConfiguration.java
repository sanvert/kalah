package kalah;

import kalah.controller.GameController;
import kalah.factory.BoardFactory;
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
	public InputValidatorChain inputValidatorChain() {
		return new InputValidatorChain();
	}

	@Bean
	public GameService gameService() {
		return new GameService();
	}
	
	@Bean
	public GameController gameController() {
		return new GameController();
	}
	
	@Bean
	public BoardRepository boardRepository() { return new BoardRepository(); }
	
	@Bean
	public BoardFactory boardFactory() {
		return new BoardFactory();
	}
	
	@Bean
	public BoardProcessor boardProcessor() {
		return new BoardProcessor();
	}
}
