import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.owen.htmlparser.FileUtil;
import com.owen.htmlparser.HtmlParserUtil;
import com.owen.htmlparser.HttpClientUtil;

public class DownloadTest {

	private String rootFolder="C:/Users/owen/Desktop/Amazon/pictures/temp";

	@Test
	public void parseAmazon() throws Exception {
		String[] urls=new String[]{"http://www.amazon.com/Rainbow-Pendant-Necklace-Lesbian-Homosexual/dp/B015FL24RA/ref=sr_1_1?s=apparel&ie=UTF8&qid=1445427307&sr=1-1&nodeID=3887881&keywords=gay+pride"};
		for(String url :urls){
			amazon(url);
		}
	}
	private void amazon(String url) throws Exception{
		String originalLabel = "hiRes\":\"";
		String endLabel = "\"";
		String html = HttpClientUtil.getHtmlSource(url);
		File subFolder = FileUtil.createSubFolder(rootFolder);
		HttpClientUtil.parseHtmlPictureFor1688Zoom(html, originalLabel, endLabel, subFolder);
	
	}

	@Test
	public void parse1688() throws Exception {
		String[] urls=new String[]{"http://detail.1688.com/offer/1288751816.html?spm=0.0.0.0.Pnjl0T"};
		for(String url :urls){
			p1688(url);
		}
	}
	
	//url http://detail.1688.com/offer/35195059069.html
	private void p1688(String url){
		try{
			String originalLabel = "\",\"original\":\"";
			String endLabel = "\"}'>";
			String pageNumber = url.substring(url.indexOf("offer")+6,url.indexOf(".html"));
			String html = HttpClientUtil.getHtmlSource(url);
			String title=getTitle4OPK1688(html);
			while(title.indexOf(" ")>0){//remove blank 
				title= title.replace(" ", "");
			}
			File subFolder = null;
			if(title!=null){
				title=title.replace("/", "");
				subFolder = new File(rootFolder+"/"+pageNumber+"-"+title);
				if(!subFolder.exists()){
					subFolder.mkdir();
				}
			}else{
				subFolder=FileUtil.createSubFolder(rootFolder);
			}
//			FileUtil.write2File(url, subFolder.getAbsolutePath()+"/"+pageNumber,"txt","url");
			FileUtil.createInternetShortcut(subFolder.getAbsolutePath()+"/"+pageNumber+".url", url);
			HttpClientUtil.parseHtmlPictureFor1688Zoom(html, originalLabel, endLabel, subFolder);
			HtmlParserUtil.parseHtmlUrlFor1688Content(url, subFolder);
		}catch(Exception e){
			System.out.println("Parse URL exception :" +url);
			e.printStackTrace();
		}
	}
	
	private String getTitle4OPK1688(String html){
		String title=null;
		String labelStart="<h1 class=\"d-title\">";
		String labelEnd="</h1>";
		int lengthOfOriginalLabel= labelStart.length();
		int indexBegin=html.indexOf(labelStart);
		int indexEnd=-1;
		if(indexBegin!=-1){
			indexBegin+=lengthOfOriginalLabel;
			indexEnd = html.indexOf( labelEnd ,indexBegin);
			if(indexEnd!=-1){
				title = html.substring(indexBegin, indexEnd);
			}else{
				System.out.println("can't find title lable end:"+labelEnd);
			}
		}else{
			System.out.println("can't find title lable start:" + labelStart);
		}
		return title;
	}
	
	@Test
	public void parseTaoBao() throws Exception {
		String url = "https://item.taobao.com/item.htm?spm=2013.1.20141002.8.aF19e1&scm=1007.10009.11473.100200300000001&id=39397534688&pvid=b6881067-a57a-43a1-904b-69a362bb626a";
		url = "https://item.taobao.com/item.htm?spm=a230r.1.14.16.t5XrHM&id=44299228058&ns=1&abbucket=4#detail";
		// url="https://item.taobao.com/item.htm?spm=5706.1529727.a31f1.7.rpPEDU&scm=1007.10977.6259.100200300000000&id=521003336178&pvid=967d5711-a128-4e86-b3f5-046a7bf8e80e";
		String originalLabel = "\",\"original\":\"";
		String endLabel = "\"}'>";
		String html = HttpClientUtil.getHtmlSource(url);

		File subFolder = FileUtil.createSubFolder(rootFolder);
		HttpClientUtil.parseHtmlPicture4TaoBao(html, subFolder);
	}

	@Test
	public void printHtmlPage() throws Exception {
		String url = "https://item.taobao.com/item.htm?spm=a219r.lm5842.14.1.DdenTQ&id=40391982657&ns=1&abbucket=17&code=-100#detail";
		url = "http://desc.alicdn.com/i5/400/911/40391982657/TB1cTEEJpXXXXXVXXXX8qtpFXlX.desc%7Cvar%5Edesc%3Bsign%5E9d0394583e86bd91c4a8e446a487da5d%3Blang%5Egbk%3Bt%5E1442146490";
		String html = HttpClientUtil.getHtmlSource(url);
		System.out.println(html);
	}

	@Test
	public void urlCreateTest(){
		try {
			FileUtil.createInternetShortcut(
					"C:/Users/owen/Desktop/Amazon/pictures/temp/38742193662_2元店饰品批发 义乌小饰品批发 木珠真皮手链 推荐复古牛皮手链/3874219366.url", 
					"http://detail.1688.com/offer/38742193662.html?spm=a2615.7691456.0.0.XwdzjJ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
