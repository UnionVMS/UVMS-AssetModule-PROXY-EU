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

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import eu.europa.ec.fisheries.uvms.proxy.vessel.FluxMessageSender;

/**
 **/
@RunWith(MockitoJUnitRunner.class)
public class TestProxyMessageRecciever {

    private static final Long ID = 1L;
    private static final String STRING = "String";

    @Mock
    FluxMessageSender client;

    @Mock
    JMSContext context;

    @InjectMocks
    ProxyMessageReciever receiver;

    @Mock
    ObjectMessage message;

    @Mock
    ObjectMessage replyMessage;

    @Mock
    Queue queue;

    @Mock
    JMSProducer producer;

    //MockDto mockDto = MockData.getDto(ID);
    @Before
    public void setUp() throws JMSException {
        when(context.createObjectMessage()).thenReturn(replyMessage);
        when(message.getJMSReplyTo()).thenReturn(queue);
        when(message.isBodyAssignableTo(Long.class)).thenReturn(true);
        when(context.createProducer()).thenReturn(producer);
    }

    @Test
    public void testGetById() {
        /*try {
         when(context.createObjectMessage(1L)).thenReturn(message);
         when(client.getVesselByCfr(ID)).thenReturn(mockDto);
         when(message.getBody(Long.class)).thenReturn(ID);

         ObjectMessage msg = context.createObjectMessage(ID);
         receiver.recvieveMessage(msg);

         Mockito.verify(client).getDataById(ID);
         Mockito.verify(client, Mockito.times(0)).getDataByStringValue("STRING");
         } catch (ProxyException | JMSException ex) {
         Assert.fail(ex.getMessage());
         }*/
    }

}