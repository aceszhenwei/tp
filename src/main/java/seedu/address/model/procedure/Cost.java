package seedu.address.model.procedure;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.math.BigDecimal;


public class Cost {

    public static final String MESSAGE_CONSTRAINTS =
            "1. Cost should be rounded to the nearest cent \n"
                    + "2. Cost should be a positive value \n"
                    + "3. Cost should follow the format 'dollars.cents'\n"
                    + "Example: 31.10";

    public static final String VALIDATION_REGEX = "^(?!(?:0|0\\.0|0\\.00)$)[+]?\\d+(\\.\\d|\\.\\d[0-9])?$";
    public final BigDecimal cost;

    /**
     * Constructs a {@code Cost}.
     *
     * @param cost A valid cost.
     */
    public Cost(String cost) {
        requireNonNull(cost);
        checkArgument(isValidCost(cost), MESSAGE_CONSTRAINTS);
        this.cost = new BigDecimal(cost);
    }

    /**
     * Returns true if a given string is a valid cost.
     */
    public static boolean isValidCost(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public BigDecimal value() {
        return this.cost;
    }

    @Override
    public String toString() {
        return this.cost.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.procedure.Cost // instanceof handles nulls
                && this.cost.equals(((seedu.address.model.procedure.Cost) other).cost)); // state check
    }

    @Override
    public int hashCode() {
        return this.cost.hashCode();
    }

}
