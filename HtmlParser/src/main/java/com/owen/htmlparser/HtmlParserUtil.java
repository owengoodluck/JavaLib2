package com.owen.htmlparser;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;

public class HtmlParserUtil {
	public static Set<String> ignoreUrlSet= new HashSet<String>();
	static{
		ignoreUrlSet.add("http://i02.c.aliimg.com/img/ibank/2013/180/734/928437081_2118088041.jpg");
		ignoreUrlSet.add("http://i02.c.aliimg.com/img/ibank/2014/340/667/1166766043_1837161286.jpg");
		ignoreUrlSet.add("http://i00.c.aliimg.com/img/ibank/2014/966/766/1298667669_1837161286.jpg");
		ignoreUrlSet.add("http://i03.c.aliimg.com/img/ibank/2014/743/171/1401171347_1837161286.jpg");
//		ignoreUrlSet.add("http://i02.c.aliimg.com/img/ibank/2015/522/444/1928444225_1837161286.jpg");
		ignoreUrlSet.add("xxxxxx");
		ignoreUrlSet.add("xxxxxx");
		ignoreUrlSet.add("xxxxxx");
		ignoreUrlSet.add("xxxxxx");
		ignoreUrlSet.add("xxxxxx");
	}
	
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
				if(imgUrl.trim().length()<1){
					continue;
				}
				if(!imgUrl.startsWith("http:")&&!imgUrl.startsWith("https:")){
					imgUrl="http:"+imgUrl;
				}
			
//				if(imgUrl.indexOf("?")!=-1){
//					imgUrl=imgUrl.substring(0,imgUrl.indexOf("?"));
//				}
				if(ignoreUrlSet.contains(imgUrl)){
					System.out.println("ignore image url as it exist in the ignore list");
					continue;
				}
				HttpClientUtil.downloadPicture(imgUrl, downloadPath);
			}
		}
	}
}
