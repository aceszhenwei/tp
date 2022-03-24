package seedu.address.model.procedure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class CostTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cost(null));
    }

    @Test
    public void constructor_invalidCost_throwsIllegalArgumentException() {
        String invalidCost = "";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidCost));
    }

    @Test
    public void isValidCost() {
        // null Cost
        assertThrows(NullPointerException.class, () -> Cost.isValidCost(null));

        // invalid Cost
        assertFalse(Cost.isValidCost("")); // empty string
        assertFalse(Cost.isValidCost(" ")); // spaces only
        assertFalse(Cost.isValidCost("^")); // only non-alphanumeric characters
        assertFalse(Cost.isValidCost("peter*")); // contains non-alphanumeric characters
        assertFalse(Cost.isValidCost("peter jack")); // alphabets only
        assertFalse(Cost.isValidCost("peter the 2nd")); // alphanumeric characters
        assertFalse(Cost.isValidCost("Capital Tan")); // with capital letters
        assertFalse(Cost.isValidCost("12.111")); // 3 decimal points
        assertFalse(Cost.isValidCost("-12")); // negative value

        // valid Costs
        assertTrue(Cost.isValidCost("12345")); // numbers only
        assertTrue(Cost.isValidCost("12.1")); // numbers with 1 decimal point
        assertTrue(Cost.isValidCost("12.11")); // numbers with 2 decimal point
    }

    @Test
    public void isValidCostAsFloat() {
        Cost test1 = new Cost("13.11");
        Cost test2 = new Cost("13.1");
        Cost test3 = new Cost("13");

        assertTrue(test1.value().compareTo(new BigDecimal("13.11")) == 0);
        assertTrue(test2.value().compareTo(new BigDecimal("13.1")) == 0);
        assertTrue(test3.value().compareTo(new BigDecimal("13")) == 0);
    }
}
