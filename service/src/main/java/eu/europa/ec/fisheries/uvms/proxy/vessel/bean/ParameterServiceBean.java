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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.europa.ec.fisheries.uvms.asset.model.exception.InputArgumentException;
import eu.europa.ec.fisheries.uvms.asset.model.exception.AssetModelException;
import eu.europa.ec.fisheries.uvms.proxy.vessel.constant.Constant;
import eu.europa.ec.fisheries.uvms.proxy.vessel.constant.ParameterKey;
import eu.europa.ec.fisheries.uvms.proxy.vessel.entity.Parameter;
import eu.europa.ec.fisheries.uvms.proxy.vessel.exception.ProxyException;
import javax.ejb.LocalBean;

@LocalBean
@Stateless
public class ParameterServiceBean {

    @PersistenceContext(unitName = "proxyPU")
    EntityManager em;

    final static Logger LOG = LoggerFactory.getLogger(ParameterServiceBean.class);

    public String getStringValue(ParameterKey key) throws ProxyException {
        try {
            Query query = em.createNamedQuery(Constant.FIND_BY_NAME);
            query.setParameter("key", key.getKey());
            Parameter entity = (Parameter) query.getSingleResult();
            return entity.getParamValue();
        } catch (Exception e) {
            LOG.error("[ Error when getting string value from parameter key. ]");
            throw new ProxyException(e.getMessage());
        }
    }

    public Boolean getBooleanValue(ParameterKey key) throws ProxyException {
        try {
            Query query = em.createNamedQuery(Constant.FIND_BY_NAME);
            query.setParameter("key", key.getKey());
            Parameter entity = (Parameter) query.getSingleResult();
            return parseBooleanValue(entity.getParamValue());
        } catch (AssetModelException e) {
            LOG.error("[ Error when getting boolean value from parameter key. ]");
            throw new ProxyException(e.getMessage());
        }
    }

    private Boolean parseBooleanValue(String value) throws InputArgumentException, ProxyException {
        try {
            if (value.equalsIgnoreCase("true")) {
                return Boolean.TRUE;
            } else if (value.equalsIgnoreCase("false")) {
                return Boolean.FALSE;
            } else {
                throw new InputArgumentException("The String value provided does not equal boolean value, value provided = " + value);
            }
        } catch (Exception e) {
            LOG.error("[ Error when parsing boolean value from string. ] ");
            throw new ProxyException(e.getMessage());
        }
    }
}