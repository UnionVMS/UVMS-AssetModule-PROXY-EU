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
package eu.europa.ec.fisheries.uvms.proxy.vessel.bean;

import eu.europa.ec.fisheries.uvms.proxy.vessel.PortInitiator;
import eu.europa.ec.fisheries.uvms.proxy.vessel.constant.ParameterKey;
import eu.europa.ec.fisheries.uvms.proxy.vessel.exception.ProxyException;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.slf4j.LoggerFactory;

/**
 **/
@Singleton
@Startup
@DependsOn({"ParameterServiceBean", "PortInitiator"})
public class ParameterCacheBean {

    @EJB
    ParameterServiceBean bean;

    @EJB
    PortInitiator portInitiator;

    final static org.slf4j.Logger LOG = LoggerFactory.getLogger(ParameterServiceBean.class);

    private ConcurrentHashMap<ParameterKey, String> params = new ConcurrentHashMap<>();

    public String getCachedStringValue(ParameterKey key) {
        return params.get(key);
    }

    @PostConstruct
    public void initValues() {
        updatateCache();
    }

    @Schedule(second = "0", minute = "*/5", hour = "*", persistent = false)
    public void updatateCache() {
        for (ParameterKey key : ParameterKey.values()) {
            try {
                String value = bean.getStringValue(key);
                LOG.info("Adding new config value KEY: {}, VALUE {}", key.getKey(), value);
                params.put(key, value);
            } catch (ProxyException ex) {
                LOG.error("Updating cache: Failure when getting parameter {}", key.getKey());
            }
        }
        portInitiator.updatePortValues();
    }

}