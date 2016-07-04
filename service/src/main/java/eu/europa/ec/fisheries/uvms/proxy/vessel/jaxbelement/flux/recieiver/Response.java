/*
﻿﻿Developed with the contribution of the European Commission - Directorate General for Maritime Affairs and Fisheries
© European Union, 2015-2016.
 
This file is part of the Integrated Data Fisheries Management (IFDM) Suite. The IFDM Suite is free software: you can
redistribute it and/or modify it under the terms of the GNU General Public License as published by the
Free Software Foundation, either version 3 of the License, or any later version. The IFDM Suite is distributed in
the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy
of the GNU General Public License along with the IFDM Suite. If not, see <http://www.gnu.org/licenses/>.
 */
package eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.recieiver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 **/
@XmlRootElement(name = "Response", namespace = "urn:xeu:ec:fisheries:flux-bl:FLUX_FR_VesselDeclarationList:0")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {

    @XmlElement(name = "ReferencedMessage")
    private String correlationId;

    @XmlElement(name = "Status")
    private String status;

    @XmlElement(name = "StatusDescription")
    private String statusDescription;

    @XmlElement(name = "StatusDetails")
    private String statusDetails;

    @XmlElement(name = "FR-VesselDeclaration")
    private FrVesselDeclaration vessel;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusDetails() {
        return statusDetails;
    }

    public void setStatusDetails(String statusDetails) {
        this.statusDetails = statusDetails;
    }

    public FrVesselDeclaration getVessel() {
        return vessel;
    }

    public void setVessel(FrVesselDeclaration vessel) {
        this.vessel = vessel;
    }

    @Override
    public String toString() {
        return "Response{" + "correlationId=" + correlationId + ", status=" + status + ", statusDescription=" + statusDescription + ", statusDetails=" + statusDetails + ", vessel=" + vessel + '}';
    }

}