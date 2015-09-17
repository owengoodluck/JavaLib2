package com.owen.htmlparser;

import java.io.File;

import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParserUtil {

	public static void parseHtmlUrlFor1688Content(String url,File downloadPath) throws Exception{
		System.out.println("Get picture from 1688 detail content");
		Parser parser =  new  Parser(url);
		HasAttributeFilter hasAttributeFilterOfID = new HasAttributeFilter("id","de-description-detail");
		NodeList nodeList = parser.extractAllNodesThatMatch(hasAttributeFilterOfID);
		if(nodeList!=null && nodeList.size()>0){
			parseHtmlString(nodeList.toHtml(),downloadPath);
		}
	}
	

	public static void parseHtmlString(String htmlString,File downloadPath) throws Exception{
		Parser parser =  new  Parser();
		parser.setInputHTML(htmlString);
		NodeClassFilter imgClassFilter = new NodeClassFilter(ImageTag.class);
		NodeList nodeList = parser.extractAllNodesThatMatch(imgClassFilter);
		if(nodeList!=null && nodeList.size()>0){
			for(int i=0;i<nodeList.size();i++){
				ImageTag imgTag = (ImageTag)nodeList.elementAt(i);
				String imgUrl=imgTag.getImageURL();
				HttpClientUtil.downloadPicture(imgUrl, downloadPath);
			}
		}
	}
}
