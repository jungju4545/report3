package com.script.script.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.script.script.model.Company;
import com.script.script.repository.CompanyRepository;


@Controller
public class Crawlering2 {

	public static String getCurrentData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	@Autowired
	private CompanyRepository cr;
	
	
	@GetMapping("/test")
	public String parsing(Company c, Model model) {
		System.out.println("Start Date : " + getCurrentData());
		try {
			cr.delete();
//			for (int i = 0; i < 110; i++) {
System.out.println("=======페이지 ==================================");
				Document doc
				= Jsoup.connect("http://www.jobkorea.co.kr/recruit/joblist?menucode=duty&duty=1000100#anchorGICnt_1").get();
				// connet는 주소를 가지고 온다.

				// 한 페이지에 있는 회사채용을 가져온거임
				Elements contents = doc.select("#dev-gi-list > div > div.tplList.tplJobList > table > tbody > tr");
				// selet는 원하는 부분을 가져온다.
//		System.out.println(contents);
				
for (Element element : contents) {
					Elements company = element.select("a");
//					System.out.println(company.size());
					String companyName = company.get(0).attr("href");
//			System.out.println(companyName);
					
					if(company.size()==2) {
						String companyEmployee = company.get(1).attr("href");
//						System.out.println(companyEmployee);
//						System.out.println("회사이름 : " + company.get(0).text() + " &링크주소 : " + companyName);
//						System.out.println("채용공고 : " + company.get(1).text() + " &링크주소 : " + companyEmployee);
						c.setTitle(company.get(0).text());
						c.setUrl("http://www.jobkorea.co.kr"+companyEmployee);
						c.setType("3");
					// 중복되는 url은 최대한 여기서 걸러 DB로 보내야 한다. 그 작업이 필요함
						
					//DB에 있는거
				    // com.get(index).getUrl() : DB에 있는 url
						List<Company>com = cr.findAll();
						
						cr.save(c);
					}else {
						String companyEmployee = company.get(2).attr("href");
//						System.out.println(companyEmployee);
//						System.out.println("회사이름 : " + company.get(0).text() + " &링크주소 : " + companyName);
//						System.out.println("채용공고 : " + company.get(2).text() + " &링크주소 : " + companyEmployee);
						c.setTitle(company.get(0).text());
						c.setUrl("http://www.jobkorea.co.kr"+companyEmployee);
						c.setType("3");
						cr.save(c);
					}
				}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		List<Company> companys = cr.findAll();
		model.addAttribute("companys", companys);
		System.out.println("End Date : " + getCurrentData());
		return "index";
	}
}
