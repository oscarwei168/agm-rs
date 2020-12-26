package tw.com.agm.rs.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@PropertySources({ @PropertySource("classpath:agm-rs.properties") })
public class AgmRsConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public WebClient webClient() {
	TcpClient tcpClient = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000).doOnConnected(connection -> {
	    connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
	    connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
	}).metrics(true);
	ReactorClientHttpConnector reactorClientHttpConnector = new ReactorClientHttpConnector(
		HttpClient.from(tcpClient).responseTimeout(Duration.ofSeconds(5)));
	WebClient client = WebClient.builder().codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
		.clientConnector(reactorClientHttpConnector)
		.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).baseUrl(env.getProperty("URL"))
		.build();
	return client;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
	return builder.build();
    }

    @Bean
    public Docket swaggerSetting() {
	return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select().paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
	return new ApiInfoBuilder().title("AGM API Documentation").description("AGM...").version("0.0.1").build();
    }
}
