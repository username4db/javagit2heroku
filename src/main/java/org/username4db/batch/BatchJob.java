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
	}

	@Scheduled(cron = "00 */30 * * * ?")
	public void timerCron() {
//		ResponseEntity<String> res = restTemp.getForEntity("https://javagit2heroku.herokuapp.com/", null);
		LOGGER.info("timer");
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest //
//				.newBuilder() //
//				.uri(URI.create("https://javagit2heroku.herokuapp.com/")) //
//				.build();
//		client.sendAsync(request, BodyHandlers.ofString()) //
//				.thenApply(HttpResponse::body) //
//				.thenAccept(str -> {
//				}) //
//				.join();
	}
}
