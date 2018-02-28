package kalah.configuration;

import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {
//    @Bean
//    public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
//        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
//        factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {
//
//            @Override
//            public void customize(Builder builder) {
//                builder.addHttpListener(8080, "0.0.0.0");
//            }
//
//        });
//        return factory;
//    }

}
