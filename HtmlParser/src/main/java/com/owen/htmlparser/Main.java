package com.owen.htmlparser;

public class Main {
	private static String rootFolder="C:/Users/owen/Desktop/Amazon/»õÔ´/0916";
	
	public static void main(String args[]) throws Exception{
//		parse1688();
		parseTaoBao();
	}
	
	public static void parse1688()throws Exception{
		String url="http://detail.1688.com/offer/41136916713.html?spm=0.0.0.0.wzVln0";
		String originalLabel="\",\"original\":\"";
		String endLabel="\"}'>";
		String html = HtmlParser.getHtmlSource(url);
		HtmlParser.parseHtmlPicture(html,originalLabel, endLabel,rootFolder);
	}
	
	public static void parseTaoBao()throws Exception{
		String url="https://item.taobao.com/item.htm?spm=2013.1.20141002.8.aF19e1&scm=1007.10009.11473.100200300000001&id=39397534688&pvid=b6881067-a57a-43a1-904b-69a362bb626a";
		url="https://item.taobao.com/item.htm?spm=a230r.1.14.297.p95ISJ&id=35408150846&ns=1&abbucket=4#detail";
		String originalLabel="\",\"original\":\"";
		String endLabel="\"}'>";
		String html=HtmlParser.getHtmlSource(url);
		HtmlParser.parseHtmlPicture4TaoBao(html, rootFolder);
	}
	
}
