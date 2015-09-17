import java.io.File;

import org.junit.Test;

import com.owen.htmlparser.FileUtil;
import com.owen.htmlparser.HtmlParserUtil;
import com.owen.htmlparser.HttpClientUtil;

public class DownloadTest {

	private String rootFolder="C:/Users/owen/Desktop/Amazon/»õÔ´/0917";
	
	@Test
	public void parse1688()throws Exception{
		String url="http://detail.1688.com/offer/41136916713.html?spm=0.0.0.0.wzVln0";
		url="http://detail.1688.com/offer/42869191136.html?spm=a2615.7691456.0.0.MNnzat";
		String originalLabel="\",\"original\":\"";
		String endLabel="\"}'>";
		String html = HttpClientUtil.getHtmlSource(url);
		
		File subFolder = FileUtil.createSubFolder(rootFolder);
		HttpClientUtil.parseHtmlPictureFor1688Zoom(html,originalLabel, endLabel,subFolder);
		HtmlParserUtil.parseHtmlUrlFor1688Content(url, subFolder);
	}
	
	@Test
	public void parseTaoBao()throws Exception{
		String url="https://item.taobao.com/item.htm?spm=2013.1.20141002.8.aF19e1&scm=1007.10009.11473.100200300000001&id=39397534688&pvid=b6881067-a57a-43a1-904b-69a362bb626a";
		url="https://item.taobao.com/item.htm?spm=a219r.lm5842.14.1.DdenTQ&id=40391982657&ns=1&abbucket=17&code=-100#detail";
		String originalLabel="\",\"original\":\"";
		String endLabel="\"}'>";
		String html=HttpClientUtil.getHtmlSource(url);
		
		File subFolder = FileUtil.createSubFolder(rootFolder);
		HttpClientUtil.parseHtmlPicture4TaoBao(html, subFolder);
	}
	
	
	@Test
	public void printHtmlPage() throws Exception{
		String url="https://item.taobao.com/item.htm?spm=a219r.lm5842.14.1.DdenTQ&id=40391982657&ns=1&abbucket=17&code=-100#detail";
		url="http://desc.alicdn.com/i5/400/911/40391982657/TB1cTEEJpXXXXXVXXXX8qtpFXlX.desc%7Cvar%5Edesc%3Bsign%5E9d0394583e86bd91c4a8e446a487da5d%3Blang%5Egbk%3Bt%5E1442146490";
		String html=HttpClientUtil.getHtmlSource(url);
		System.out.println(html);
	}
	
}
