package tw.com.agm.rs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import tw.com.agm.rs.model.ReportDtl;
import tw.com.agm.rs.service.SamplesService;

@Slf4j
@RestController
@RequestMapping("/api")
public class SampleController {

    @Autowired
    private Environment env;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SamplesService service;

    @ApiOperation(value = "Obtain home information", notes = "Obtain home information from AGM API")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Successful"),
	    @ApiResponse(responseCode = "404", description = "Cannot find anything...") })
    @GetMapping(value = "/type/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ReportDtl> home(@ApiParam("Product ID") @PathVariable(value = "id") String id) throws Exception {
	log.info("[Enter] {}.{}", SampleController.class.getSimpleName(), "home");
	log.info("env: {}", env.getProperty("URL"));
	// ReportDtl dtl = service.search("00000d06s44163og", 1);
	ReportDtl dtl = new ReportDtl();
	if (null != dtl) {
	    return ResponseEntity.status(HttpStatus.OK).body(dtl);
	}
	return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
