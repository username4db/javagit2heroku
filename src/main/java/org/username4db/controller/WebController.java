package org.username4db.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String htmlUnit() throws Exception {
		String xml = "";
		try (final WebClient webClient = new WebClient()) {
			HtmlPage page = webClient.getPage("https://findbiz.nat.gov.tw/fts/query/QueryBar/queryInit.do");

			final HtmlForm queryListForm = page.getFormByName("queryListForm");
			final HtmlTextInput qryCond = queryListForm.getInputByName("qryCond");
			qryCond.type("04231910");

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

}
