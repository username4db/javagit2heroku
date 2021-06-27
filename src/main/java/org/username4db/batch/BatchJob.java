package org.username4db.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchJob {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

//	@Autowired
//	private RestTemplate restTemp;

	@Scheduled(fixedRate = 60000)
	public void timerRate() {
		LOGGER.info("timerRate");
	}

	@Scheduled(cron = "00 */15 * * * ?")
	public void timerCron() {
//		ResponseEntity<String> res = restTemp.getForEntity("https://javagit2heroku.herokuapp.com/", null);
		LOGGER.info("timer");
	}
}
