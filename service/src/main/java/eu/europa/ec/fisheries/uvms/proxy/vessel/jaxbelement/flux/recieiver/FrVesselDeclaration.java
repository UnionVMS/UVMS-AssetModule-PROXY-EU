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
package eu.europa.ec.fisheries.uvms.proxy.vessel.jaxbelement.flux.recieiver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 **/
@XmlRootElement(name = "FR-VesselDeclaratio", namespace = "urn:xeu:ec:fisheries:flux-bl:FLUX_FR_VesselDeclarationList:0")
@XmlAccessorType(XmlAccessType.FIELD)
public class FrVesselDeclaration {

    @XmlElement(name = "CountryOfRegistration")
    private String countryOfRegistration;
    @XmlElement(name = "CFR")
    private String cfr;
    @XmlElement(name = "EventCode")
    private String eventCode;
    @XmlElement(name = "DateOfEvent")
    private String dayOfEvent;
    @XmlElement(name = "LicenceIndicator")
    private String licenceIndicator;
    @XmlElement(name = "RegistrationNumber")
    private String registrationNumber;
    @XmlElement(name = "ExternalMarking")
    private String externalMarking;
    @XmlElement(name = "NameOfVessel")
    private String nameOfVessel;
    @XmlElement(name = "PortOfRegistration")
    private String portOfRegistration;
    @XmlElement(name = "IRCSIndicator")
    private String ircsIndicator;
    @XmlElement(name = "IRCS")
    private String ircs;
    @XmlElement(name = "VMSIndicator")
    private String vmsIndicator;
    @XmlElement(name = "MainFishingGear")
    private String mainFishingGear;
    @XmlElement(name = "SubsidiaryFishingGear")
    private String subsidiaryFishingGear;
    @XmlElement(name = "LOA")
    private String loa;
    @XmlElement(name = "LBP")
    private String lbp;
    @XmlElement(name = "TonnageGT")
    private String tonnageGt;
    @XmlElement(name = "OtherTonnage")
    private String otherTonnage;
    @XmlElement(name = "GTs")
    private String gts;
    @XmlElement(name = "PowerOfMainEngine")
    private String powerOfMainEngine;
    @XmlElement(name = "PowerOfAuxiliaryEngine")
    private String powerOfAuxiliaryEngine;
    @XmlElement(name = "HullMaterial")
    private String hullMaterial;
    @XmlElement(name = "YearOfCommissioning")
    private String yearOfCommissioning;
    @XmlElement(name = "MonthOfCommissioning")
    private String monthOfCommissioning;
    @XmlElement(name = "DayOfCommissioning")
    private String dayOfCommissioning;
    @XmlElement(name = "Segment")
    private String segment;
    @XmlElement(name = "CountryOfImportation_Exportation")
    private String countryOfImportationExportation;
    @XmlElement(name = "TypeOfExport")
    private String typeOfExport;
    @XmlElement(name = "CodeForPublicAid")
    private String codeForPublicAid;
    @XmlElement(name = "DateOfAdministrativeDecision")
    private String dateOfAdministrativeDecision;
    @XmlElement(name = "SegmentCoveredByAdministrativeDecision")
    private String segmentCoveredByAdministrativeDecision;
    @XmlElement(name = "YearOfConstruction")
    private String yearOfConstruction;
    @XmlElement(name = "PlaceOfConstruction")
    private String placeOfConstruction;
    @XmlElement(name = "NameOfVesselAgent")
    private String nameOfVesselAgent;
    @XmlElement(name = "AddressOfVesselAgent")
    private String addressOfVesselAgent;
    @XmlElement(name = "IndicatorOfOwner")
    private String indicatorOfOwner;
    @XmlElement(name = "NameOfOwner")
    private String nameOfOwner;
    @XmlElement(name = "AddressOfOwner")
    private String addressOfOwner;

    public String getCountryOfRegistration() {
        return countryOfRegistration;
    }

    public void setCountryOfRegistration(String countryOfRegistration) {
        this.countryOfRegistration = countryOfRegistration;
    }

    public String getCfr() {
        return cfr;
    }

