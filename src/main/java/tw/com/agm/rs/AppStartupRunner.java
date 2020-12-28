package tw.com.agm.rs;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(1)
@Component
public class AppStartupRunner
	implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
	log.info("args: {}", Arrays.asList(args));
    }
}
