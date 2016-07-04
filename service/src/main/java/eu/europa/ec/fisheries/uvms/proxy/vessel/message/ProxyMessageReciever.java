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
package eu.europa.ec.fisheries.uvms.proxy.vessel.message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.ws.rs.GET;

import eu.europa.ec.fisheries.uvms.asset.model.exception.AssetModelMapperException;
import eu.europa.ec.fisheries.uvms.asset.model.mapper.JAXBMarshaller;
import eu.europa.ec.fisheries.wsdl.asset.source.GetAssetRequest;
import eu.europa.ec.fisheries.wsdl.asset.types.AssetDataSourceRequest;
import eu.europa.ec.fisheries.wsdl.asset.types.AssetId;
import eu.europa.ec.fisheries.wsdl.asset.types.AssetIdType;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.europa.ec.fisheries.uvms.proxy.vessel.FluxMessageSender;
import eu.europa.ec.fisheries.uvms.proxy.vessel.cache.CacheHandler;
import eu.europa.ec.fisheries.uvms.proxy.vessel.constant.Constant;
import eu.europa.ec.fisheries.uvms.proxy.vessel.dto.ProxyResponse;
import eu.europa.ec.fisheries.uvms.proxy.vessel.event.VesselProxyErrorEvent;
import eu.europa.ec.fisheries.uvms.proxy.vessel.exception.ProxyException;

/**
 **/
@MessageDriven(mappedName = Constant.VESSEL_XEU_QUEUE, activationConfig = {
    @ActivationConfigProperty(propertyName = "messagingType", propertyValue = Constant.CONNECTION_TYPE),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = Constant.DESTINATION_TYPE_QUEUE),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = Constant.VESSEL_XEU_QUEUE_NAME),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class ProxyMessageReciever implements MessageListener {

    @Inject
    @VesselProxyErrorEvent
    Event<ProxyResponse> errorEvent;

    @EJB
    CacheHandler cache;

    @EJB
    FluxMessageSender fluxSender;

    private static Logger LOG = LoggerFactory.getLogger(ProxyMessageReciever.class);

    @Override
    public void onMessage(Message message) {
        try {
            recvieveMessage(message);
        } catch (AssetModelMapperException | JMSException | ProxyException e) {
            LOG.error("[ Error when recieveing message. ] {} {}", e.getMessage(), e.getStackTrace());
        }
    }

    /**
     *
     * @param message
     * @throws JMSException
     * @throws ProxyException
     * @throws
     * eu.europa.ec.fisheries.uvms.asset.model.exception.AssetModelMapperException
     */
    public void recvieveMessage(Message message) throws JMSException, ProxyException, AssetModelMapperException {

        TextMessage textMessage = (TextMessage) message;

        LOG.debug("Recieved  message in ProxyMessageReciever [ XEU ]");

        try {

            AssetDataSourceRequest request = JAXBMarshaller.unmarshallTextMessage(textMessage, AssetDataSourceRequest.class);

            switch (request.getMethod()) {
                case GET:
                    GetAssetRequest xmlMessage = JAXBMarshaller.unmarshallTextMessage(textMessage, GetAssetRequest.class);
                    AssetId assetId = xmlMessage.getId();
                    getVessel(textMessage, assetId.getType(), assetId.getValue());
                    break;
                case CREATE:
                case DELETE:
                case UPDATE:
                case LIST:
                case HISTORY_GET:
                case HISTORY_LIST:
                case UPSERT:
                    LOG.error("[ Error, method {} not implemented ] {}", request.getMethod().name());
                    errorEvent.fire(new ProxyResponse(message, "Method " + request.getMethod().name() + " implemented!"));
                    break;
                default:
                    LOG.error("[ Error, method {} not implemented ] {}", request.getMethod().name());
                    errorEvent.fire(new ProxyResponse(message, "Method " + request.getMethod().name() + " implemented!"));

            }

        } catch (JMSException e) {
            LOG.error("[ Error when receiving message. ] {}", e.getMessage());
            throw new ProxyException(e.getLocalizedMessage());
        }

    }

    public void getVessel(TextMessage message, AssetIdType queryType, String cfr) throws JMSException {
        try {
            String id = fluxSender.getVessel(cfr, queryType, message.getJMSMessageID());
            Element elmnt = new Element(id, new ProxyResponse(message));
            cache.getCache().put(elmnt);

            int cacheSize = cache.getCache().getKeys().size();
            LOG.info("[ Request CacheSize is now: ] {} ", cacheSize);

        } catch (ProxyException e) {
            LOG.error("[ Error when getting vessel. ] {} ", e.getMessage());
            errorEvent.fire(new ProxyResponse(message, e.getMessage()));
        }
    }

}