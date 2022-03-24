package seedu.address.model.procedure;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a specific procedure in a clients
 * Guarantees: immutable; is valid as declared in {@link #isValidProcedure(String)}
 */
public class Procedure {
    private final Information information;
    private final Date date;
    private final Cost cost;
    private final Completion hasCompleted;

    /**
     * Every field must be present and not null.
     */
    public Procedure(Information information, Date date, Cost cost, Completion hasCompleted) {
        requireAllNonNull(information, date, cost);
        this.information = information;
        this.date = date;
        this.cost = cost;
        this.hasCompleted = hasCompleted;
    }

    public Information getInfo() {
        return this.information;
    }

    public Date getDate() {
        return this.date;
    }

    public Cost getCost() {
        return this.cost;
    }

    public Completion getHasCompleted() {
        return this.hasCompleted;
    }

    /**
     * Returns true if both Procedures have the same fields.
     * This defines a stronger notion of equality between two Procedures.
     */

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Procedure)) {
            return false;
        }

        Procedure otherPerson = (Procedure) other;
        return otherPerson.getInfo().equals(getInfo())
                && otherPerson.getDate().equals(getDate())
                && otherPerson.getCost().equals(getCost())
                && otherPerson.getHasCompleted().equals(getHasCompleted());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(information, date, cost, hasCompleted);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Information: ")
                .append(getInfo())
                .append("; Date: ")
                .append(getDate())
                .append("; Cost: ")
                .append(getCost())
                .append("; Completed: ")
                .append(getHasCompleted());

        return builder.toString();
    }

}

