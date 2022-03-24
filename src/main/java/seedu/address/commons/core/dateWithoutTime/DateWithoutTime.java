package seedu.address.commons.core.dateWithoutTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import seedu.address.model.procedure.Date;

/**
 * Represents a Procedure's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class DateWithoutTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Please ensure that you have typed an existing date in the correct format. \n"
                    + "Dates should be in the format DD/MM/YYYY, and it should not be blank \n"
                    + "Year entered must be less than 10000";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$";

    public static final DateTimeFormatter FORMAT_WITH_DATE = DateTimeFormatter
            .ofPattern("dd/MM/uuuu");

    public final LocalDate validDate;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public DateWithoutTime(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        validDate = LocalDate.parse(date, FORMAT_WITH_DATE); // need to check
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            try {
                System.out.println("correct format");
                LocalDate.parse(test, FORMAT_WITH_DATE
                        .withResolverStyle(ResolverStyle.STRICT));
                return true;
            } catch (DateTimeParseException err) {
                System.out.println("invalid date");
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return validDate.format(FORMAT_WITH_DATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && validDate.equals(((Date) other).validDate)); // state check
    }
}

