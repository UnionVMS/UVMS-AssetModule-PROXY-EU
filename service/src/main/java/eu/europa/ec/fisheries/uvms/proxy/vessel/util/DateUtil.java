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
package eu.europa.ec.fisheries.uvms.proxy.vessel.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

    private static DatatypeFactory dataTypeFactory;
    public static final int DESCENDING = -1;
    public static final int ASCENDING = 1;

    private static Logger LOG = LoggerFactory.getLogger(DateUtil.class);

    static {
        try {
            dataTypeFactory = DatatypeFactory.newInstance();
        } catch (final DatatypeConfigurationException e) {
            LOG.error("[ Error when instantiating a DatatypeFactory. ] {} {}", e.getMessage(), e.getStackTrace());
        }
    }

    public static int compareDates(Date d1, Date d2, int order) {
        if (d1 != null && d2 != null) {
            return d1.compareTo(d2) * order;
        }
        if (d2 != null && d1 == null) {
            return -1 * order;
        }
        if (d2 == null && d1 != null) {
            return 1 * order;
        }
        return 0;
    }

    /**
     * Takes an {@link XMLGregorianCalendar} and converts it to an {@link Date}
     * .
     *
     * @param xmlGregorianCalendar the {@link XMLGregorianCalendar} that should
     * be transformed
     * @return the created {@link Date}. If an {@link Date} could not be
     * created, the return value is {@code null}
     */
    public static Date getDate(final XMLGregorianCalendar xmlGregorianCalendar) {
        Date date = null;
        if (xmlGregorianCalendar != null) {
            final GregorianCalendar gregorianCalendar = xmlGregorianCalendar.toGregorianCalendar();
            date = gregorianCalendar.getTime();
        }
        return date;
    }

    public static Date getDate(final XMLGregorianCalendar inDate, final XMLGregorianCalendar inTime) {
        Date date = getDate(inDate);
        Date time = getDate(inTime);

        long joinedDate = date.getTime() + time.getTime();

        return new Date(joinedDate);
    }

    /**
     * Takes nowtime and adds hours
     *
     * @param hours
     * @return
     */
    public static Date getTimeInFuture(Integer hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        return cal.getTime();
    }

    public static XMLGregorianCalendar createXMLGregorianCalendar(final Date date) {
        return createXMLGregorianCalendar(date, TimeZone.getDefault());
    }

    public static XMLGregorianCalendar createXMLGregorianCalendar(final Date date, final TimeZone timeZone) {
        XMLGregorianCalendar xmlCalendar = null;
        if (date != null) {
            synchronized (DateUtil.class) {
                final GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone);
                gregorianCalendar.setTime(date);
                xmlCalendar = createXMLGregorianCalendar(gregorianCalendar);
            }
        }
        return xmlCalendar;
    }

    /**
     * Creates a XMLGregorianCalendar from a java.util.GregorianCalendar
     *
     * @param cal the {@link GregorianCalendar} to create from
     * @return an XMLGregorianCalendar for the calendar
     */
    public static XMLGregorianCalendar createXMLGregorianCalendar(final GregorianCalendar cal) {
        if (cal != null) {
            synchronized (DateUtil.class) {
                return dataTypeFactory.newXMLGregorianCalendar(cal);
            }
        }
        return null;
    }

    /**
     * Creates a {@link Date} from a {@link String}
     *
     * @param input A {@link String} on the format "YYYYMMDD"
     * @return A {@link Date} representation of date, with time set to 00:00:00
     * set according to UTC timezone.
     * @throws {@link NumberFormatException} when the input parameter isn't as
     * expected.
     */
    public static Date getTimeFromString(String input) {
        if (input != null && !input.trim().isEmpty()) {
            final String pattern = "(19|20){1}\\d{6}";
            if (input.length() == 8 & input.matches(pattern)) {
                DateTime time = new DateTime(new Integer(input.substring(0, 4)).intValue() // year
                        , new Integer(input.substring(4, 6)).intValue() // month
                        , new Integer(input.substring(6, 8)).intValue() // day
                        , 0 // hour
                        , 0 // minute
                        , DateTimeZone.UTC);
                time = time.withMillisOfDay(0);
                return new Date(time.getMillis());
            }
        }
        throw new NumberFormatException("Provided string is not parsable for a date. Received: " + input);
    }

}