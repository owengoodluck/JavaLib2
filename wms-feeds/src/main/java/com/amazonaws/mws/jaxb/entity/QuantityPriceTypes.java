//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.29 at 10:21:45 PM CST 
//


package com.amazonaws.mws.jaxb.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QuantityPriceTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="QuantityPriceTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="fixed"/>
 *     &lt;enumeration value="percent"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QuantityPriceTypes")
@XmlEnum
public enum QuantityPriceTypes {

    @XmlEnumValue("fixed")
    FIXED("fixed"),
    @XmlEnumValue("percent")
    PERCENT("percent");
    private final String value;

    QuantityPriceTypes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QuantityPriceTypes fromValue(String v) {
        for (QuantityPriceTypes c: QuantityPriceTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
