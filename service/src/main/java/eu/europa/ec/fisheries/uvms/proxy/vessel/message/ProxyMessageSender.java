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

import eu.europa.ec.fisheries.uvms.asset.model.exception.AssetModelMapperException;
import eu.europa.ec.fisheries.uvms.asset.model.mapper.JAXBMarshaller;
import eu.europa.ec.fisheries.uvms.proxy.vessel.constant.Constant;
import eu.europa.ec.fisheries.uvms.proxy.vessel.dto.ProxyResponse;
import eu.europa.ec.fisheries.uvms.proxy.vessel.event.FluxMessageRecievedEvent;
import eu.europa.ec.fisheries.uvms.proxy.vessel.event.VesselProxyErrorEvent;
import eu.europa.ec.fisheries.wsdl.asset.types.AssetFault;
import eu.europa.ec.fisheries.wsdl.asset.types.SingleAssetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.jms.*;

/**
 **/
@LocalBean
@Stateless
public class ProxyMessageSender {
    
    @Resource(lookup = Constant.CONNECTION_FACTORY)
    private ConnectionFactory connectionFactory;

    private Connection connection = null;
    private Session session = null;

    private static Logger LOG = LoggerFactory.getLogger(ProxyMessageSender.class);

    public void sendMessage(@Observes @FluxMessageRecievedEvent ProxyResponse message) {
        try {
            
            LOG.info("Sending message back to recipient om JMS Queue with correlationID: {}.", message.getJmsMessage().getJMSMessageID());
            
            connectToQueue();

            TextMessage response = session.createTextMessage();
            SingleAssetResponse request = new SingleAssetResponse();
            request.setAsset(message.getResponse());
            
            String responseString = JAXBMarshaller.marshallJaxBObjectToString(request);
            
            response.setText(responseString);
            response.setJMSCorrelationID(message.getJmsMessage().getJMSCorrelationID());
            
            session.createProducer(message.getJmsMessage().getJMSReplyTo()).send(response);
        } catch (JMSException | AssetModelMapperException e) {
            LOG.error("[ Error when sending message. ] {} {}", e.getMessage(), e.getStackTrace());
        } finally {
            try {
                connection.stop();
                connection.close();
            } catch (JMSException e) {
                LOG.error("[ Error when stopping or closing JMS queue. ] {} {}", e.getMessage(), e.getStackTrace());
            }
        }
        
    }
    
    public void sendErrorMessage(@Observes @VesselProxyErrorEvent ProxyResponse message) {
        try {

            LOG.info("Sending error message back to recipient.");
            
            connectToQueue();

            TextMessage response = session.createTextMessage();
            
            AssetFault errorResponse = new AssetFault();
            errorResponse.setFault(message.getErrorMessage());
            
            String responseString = JAXBMarshaller.marshallJaxBObjectToString(errorResponse);
            
            response.setText(responseString);
            response.setJMSCorrelationID(message.getJmsMessage().getJMSCorrelationID());
            session.createProducer(message.getJmsMessage().getJMSReplyTo()).send(response);
            
        } catch (JMSException | AssetModelMapperException e) {
            LOG.error("[ Error when sending error message. ] {} {}", e.getMessage(), e.getStackTrace());
        } finally {
            try {
                connection.stop();
                connection.close();
            } catch (JMSException e) {
                LOG.error("[ Error when stopping or closing JMS queue. ] {} {}", e.getMessage(), e.getStackTrace());
            }
        }
        
    }
    
    private void connectToQueue() throws JMSException {
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
    }

}