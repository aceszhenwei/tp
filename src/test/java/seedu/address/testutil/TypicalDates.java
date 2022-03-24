package seedu.address.testutil;

import seedu.address.commons.core.dateWithoutTime.DateWithoutTime;
import seedu.address.model.procedure.Date;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalDates {
    public static final Date DATE_WITH_TIME_FIRST = new Date("22/03/2018 23:00");
    public static final Date DATE_WITH_TIME_SECOND = new Date("31/12/2020 00:00");
    public static final Date DATE_WITH_TIME_THIRD = new Date("29/02/2016 06:00");
    public static final Date DATE_WITH_TIME_INVALID = new Date("29/02/2016 26:00");


    public static final DateWithoutTime DATE_WITHOUT_TIME_FIRST = new DateWithoutTime("22/03/2018");
    public static final DateWithoutTime DATE_WITHOUT_TIME_SECOND = new DateWithoutTime("31/12/2020");
    public static final DateWithoutTime DATE_WITHOUT_TIME_THIRD = new DateWithoutTime("29/02/2016");
    public static final DateWithoutTime DATE_WITHOUT_TIME_INVALID = new DateWithoutTime("39/02/2016");

}
