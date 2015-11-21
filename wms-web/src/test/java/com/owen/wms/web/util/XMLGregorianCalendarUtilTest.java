package com.owen.wms.web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

import com.amazonservices.mws.client.MwsUtl;
import com.owen.wms.web.utils.XMLGregorianCalendarUtil;

public class XMLGregorianCalendarUtilTest {

	private SimpleDateFormat sdfLong = new SimpleDateFormat("yyyyMMdd HHmmss");
	private SimpleDateFormat sdfShort = new SimpleDateFormat("yyyyMMdd");
	@Test
	public void test() throws Exception{
		XMLGregorianCalendar cal = MwsUtl.getDTF().newXMLGregorianCalendar();
		cal.setMonth(1);
		cal.setDay(12);
		cal.setYear(2015);
		Date date = XMLGregorianCalendarUtil.xmlDate2Date(cal);
		System.out.println(this.sdfLong.format(date));
	}
	
	@Test
	public void test1()throws Exception{
		Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(sdfShort.parse("20141012").getTime());
    	XMLGregorianCalendar createdAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
    	createdAfter.setDay(cal.get(Calendar.DAY_OF_MONTH));
    	createdAfter.setMonth(cal.get(Calendar.MONTH));
    	createdAfter.setYear(cal.get(Calendar.YEAR));
    	
    	System.out.println(this.sdfLong.format(XMLGregorianCalendarUtil.xmlDate2Date(createdAfter)));
	}
}
