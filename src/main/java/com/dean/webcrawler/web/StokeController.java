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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class StokeController {
	
	private static final Logger logger = LoggerFactory.getLogger(StokeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String travel(Locale locale, Model model) throws ClientProtocolException, IOException{
		
		System.out.println("현재시각 : "+ getCurrentData());
		
		// Input url for Crawling
		String url = "https://finance.naver.com/sise/sise_market_sum.nhn";
		
		// Input tag for Crawling 
		String tag = ".tltle";
		
		// url connect 
		Document doc = Jsoup.connect(url).get();
		
		System.out.println("주제 : "+ doc.title());
		
		// Elements - doc.select("selector")
        Elements elements = doc.select(tag);
        
        List<String> dataList = new ArrayList<String>();
        
        // Test for checking Elements
        for(Element element: elements) {
            System.out.println(element.text());
            dataList.add(element.text());
        } 
        
        model.addAttribute("dataList", dataList);
        
		return "home";
	}
	
	
	// 현재시각 메서드
	public static String getCurrentData(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        return sdf.format(new Date());
	}
	
}
