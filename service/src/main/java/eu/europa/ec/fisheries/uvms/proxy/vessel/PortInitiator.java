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
package eu.europa.ec.fisheries.uvms.proxy.vessel;

import eu.europa.ec.fisheries.uvms.proxy.vessel.bean.ParameterCacheBean;
import eu.europa.ec.fisheries.uvms.proxy.vessel.constant.ParameterKey;
import eu.europa.ec.fisheries.uvms.proxy.vessel.message.FluxMessageSenderBean;
import java.util.Map;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.ws.BindingProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xeu.connector_bridge.wsdl.v1.BridgeConnectorPortType;
import xeu.connector_bridge.wsdl.v1.BridgeConnectorService;

/**
 **/
/**
 * This class is intended to initiate the PortType for the intended WS-calls
 **/
@Singleton
public class PortInitiator {
    
    private static Logger LOG = LoggerFactory.getLogger(PortInitiator.class);
    
    private BridgeConnectorPortType vesselPort;
    
    @EJB
    ParameterCacheBean params;

    /**
     *
     * @return
     */
    public BridgeConnectorPortType getPort() {
        if (vesselPort == null) {
            vesselPort = setupPort();
        }
        return vesselPort;
    }

    /**
     *
     * @return
     */
    private BridgeConnectorPortType setupPort() {
        LOG.info("Setting up port with endpoint address: {}", params.getCachedStringValue(ParameterKey.SERVICE_ENDPOINT));
        BridgeConnectorService service = new BridgeConnectorService();
        BridgeConnectorPortType port = service.getBridgeConnectorSOAP11Port();
        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> context = bp.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, params.getCachedStringValue(ParameterKey.SERVICE_ENDPOINT));
        return port;
    }
    
    public void updatePortValues() {
        vesselPort = null;
    }
}