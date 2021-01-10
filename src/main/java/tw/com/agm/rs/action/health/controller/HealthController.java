package tw.com.agm.rs.action.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tw.com.agm.rs.action.health.service.HealthService;
import tw.com.agm.rs.config.LogExecutionTime;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @ApiOperation(value = "AGM-RS Health Check", notes = "For Docker Swarm health checking")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Successful"),
	    @ApiResponse(responseCode = "404", description = "Cannot find anything...") })
    @GetMapping(value = "/call", produces = { MediaType.TEXT_PLAIN_VALUE })
    @LogExecutionTime
    public String call(String args) {
	int check = healthService.test();
	return 1 == check ? "Success" : "Fail";
    }

    @GetMapping(value = "/check", produces = { MediaType.TEXT_PLAIN_VALUE })
    public String check(String args) {
	return "OK";
    }
}
