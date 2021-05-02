package org.username4db.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.username4db.repository.RecordRepo;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

@Controller
@RequestMapping(value = "/")
public class WebController {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private RecordRepo recordRepo;

	final private RestTemplate restTemplate = new RestTemplate();

	@RequestMapping("/")
	String index() {
		return "index";
	}

	@RequestMapping("/ticks")
	String ticks(Map<String, Object> model) {
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
			stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
			ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

			ArrayList<String> output = new ArrayList<String>();
			while (rs.next()) {
				output.add("Read from DB: " + rs.getTimestamp("tick"));
			}

			model.put("records", output);
			return "db";
		} catch (Exception e) {
			model.put("message", e.getMessage());
			return "error";
		}
	}

	@RequestMapping("/rec")
	String rec(Map<String, Object> model) {
		model.put("recs", recordRepo.findAll());
		return "rec";
	}

	@RequestMapping("/htmlUnit")
	@ResponseBody
	public String htmlUnit(@RequestParam(name = "ID", required = false, defaultValue = "04231910") String id)
			throws Exception {
		String xml = "";
		try (final WebClient webClient = new WebClient()) {
			HtmlPage page = webClient.getPage("https://findbiz.nat.gov.tw/fts/query/QueryBar/queryInit.do");

			final HtmlForm queryListForm = page.getFormByName("queryListForm");
			final HtmlTextInput qryCond = queryListForm.getInputByName("qryCond");
			qryCond.type(id);

			final HtmlInput infoDefault = (HtmlInput) page.getElementById("infoDefault");
			infoDefault.click();

			page.getElementsByName("qryType").stream().forEach(ele -> {
				try {
					ele.click();
				} catch (IOException e) {
				}
			});

			final HtmlButton qryBtn = (HtmlButton) page.getElementById("qryBtn");
			page = qryBtn.click();

			final List<HtmlSpan> detail = page.getByXPath("//span[@class='moreLinkMouseOut']");
			if (detail.size() > 0) {
				page = detail.get(0).click();
			}
			xml = page.asXml();
		}
		return xml;
	}

	@RequestMapping("/queryList")
	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryList(@RequestParam(name = "ID", required = false, defaultValue = "04231910") String id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
		headers.setAcceptLanguageAsLocales(Arrays.asList(Locale.TRADITIONAL_CHINESE));
		headers.setConnection("close");
		headers.setOrigin("https://findbiz.nat.gov.tw");
		headers.set(HttpHeaders.REFERER, "https://findbiz.nat.gov.tw/fts/query/QueryBar/queryInit.do");
		headers.set(HttpHeaders.USER_AGENT, "Mozilla/5.0");

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("qryCond", id);
		map.add("fhl", "zh_TW");
		map.add("infoType", "D");
		map.add("qryType", "cmpyType");
		map.add("cmpyType", "true");
		map.add("qryType", "brCmpyType");
		map.add("brCmpyType", "true");
		map.add("qryType", "busmType");
		map.add("busmType", "true");
		map.add("qryType", "factType");
		map.add("factType", "true");
		map.add("qryType", "lmtdType");
		map.add("lmtdType", "true");
		map.add("isAlive", "all");
		map.add("busiItemMain", "");
		map.add("busiItemSub", "");
		map.add("sugCont", "");
		map.add("sugEmail", "");
		map.add("g-recaptcha-response", "");

		HttpEntity request = new HttpEntity(map, headers);

		ResponseEntity<String> response = restTemplate
				.postForEntity("https://findbiz.nat.gov.tw/fts/query/QueryList/queryList.do", request, String.class);

		if (HttpStatus.OK.equals(response.getStatusCode())) {
			response.getHeaders().forEach((key, value) -> {
				// LOGGER.info("{}={}", key, value);
			});
			Document htmlDoc = Jsoup.parse(id);
			// LOGGER.info("disj = {} ", htmlDoc.getElementById("_disj").val());
		}
		return response.getBody();
	}

	@RequestMapping("/queryCmpyDetail")
	@ResponseBody
	public String queryCmpyDetail(@RequestParam(name = "ID", required = false, defaultValue = "04231910") String id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
		headers.setAcceptLanguageAsLocales(Arrays.asList(Locale.TRADITIONAL_CHINESE));
		headers.setConnection("close");
		headers.setOrigin("https://findbiz.nat.gov.tw");
		headers.set(HttpHeaders.REFERER, "https://findbiz.nat.gov.tw/fts/query/QueryList/queryList.do");
		headers.set(HttpHeaders.USER_AGENT, "Mozilla/5.0");

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("banNo", id);
		map.add("brBanNo", "");
		map.add("banKey", "");
		map.add("estbId", "");
		map.add("objectId", "");
		map.add("CPage", "");
		map.add("brCmpyPage", "");
		map.add("eng", "false");
		map.add("disj", "32559341517F1761DC36D961C9D6DC15");
		map.add("fhl", "zh_TW");
		map.add("CPageHistory", "");
		map.add("historyPage", "");
		map.add("chgAppDate", "");
		map.add("translateAddress", "");
		map.add("regUnitCode", "");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(
				"https://findbiz.nat.gov.tw/fts/query/QueryCmpyDetail/queryCmpyDetail.do", request, String.class);
		response.getHeaders().forEach((key, value) -> {
			// LOGGER.info("{}={}", key, value);
		});
		return response.getBody();
	}

}
