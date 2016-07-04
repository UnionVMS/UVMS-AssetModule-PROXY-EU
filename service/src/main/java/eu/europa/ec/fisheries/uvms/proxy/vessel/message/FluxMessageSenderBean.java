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
package eu.europa.ec.fisheries.uvms.proxy.vessel.message;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import eu.europa.ec.fisheries.uvms.proxy.vessel.PortInitiator;
import eu.europa.ec.fisheries.uvms.proxy.vessel.FluxMessageSender;
import eu.europa.ec.fisheries.uvms.proxy.vessel.bean.ParameterCacheBean;
import eu.europa.ec.fisheries.uvms.proxy.vessel.constant.ParameterKey;
import eu.europa.ec.fisheries.uvms.proxy.vessel.exception.ProxyException;
import eu.europa.ec.fisheries.uvms.proxy.vessel.exception.VesselProxyMappingException;
import eu.europa.ec.fisheries.uvms.proxy.vessel.mapper.FluxMessageRequestMapper;
import eu.europa.ec.fisheries.wsdl.asset.types.AssetIdType;
import xeu.connector_bridge.wsdl.v1.BridgeConnectorPortType;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xeu.connector_bridge.v1.PostMsgOutType;
import xeu.connector_bridge.v1.PostMsgType;

/**
 **/
@Stateless
public class FluxMessageSenderBean implements FluxMessageSender {

    @EJB
    PortInitiator port;

    @EJB
    ParameterCacheBean configValues;

    private static final Logger LOG = LoggerFactory.getLogger(FluxMessageSenderBean.class);

    @Override
    public String getVessel(String data, AssetIdType queryType, String messageId) throws ProxyException {
        try {

            BridgeConnectorPortType portType = port.getPort();

            try {

                String headerType = configValues.getCachedStringValue(ParameterKey.CERT_HEADER);
                String headerValue = configValues.getCachedStringValue(ParameterKey.CERT_VALUE);

                //TODO Addd these in properties table
                Map<String, String> headerValues = new HashMap<>();
                headerValues.put(headerType, headerValue);
                FluxMessageRequestMapper.addHeaderValueToRequest(portType, headerValues);
            } catch (Exception e) {
                LOG.error("[ Error when setting header values ] {}", e.getMessage());
            }

            String ad = null;
            String dataFlow = null;

            try {
                ad = configValues.getCachedStringValue(ParameterKey.FLUX_AD);
                dataFlow = configValues.getCachedStringValue(ParameterKey.FLUX_DATAFLOW);
            } catch (Exception e) {
                LOG.error("[ Error when AD and dataflow values were retrieved: Aborting retrieval of vesels ] {}", e.getMessage());
                throw new ProxyException("Error when AD and dataflow values were retrieved: Aborting retrieval of vesels");
            }

            PostMsgType request = FluxMessageRequestMapper.mapToRequest(data, queryType, messageId, ad, dataFlow);
            PostMsgOutType resp = portType.post(request);

            LOG.info("Sending message to EU [ {} ] with messageID: [ {} ] dataFlow: [ {} ] endPoint: [ {} ]", queryType, messageId, request.getDF(), configValues.getCachedStringValue(ParameterKey.SERVICE_ENDPOINT));

            if (request.getID() != null && !request.getID().isEmpty()) {
                return request.getID();
            } else {
                throw new ProxyException("No MessageID in request, MessageID must be set!");
            }

        } catch (JAXBException | VesselProxyMappingException | ProxyException e) {
            LOG.error("[ Error when getting vessel. ] {}", e.getMessage());
            throw new ProxyException(e.getMessage());
        } catch (Exception e) {
            LOG.error("[ Error when getting vessel. ( Other ERROR ] {}", e.getMessage());
            throw new ProxyException(e.getMessage());
        }
    }

}