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
		String[] urls=new String[]{"http://www.amazon.com/TEMEGO-Stainless-Polished-Detachable-Necklace/dp/B0132A9YR4/ref=sr_1_46?s=apparel&ie=UTF8&qid=1446385312&sr=1-46&nodeID=7141123011&keywords=Cross+Dog+Tag+Pendant"};
		for(String url :urls){
			amazon(url);
		}
	}
	private void amazon(String url) throws Exception{
		String originalLabel = "hiRes\":\"";
		String endLabel = "\"";
		String html = HttpClientUtil.getHtmlSource(url);
		String title=getTitle4Amazon(html);
		File subFolder = null;
		if(title!=null){
			title=title.replace("/", "");
			subFolder = new File(rootFolder+"/"+title);
			if(!subFolder.exists()){
				subFolder.mkdir();
			}
		}else{
			subFolder=FileUtil.createSubFolder(rootFolder);
		}

		FileUtil.createInternetShortcut(subFolder.getAbsolutePath()+"/Amazon_"+title+".url", url);
		HttpClientUtil.parseHtmlPictureFor1688Zoom(html, originalLabel, endLabel, subFolder);
	
	}

	@Test
	public void parse1688() throws Exception {
		String[] urls=new String[]{"http://detail.1688.com/offer/521627405218.html?spm=0.0.0.0.VDc3M4"};
		
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
			FileUtil.createInternetShortcut(subFolder.getAbsolutePath()+"/Alibaba_"+pageNumber+".url", url);
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
	
	private String getTitle4Amazon(String html){
		String title=null;
		String labelStart="<span id=\"productTitle\" class=\"a-size-large\">";
		String labelEnd="</span>";
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
//		title.replace("~", "");
		return title;
	}
	
	@Test
	public void parseTaobaoList() throws Exception {
		String[] urls=new String[]{"https://item.taobao.com/item.htm?spm=a230r.1.999.6.V1lKCS&id=35835226199&ns=1#detail",
				"https://item.taobao.com/item.htm?spm=a230r.1.999.39.V1lKCS&id=36096399678&ns=1#detail"};
		
		for(String url :urls){
			parseTaoBao(url);
		}
	}
	
	private void parseTaoBao(String url) throws Exception {
		String html = HttpClientUtil.getHtmlSource(url);
		String title = this.getTitle4Taobao(html);

		File subFolder = null;
		if(title!=null){
			title=title.replace("/", "");
			subFolder = new File(rootFolder+"/"+title);
			if(!subFolder.exists()){
				subFolder.mkdir();
			}
		}else{
			subFolder=FileUtil.createSubFolder(rootFolder);
		}
		
		HttpClientUtil.parseHtmlPicture4TaoBao(html, subFolder);
	}

	private String getTitle4Taobao(String html){
		String title=null;
		String labelStart="<title>";
		String labelEnd="</title>";
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
//		title.replace("~", "");
		return title;
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
		FileUtil.createInternetShortcut(
				"C:/Users/owen/Desktop/Amazon/pictures/temp/38742193662_2Ԫ����Ʒ�� ����С��Ʒ�� ľ����Ƥ���� �Ƽ�����ţƤ����/3874219366.url", 
				"http://detail.1688.com/offer/38742193662.html?spm=a2615.7691456.0.0.XwdzjJ");
	}
}
