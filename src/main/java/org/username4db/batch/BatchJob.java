package org.username4db.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchJob {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Scheduled(fixedRate = 60000)
	public void timerRate() {
		LOGGER.info("");
	}

	@Scheduled(cron = "00 00 18 * * ?")
	public void timerCron() {
		LOGGER.info("");
	}
}
