package com.dean.webcrawler.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class StokeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(StokeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String travel(Locale locale, Model model) throws ClientProtocolException, IOException{
		
		System.out.println("현재시각 : "+ getCurrentData());
		
		// Input url for Crawling
		String basicUrl = "https://finance.naver.com/sise/sise_market_sum.nhn";
		
		// Input page Number
		int pageNumber = 1;
		
		Document doc;
		
		// Input DataList what you want
		List<String> urlList 	= new ArrayList<String>();		// 원하는 urlList
		List<String> dataList 	= new ArrayList<String>();		// 추출한 기업명List
		//List<String> codeList 	= new ArrayList<String>();		// 기업 codeList
		
		for(int i = 1; i < pageNumber; i++) {
			String parameter = "?&page=" + pageNumber;
			
			String url = basicUrl + parameter;
			urlList.add(url);
		}
		
		for(int i = 0; i < urlList.size(); i++) {
			// Input tag for Crawling 
			String nameTag = ".tltle";		// 기업이름
			
			// url connect 
			doc = Jsoup.connect(urlList.get(i)).timeout(500).get();
			
			System.out.println("주제 : "+ doc.title());
			
			// Elements - doc.select("selector")
	        Elements nameElements = doc.select(nameTag);
	        
	        // Test for checking Elements
	        for(Element element: nameElements) {
	            System.out.println(element);
	            // 파싱 해서 코드번호 가져와 코드리스트에 넣어주기.
	            //System.out.println(element.select("a").attr("href"));
	            dataList.add(element.text());
	        }
		}
		
		// 해당 기업명 들어가서 데이터 가져오기.
        
        model.addAttribute("dataList", dataList);
        
		return "home";
	}
	
	
	// 현재시각 메서드
	public static String getCurrentData(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        return sdf.format(new Date());
	}
	
	
	// 전체 html을 파싱하는것 부분부분 가져와서 파싱하는것
	
	
	
	
}
