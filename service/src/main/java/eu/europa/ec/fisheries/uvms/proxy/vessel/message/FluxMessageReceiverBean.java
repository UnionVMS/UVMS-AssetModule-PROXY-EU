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

import eu.europa.ec.fisheries.uvms.proxy.vessel.cache.CacheHandler;
import eu.europa.ec.fisheries.uvms.proxy.vessel.dto.ProxyResponse;
import eu.europa.ec.fisheries.uvms.proxy.vessel.event.FluxMessageRecievedEvent;
import eu.europa.ec.fisheries.uvms.proxy.vessel.exception.VesselProxyMappingException;
import eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.recieiver.BasicAttribute;
import eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.recieiver.FrVesselDeclaration;
import eu.europa.ec.fisheries.uvms.proxy.vessel.mapper.FluxMessageResponseMapper;
import eu.europa.ec.fisheries.wsdl.asset.types.Asset;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;
import org.jboss.ws.api.annotation.WebContext;
import xeu.bridge_connector.v1.RequestType;
import xeu.bridge_connector.v1.ResponseType;
import xeu.bridge_connector.wsdl.v1.BridgeConnectorPortType;

/**
 **/
@Stateless
@WebContext(contextRoot = "/XeuVesselProxy")
@WebService(serviceName = "VesselService", targetNamespace = "urn:xeu:bridge-connector:wsdl:v1", portName = "BridgeConnectorPortType", endpointInterface = "xeu.bridge_connector.wsdl.v1.BridgeConnectorPortType")
public class FluxMessageReceiverBean implements BridgeConnectorPortType {

    @EJB
    CacheHandler cacheHandler;

    @Inject
    @FluxMessageRecievedEvent
    Event<ProxyResponse> vesselEvent;

    private static Logger LOG = LoggerFactory.getLogger(FluxMessageReceiverBean.class);

    @Override
    public ResponseType post(RequestType rt) {
        ResponseType type = new ResponseType();
        try {

            BasicAttribute response = FluxMessageResponseMapper.extractBasicAttribute(rt.getAny());
            String correlationId = FluxMessageResponseMapper.extractCorrelationId(response);
            FrVesselDeclaration vesselResponse = FluxMessageResponseMapper.extractVessel(response);
            Asset asset = FluxMessageResponseMapper.mapResponse(vesselResponse);

            LOG.info("Got response from EU with correlationId: {}", correlationId);

            if (correlationId != null && !correlationId.isEmpty()) {
                Element elmnt = cacheHandler.getCache().get(correlationId);
                if (elmnt != null) {
                    ProxyResponse resp = (ProxyResponse) elmnt.getObjectValue();
                    resp.setResponse(asset);
                    LOG.info("Found Request in cache ID : {}", correlationId);
                    vesselEvent.fire(resp);
                } else {
                    int cacheSize = cacheHandler.getCache().getKeys().size();
                    LOG.info("Could not find Request in cache ID : {}, Size of cache is: {} ", correlationId, cacheSize);
                }
            }

            type.setStatus("OK");
            return type;
        } catch (VesselProxyMappingException | JAXBException | IllegalStateException | CacheException e) {
            LOG.error("[ Error when getting data. ] {} {}", e.getMessage(), e.getStackTrace());
            type.setStatus("NOK");
            return type;
        }
    }

}