    public void setCfr(String cfr) {
        this.cfr = cfr;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getDayOfEvent() {
        return dayOfEvent;
    }

    public void setDayOfEvent(String dayOfEvent) {
        this.dayOfEvent = dayOfEvent;
    }

    public String getLicenceIndicator() {
        return licenceIndicator;
    }

    public void setLicenceIndicator(String licenceIndicator) {
        this.licenceIndicator = licenceIndicator;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getExternalMarking() {
        return externalMarking;
    }

    public void setExternalMarking(String externalMarking) {
        this.externalMarking = externalMarking;
    }

    public String getNameOfVessel() {
        return nameOfVessel;
    }

    public void setNameOfVessel(String nameOfVessel) {
        this.nameOfVessel = nameOfVessel;
    }

    public String getPortOfRegistration() {
        return portOfRegistration;
    }

    public void setPortOfRegistration(String portOfRegistration) {
        this.portOfRegistration = portOfRegistration;
    }

    public String getIrcsIndicator() {
        return ircsIndicator;
    }

    public void setIrcsIndicator(String ircsIndicator) {
        this.ircsIndicator = ircsIndicator;
    }

    public String getIrcs() {
        return ircs;
    }

    public void setIrcs(String ircs) {
        this.ircs = ircs;
    }

    public String getVmsIndicator() {
        return vmsIndicator;
    }

    public void setVmsIndicator(String vmsIndicator) {
        this.vmsIndicator = vmsIndicator;
    }

    public String getMainFishingGear() {
        return mainFishingGear;
    }

    public void setMainFishingGear(String mainFishingGear) {
        this.mainFishingGear = mainFishingGear;
    }

    public String getSubsidiaryFishingGear() {
        return subsidiaryFishingGear;
    }

    public void setSubsidiaryFishingGear(String subsidiaryFishingGear) {
        this.subsidiaryFishingGear = subsidiaryFishingGear;
    }

    public String getLoa() {
        return loa;
    }

    public void setLoa(String loa) {
        this.loa = loa;
    }

    public String getLbp() {
        return lbp;
    }

    public void setLbp(String lbp) {
        this.lbp = lbp;
    }

    public String getTonnageGt() {
        return tonnageGt;
    }

    public void setTonnageGt(String tonnageGt) {
        this.tonnageGt = tonnageGt;
    }

    public String getOtherTonnage() {
        return otherTonnage;
    }

    public void setOtherTonnage(String otherTonnage) {
        this.otherTonnage = otherTonnage;
    }

    public String getGts() {
        return gts;
    }

    public void setGts(String gts) {
        this.gts = gts;
    }

    public String getPowerOfMainEngine() {
        return powerOfMainEngine;
    }

    public void setPowerOfMainEngine(String powerOfMainEngine) {
        this.powerOfMainEngine = powerOfMainEngine;
    }

    public String getPowerOfAuxiliaryEngine() {
        return powerOfAuxiliaryEngine;
    }

    public void setPowerOfAuxiliaryEngine(String powerOfAuxiliaryEngine) {
        this.powerOfAuxiliaryEngine = powerOfAuxiliaryEngine;
    }

    public String getHullMaterial() {
        return hullMaterial;
    }

    public void setHullMaterial(String hullMaterial) {
        this.hullMaterial = hullMaterial;
    }

    public String getYearOfCommissioning() {
        return yearOfCommissioning;
    }

    public void setYearOfCommissioning(String yearOfCommissioning) {
        this.yearOfCommissioning = yearOfCommissioning;
    }

    public String getMonthOfCommissioning() {
        return monthOfCommissioning;
    }

    public void setMonthOfCommissioning(String monthOfCommissioning) {
        this.monthOfCommissioning = monthOfCommissioning;
    }

    public String getDayOfCommissioning() {
        return dayOfCommissioning;
    }

    public void setDayOfCommissioning(String dayOfCommissioning) {
        this.dayOfCommissioning = dayOfCommissioning;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getCountryOfImportationExportation() {
        return countryOfImportationExportation;
    }

    public void setCountryOfImportationExportation(String countryOfImportationExportation) {
        this.countryOfImportationExportation = countryOfImportationExportation;
    }

    public String getTypeOfExport() {
        return typeOfExport;
    }

    public void setTypeOfExport(String typeOfExport) {
        this.typeOfExport = typeOfExport;
    }

    public String getCodeForPublicAid() {
        return codeForPublicAid;
    }

    public void setCodeForPublicAid(String codeForPublicAid) {
        this.codeForPublicAid = codeForPublicAid;
    }

    public String getDateOfAdministrativeDecision() {
        return dateOfAdministrativeDecision;
    }

    public void setDateOfAdministrativeDecision(String dateOfAdministrativeDecision) {
        this.dateOfAdministrativeDecision = dateOfAdministrativeDecision;
    }

    public String getSegmentCoveredByAdministrativeDecision() {
        return segmentCoveredByAdministrativeDecision;
    }

    public void setSegmentCoveredByAdministrativeDecision(String segmentCoveredByAdministrativeDecision) {
        this.segmentCoveredByAdministrativeDecision = segmentCoveredByAdministrativeDecision;
    }

    public String getYearOfConstruction() {
        return yearOfConstruction;
    }

    public void setYearOfConstruction(String yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }

    public String getPlaceOfConstruction() {
        return placeOfConstruction;
    }

    public void setPlaceOfConstruction(String placeOfConstruction) {
        this.placeOfConstruction = placeOfConstruction;
    }

    public String getNameOfVesselAgent() {
        return nameOfVesselAgent;
    }

    public void setNameOfVesselAgent(String nameOfVesselAgent) {
        this.nameOfVesselAgent = nameOfVesselAgent;
    }

    public String getAddressOfVesselAgent() {
        return addressOfVesselAgent;
    }

    public void setAddressOfVesselAgent(String addressOfVesselAgent) {
        this.addressOfVesselAgent = addressOfVesselAgent;
    }

    public String getIndicatorOfOwner() {
        return indicatorOfOwner;
    }

    public void setIndicatorOfOwner(String indicatorOfOwner) {
        this.indicatorOfOwner = indicatorOfOwner;
    }

    public String getNameOfOwner() {
        return nameOfOwner;
    }

    public void setNameOfOwner(String nameOfOwner) {
        this.nameOfOwner = nameOfOwner;
    }

    public String getAddressOfOwner() {
        return addressOfOwner;
    }

    public void setAddressOfOwner(String addressOfOwner) {
        this.addressOfOwner = addressOfOwner;
    }

    @Override
    public String toString() {
        return "FrVesselDeclaration{" + "countryOfRegistration=" + countryOfRegistration + ", cfr=" + cfr + ", eventCode=" + eventCode + ", dayOfEvent=" + dayOfEvent + ", licenceIndicator=" + licenceIndicator + ", registrationNumber=" + registrationNumber + ", externalMarking=" + externalMarking + ", nameOfVessel=" + nameOfVessel + ", portOfRegistration=" + portOfRegistration + ", ircsIndicator=" + ircsIndicator + ", ircs=" + ircs + ", vmsIndicator=" + vmsIndicator + ", mainFishingGear=" + mainFishingGear + ", subsidiaryFishingGear=" + subsidiaryFishingGear + ", loa=" + loa + ", lbp=" + lbp + ", tonnageGt=" + tonnageGt + ", otherTonnage=" + otherTonnage + ", gts=" + gts + ", powerOfMainEngine=" + powerOfMainEngine + ", powerOfAuxiliaryEngine=" + powerOfAuxiliaryEngine + ", hullMaterial=" + hullMaterial + ", yearOfCommissioning=" + yearOfCommissioning + ", monthOfCommissioning=" + monthOfCommissioning + ", dayOfCommissioning=" + dayOfCommissioning + ", segment=" + segment + ", countryOfImportationExportation=" + countryOfImportationExportation + ", typeOfExport=" + typeOfExport + ", codeForPublicAid=" + codeForPublicAid + ", dateOfAdministrativeDecision=" + dateOfAdministrativeDecision + ", segmentCoveredByAdministrativeDecision=" + segmentCoveredByAdministrativeDecision + ", yearOfConstruction=" + yearOfConstruction + ", placeOfConstruction=" + placeOfConstruction + ", nameOfVesselAgent=" + nameOfVesselAgent + ", addressOfVesselAgent=" + addressOfVesselAgent + ", indicatorOfOwner=" + indicatorOfOwner + ", nameOfOwner=" + nameOfOwner + ", addressOfOwner=" + addressOfOwner + '}';
    }

}