package tw.com.agm.rs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import tw.com.agm.rs.model.RainResponse;
import tw.com.agm.rs.model.Sku;

@Slf4j
@RestController
@RequestMapping("/api/out")
public class RainController {

    @Autowired
    private Environment env;
    @Autowired
    private WebClient webClient;

    @ApiOperation(value = "Obtain home information", notes = "Obtain home information from AGM API")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Successful"),
	    @ApiResponse(responseCode = "404", description = "Cannot find anything...") })
    @GetMapping(value = "/call", produces = { MediaType.TEXT_PLAIN_VALUE })
    public String call(String args) {
	log.info("url: {} key: {} value: {}", env.getProperty("URL"), env.getProperty("SECRET_KEY"),
		env.getProperty("SECRET_TOKEN"));
	Sku sku = new Sku();
	ResponseSpec retrieve = webClient.post().uri("/v1/SKU")
		.header(env.getProperty("SECRET_KEY"), env.getProperty("SECRET_TOKEN")).body(Mono.just(sku), Sku.class).retrieve()
		.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus), clientResponse -> Mono.empty())
		.onStatus(HttpStatus::is4xxClientError, response -> Mono.empty())
		.onStatus(HttpStatus::is5xxServerError, response -> Mono.empty());
	// Optional<RainResponse> response = retrieve.bodyToMono(RainResponse.class).blockOptional();
	retrieve.bodyToMono(RainResponse.class).subscribe(result -> log.info("result: {}", result.getSuccess()));
	return "Success";
    }
}
