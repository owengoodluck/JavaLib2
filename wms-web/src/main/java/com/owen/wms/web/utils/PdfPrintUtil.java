package com.owen.wms.web.utils;

import java.io.FileInputStream;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.apache.log4j.Logger;

import com.owen.wms.web.constants.AppConstant;

public class PdfPrintUtil {

	private static Logger log = Logger.getLogger(PdfPrintUtil.class);
	
	public static void main(String[] args) throws Exception{
		String filePath = "C:/Users/owen/Desktop/tmp/20151117_091204_052.pdf";
		printViaCommandLine(filePath);
	}

	public static void printViaCommandLine(String filePath) throws Exception{
		String command = AppConstant.AdobeCommand+filePath;
		Runtime.getRuntime().exec(command);
		log.info(command);
	}
	
	private static PrintService getDefaultPrinter(){
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		return getDefaultPrinter(flavor, patts);
	}
	
	private static PrintService getDefaultPrinter(DocFlavor flavor , PrintRequestAttributeSet patts){
		PrintService service = null;
		PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);
		if (ps.length == 0) {
		    throw new IllegalStateException("No Printer found");
		}else{
			service = ps[0];
		    System.out.println(service.getName());
		}
		return service;
	}
	
	public static void test(String filePath) throws PrintException, IOException {
	    PrintService myService = getDefaultPrinter();
	    FileInputStream fis = new FileInputStream(filePath);
	    Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
	    DocPrintJob printJob = myService.createPrintJob();
	    printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
	    fis.close();        
	}
}
