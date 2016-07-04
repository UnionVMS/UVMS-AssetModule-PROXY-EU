/*
﻿Developed with the contribution of the European Commission - Directorate General for Maritime Affairs and Fisheries
© European Union, 2015-2016.

This file is part of the Integrated Fisheries Data Management (IFDM) Suite. The IFDM Suite is free software: you can
redistribute it and/or modify it under the terms of the GNU General Public License as published by the
Free Software Foundation, either version 3 of the License, or any later version. The IFDM Suite is distributed in
the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a
copy of the GNU General Public License along with the IFDM Suite. If not, see <http://www.gnu.org/licenses/>.
 */
package eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.sender;

import eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.common.FrQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 **/
@XmlRootElement(name = "BasicAttribute", namespace = "urn:xeu:ec:fisheries:flux-bl:FLUX_FR_Query:0")
@XmlAccessorType(XmlAccessType.FIELD)
public class BasicAttribute {

    @XmlElement(name = "MessageID", namespace = "urn:xeu:ec:fisheries:flux-bl:FLUX_FR_Query:0")
    private String messageId;

    @XmlElement(name = "Creation", namespace = "urn:xeu:ec:fisheries:flux-bl:FLUX_FR_Query:0")
    private XMLGregorianCalendar creation;

    @XmlElement(name = "FR-Query", namespace = "urn:xeu:ec:fisheries:flux-bl:FLUX_FR_Query:0")
    private FrQuery frQuery;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public XMLGregorianCalendar getCreation() {
        return creation;
    }

    public void setCreation(XMLGregorianCalendar creation) {
        this.creation = creation;
    }

    public FrQuery getFrQuery() {
        return frQuery;
    }

    public void setFrQuery(FrQuery frQuery) {
        this.frQuery = frQuery;
    }

}