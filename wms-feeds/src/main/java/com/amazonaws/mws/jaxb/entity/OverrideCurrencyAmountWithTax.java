//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.29 at 10:21:45 PM CST 
//


package com.amazonaws.mws.jaxb.entity;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OverrideCurrencyAmountWithTax complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OverrideCurrencyAmountWithTax">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;>OverrideCurrencyAmount">
 *       &lt;attribute name="valueWithoutTax" type="{}BasePriceCurrencyAmount" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OverrideCurrencyAmountWithTax")
public class OverrideCurrencyAmountWithTax
    extends OverrideCurrencyAmount
{

    @XmlAttribute(name = "valueWithoutTax")
    protected BigDecimal valueWithoutTax;

    /**
     * Gets the value of the valueWithoutTax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValueWithoutTax() {
        return valueWithoutTax;
    }

    /**
     * Sets the value of the valueWithoutTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValueWithoutTax(BigDecimal value) {
        this.valueWithoutTax = value;
    }

}