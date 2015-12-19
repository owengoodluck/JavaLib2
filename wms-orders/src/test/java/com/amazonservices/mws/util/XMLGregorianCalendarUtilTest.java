package com.amazonservices.mws.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

import com.amazonservices.mws.orders._2013_09_01.util.XMLGregorianCalendarUtil;

public class XMLGregorianCalendarUtilTest {

	@Test
	public void test() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		XMLGregorianCalendar result = XMLGregorianCalendarUtil.dateToXmlDate(sdf.parse("2015-01-02"));
//		result = XMLGregorianCalendarUtil.dateToXmlDate(new Date());
		System.out.println(result.getHour());
		System.out.println(result.getMinute());
		System.out.println(result.getSecond());
	}
}
