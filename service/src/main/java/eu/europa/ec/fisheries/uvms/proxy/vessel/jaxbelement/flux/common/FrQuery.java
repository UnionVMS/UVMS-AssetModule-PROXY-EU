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
package eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 **/
@XmlRootElement(name = "FR-Query", namespace = "urn:xeu:ec:fisheries:flux-bl:FLUX_FR_Query:0")
@XmlAccessorType(XmlAccessType.FIELD)
public class FrQuery {

    @XmlElement(name = "CFR", namespace = "urn:xeu:ec:fisheries:flux-bl:FLUX_FR_Query:0")
    private String cfr;

    @XmlElement(name = "EXT_MARKING", namespace = "urn:xeu:ec:fisheries:flux-bl:FLUX_FR_Query:0")
    private String extMarking;

    @XmlElement(name = "IRCS", namespace = "urn:xeu:ec:fisheries:flux-bl:FLUX_FR_Query:0")
    private String ircs;

    public String getCfr() {
        return cfr;
    }

    public void setCfr(String cfr) {
        this.cfr = cfr;
    }

    public String getExtMarking() {
        return extMarking;
    }

    public void setExtMarking(String extMarking) {
        this.extMarking = extMarking;
    }

    public String getIrcs() {
        return ircs;
    }

    public void setIrcs(String ircs) {
        this.ircs = ircs;
    }

}