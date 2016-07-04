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
package eu.europa.ec.fisheries.uvms.proxy.vessel.mapper;

import eu.europa.ec.fisheries.uvms.proxy.vessel.exception.VesselProxyMappingException;
import eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.recieiver.BasicAttribute;
import eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.recieiver.FrVesselDeclaration;
import eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.recieiver.Response;
import eu.europa.ec.fisheries.wsdl.asset.types.Asset;
import eu.europa.ec.fisheries.wsdl.asset.types.CarrierSource;

import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

/**
 **/
public class FluxMessageResponseMapper {

    private static Logger LOG = LoggerFactory.getLogger(FluxMessageResponseMapper.class);

    public static BasicAttribute extractBasicAttribute(Element elmnt) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(BasicAttribute.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        BasicAttribute xmlMessage = (BasicAttribute) unmarshaller.unmarshal(elmnt);
        return xmlMessage;
    }

    public static FrVesselDeclaration extractVessel(BasicAttribute attribute) throws VesselProxyMappingException {
        try {
            if (attribute != null) {
                Response response = attribute.getResponse();
                if (response != null) {
                    return response.getVessel();
                } else {
                    throw new VesselProxyMappingException("Error when extracting FrVesselDeclaration ( Vessel ) : Response in BasicAttribute is null");
                }
            } else {
                throw new VesselProxyMappingException("Error when extracting FrVesselDeclaration ( Vessel ): BasicAttribute is null");
            }
        } catch (Exception e) {
            LOG.error("[ Error when extracting vessel. ] {}", e.getMessage());
            throw new VesselProxyMappingException("Error when extracting FrVesselDeclaration ( Vessel ) ");
        }

    }

    public static String extractCorrelationId(BasicAttribute attribute) throws VesselProxyMappingException {
        try {
            if (attribute != null) {
                Response response = attribute.getResponse();
                if (response != null) {
                    return response.getCorrelationId();
                } else {
                    throw new VesselProxyMappingException("Error when extracting Correlation ID: Response in BasicAttribute is null");
                }
            } else {
                throw new VesselProxyMappingException("Error when extracting Correlation ID: BasicAttribute is null");
            }
        } catch (Exception e) {
            LOG.error("[ Error when extracting correlation ID. ] {}", e.getMessage());
            throw new VesselProxyMappingException("Error when extracting Correlation ID: " + e.getMessage());
        }
    }

    public static Asset mapResponse(FrVesselDeclaration response) throws JAXBException {
        Asset asset = new Asset();

        if (response != null) {

            if (response != null) {

                asset.setCfr(response.getCfr());
                asset.setCountryCode(response.getCountryOfRegistration());
                asset.setExternalMarking(response.getExternalMarking());
                asset.setHomePort(response.getPortOfRegistration());
                asset.setIrcs(response.getIrcs());
                asset.setName(response.getNameOfVessel());

                try {
                    asset.setHasLicense(convertYNToBoolean(response.getLicenceIndicator()));
                } catch (VesselProxyMappingException e) {
                    LOG.error("[ Error when converting licence indicator to boolean. ] {} {}", e.getMessage());
                }

                try {
                    asset.setGrossTonnage(convertStringToBigDecimal(response.getTonnageGt()));
                } catch (VesselProxyMappingException e) {
                    LOG.error("[ Error when converting tonnage to number. ] {} {}", e.getMessage());
                }

                asset.setHasIrcs(response.getIrcsIndicator());

                try {
                    asset.setLengthBetweenPerpendiculars(convertStringToBigDecimal(response.getLbp()));
                } catch (VesselProxyMappingException e) {
                    LOG.error("[ Error when converting LBP to number. ] {} ", e.getMessage());
                }

                try {
                    asset.setLengthOverAll(convertStringToBigDecimal(response.getLoa()));
                } catch (VesselProxyMappingException e) {
                    LOG.error("[ Error when converting LOA to number. ] {} ", e.getMessage());
                }

                try {
                    asset.setOtherGrossTonnage(convertStringToBigDecimal(response.getOtherTonnage()));
                } catch (VesselProxyMappingException e) {
                    LOG.error("[ Error when converting other tonnage to number. ] {} ", e.getMessage());
                }

                try {
                    asset.setPowerAux(convertStringToBigDecimal(response.getPowerOfAuxiliaryEngine()));
                } catch (VesselProxyMappingException e) {
                    LOG.error("[ Error when converting power of auxiliary engine to number. ] {}", e.getMessage());
                }

                try {
                    asset.setPowerMain(convertStringToBigDecimal(response.getPowerOfMainEngine()));
                } catch (VesselProxyMappingException e) {
                    LOG.error("[ Error when converting power of main engine to number. ] {}", e.getMessage());
                }

                try {
                    asset.setSafetyGrossTonnage(convertStringToBigDecimal(response.getGts()));
                } catch (VesselProxyMappingException e) {
                    LOG.error("[ Error when converting GTS to number. ] {}", e.getMessage());
                }

                asset.setSource(CarrierSource.XEU);
            }
        }

        return asset;
    }

    public static BigDecimal convertStringToBigDecimal(String value) throws VesselProxyMappingException {
        try {
            Double parsedDouble = Double.parseDouble(value);
            BigDecimal parsedBigDecimal = BigDecimal.valueOf(parsedDouble);
            return parsedBigDecimal;
        } catch (Exception e) {
            LOG.error("[ Error when parsing decimal value from string. ] {}", e.getMessage());
            throw new VesselProxyMappingException("Error when converting String to Decimal: " + e.getMessage());
        }
    }

    public static boolean convertYNToBoolean(String data) throws VesselProxyMappingException {
        try {
            if (data != null && !data.isEmpty()) {
                if (data.equalsIgnoreCase("Y")) {
                    return true;
                } else if (data.equalsIgnoreCase("N")) {
                    return false;
                } else {
                    throw new VesselProxyMappingException("Error when converting Y N to boolean, String does not match Y or N");
                }
            } else {
                throw new VesselProxyMappingException("Error when converting Y N to boolean, input string is null or empty");
            }
        } catch (Exception e) {
            LOG.error("[ Error when converting string to boolean. ] {}", e.getMessage());
            throw new VesselProxyMappingException("Error when converting Y N to boolean: " + e.getMessage());
        }
    }

}