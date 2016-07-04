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
package eu.europa.ec.fisheries.uvms.proxy.vessel.mapper;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.dom.DOMResult;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import eu.europa.ec.fisheries.wsdl.asset.types.AssetIdType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import eu.europa.ec.fisheries.uvms.proxy.vessel.exception.VesselProxyMappingException;
import eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.common.FrQuery;
import eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.sender.BasicAttribute;
import eu.europa.ec.fisheries.uvms.proxy.vessel.util.DateUtil;
import xeu.connector_bridge.v1.PostMsgType;
import xeu.connector_bridge.v1.VerbosityType;

/**
 **/
public class FluxMessageRequestMapper {

    public static PostMsgType mapToRequest(String data, AssetIdType queryType, String messageId, String ad, String dataFlow) throws JAXBException, VesselProxyMappingException {
        try {
            PostMsgType message = new PostMsgType();
            message.setAD(ad);
            message.setAR(false);
            message.setDF(dataFlow);
            message.setDT(DateUtil.createXMLGregorianCalendar(new Date()));
            message.setID(messageId);
            message.setTO(60);

            Date timeInFuture = DateUtil.getTimeInFuture(1);
            message.setTODT(DateUtil.createXMLGregorianCalendar(timeInFuture));

            message.setTS(true);
            message.setVB(VerbosityType.ERROR);

            BasicAttribute attr = mapToBasicAttribute(data, queryType, messageId);

            JAXBContext context = JAXBContext.newInstance(BasicAttribute.class);
            Marshaller marshaller = context.createMarshaller();
            DOMResult res = new DOMResult();
            marshaller.marshal(attr, res);

            Element elt = ((Document) res.getNode()).getDocumentElement();

            message.setAny(elt);
            return message;
        } catch (Exception e) {
            throw new VesselProxyMappingException("Error when mapping to PostMsgType");
        }

    }

    public static BasicAttribute mapToBasicAttribute(String data, AssetIdType queryType, String messageId) throws VesselProxyMappingException {
        try {
            BasicAttribute attr = new BasicAttribute();
            //attr.setMessageId(UUID.randomUUID().toString());
            attr.setMessageId(messageId);
            attr.setCreation(DateUtil.createXMLGregorianCalendar(new Date()));

            FrQuery query = new FrQuery();

            switch (queryType) {
                case INTERNAL_ID:
                    throw new VesselProxyMappingException("Method get vessel by id not implemented");
                case CFR:
                    query.setCfr(data);
                    break;
                case IMO:
                    query.setExtMarking(data);
                    break;
                case IRCS:
                    query.setIrcs(data);
                    break;
                default:
                    throw new VesselProxyMappingException("Method not defined");

            }

            attr.setFrQuery(query);
            return attr;
        } catch (Exception e) {
            throw new VesselProxyMappingException("Error when mapping BasicAttribute");
        }
    }

    public static void addHeaderValueToRequest(Object port, final Map<String, String> values) throws VesselProxyMappingException {
        try {
            BindingProvider bp = (BindingProvider) port;
            Map<String, Object> context = bp.getRequestContext();

            Map<String, List<String>> headers = new HashMap<>();
            for (Entry entry : values.entrySet()) {
                headers.put(entry.getKey().toString(), Collections.singletonList(entry.getValue().toString()));
            }
            context.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        } catch (Exception e) {
            throw new VesselProxyMappingException("Error when header value to request");
        }
    }
}