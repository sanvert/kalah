package kalah;

import kalah.controller.GameController;
import kalah.factory.GameFactory;
import kalah.processor.GameProcessor;
import kalah.repository.GameRepository;
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
	public GameController gameController() {
		return new GameController();
	}
	
	@Bean
	public GameRepository gameRepository() {
		return new GameRepository();
	}
	
	@Bean
	public GameFactory gameFactory() {
		return new GameFactory();
	}
	
	@Bean
	public GameProcessor gameProcessor() {
		return new GameProcessor();
	}
}
