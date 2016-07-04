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
package eu.europa.ec.fisheries.uvms.proxy.vessel.cache;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 **/
@Singleton
public class CacheHandlerBean implements CacheHandler {

    private CacheManager manager;

    @PostConstruct
    public void init() {
        manager = CacheManager.getInstance();
    }

    @Override
    public Cache getCache() {
        return manager.getCache("messages");
    }

}