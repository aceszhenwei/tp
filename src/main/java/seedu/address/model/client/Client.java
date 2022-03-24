package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.dateWithoutTime.DateWithoutTime;
import seedu.address.model.procedure.Date;
import seedu.address.model.procedure.Procedure;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Plan plan;
    private final Set<Tag> tags = new HashSet<>();
    private final List<Procedure> procedures = new ArrayList<Procedure>();

    /**
     * Every field, less Procedures, must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Plan plan, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.plan = plan;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Plan plan,
                  Set<Tag> tags, List<Procedure> procedures) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.plan = plan;
        this.tags.addAll(tags);
        this.procedures.addAll(procedures);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Plan getPlan() {
        return plan;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable procedure set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Procedure> getProcedures() {
        return Collections.unmodifiableList(procedures);
    }

    public List<Procedure> getProcsOnDate(DateWithoutTime date) {
        List<Procedure> procsOnDate = new ArrayList<Procedure> ();
        Date startTime = new Date(date.toString() + Date.START_OF_DAY);
        Date endTime = new Date(date.toString() + Date.END_OF_DAY);
        for (int i = 0; i < this.procedures.size(); i++) {
            Procedure currentProc = this.procedures.get(i);
            Date currentDate = currentProc.getDate();
            if (currentDate.compareTo(startTime) >= 0
                    && currentDate.compareTo(endTime) <= 0) {
                procsOnDate.add(currentProc);
            }
        }
        return procsOnDate;
    }

    public float getCostOnDate(DateWithoutTime date) {
        List<Procedure> procsOnDate = getProcsOnDate(date);
        BigDecimal totalCost = new BigDecimal(0);
        for (int i = 0; i < procsOnDate.size(); i++) {
            Procedure currentProc = this.procedures.get(i);
            BigDecimal currentCost = currentProc.getCost().value();
            totalCost = totalCost.add(currentCost);;
        }
        return totalCost.floatValue();
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getAddress().equals(getAddress());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail())
                && otherClient.getAddress().equals(getAddress())
                && otherClient.getPlan().equals(getPlan())
                && otherClient.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, plan, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Plan: ")
                .append(getPlan());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        List<Procedure> procedures = getProcedures();
        if (!procedures.isEmpty()) {
            builder.append("; Procedures: ");
            procedures.forEach(builder::append);
        }
        return builder.toString();
    }

}
