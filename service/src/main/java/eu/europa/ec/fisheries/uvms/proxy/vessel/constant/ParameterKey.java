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
package eu.europa.ec.fisheries.uvms.proxy.vessel.constant;

public enum ParameterKey {

    SERVICE_ENDPOINT("asset.eu.service.endpoint"),
    CERT_HEADER("asset.eu.service.client.cert.header"),
    CERT_VALUE("asset.eu.service.client.cert.value"),
    FLUX_DATAFLOW("asset.eu.service.dataflow"),
    FLUX_AD("asset.eu.service.flux.ad");

    private final String key;
	

private ParameterKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}

}