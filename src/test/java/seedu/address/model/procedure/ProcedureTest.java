package seedu.address.model.procedure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HASCOMPLETED_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_REPLACE;
import static seedu.address.testutil.TypicalProcedures.REPAIR_ROUTER_PROC;
import static seedu.address.testutil.TypicalProcedures.REPLACE_WIRES_PROC;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ProcedureBuilder;

class ProcedureTest {
    @Test
    public void equals() {
        // same values -> returns true
        Procedure repairCopy = new ProcedureBuilder(REPAIR_ROUTER_PROC).build();
        assertTrue(REPAIR_ROUTER_PROC.equals(repairCopy));

        // same object -> returns true
        assertTrue(REPAIR_ROUTER_PROC.equals(REPAIR_ROUTER_PROC));

        // null -> returns false
        assertFalse(REPAIR_ROUTER_PROC.equals(null));

        // different type -> returns false
        assertFalse(REPAIR_ROUTER_PROC.equals(5));

        // different procedure -> returns false
        assertFalse(REPAIR_ROUTER_PROC.equals(REPLACE_WIRES_PROC));

        // different information -> returns false
        Procedure editedRepair = new ProcedureBuilder(REPAIR_ROUTER_PROC).withInfo(VALID_INFO_REPLACE).build();
        assertFalse(REPAIR_ROUTER_PROC.equals(editedRepair));

        // different cost -> returns false
        editedRepair = new ProcedureBuilder(REPAIR_ROUTER_PROC).withCost(VALID_COST_REPLACE).build();
        assertFalse(REPAIR_ROUTER_PROC.equals(editedRepair));

        // different date -> returns false
        editedRepair = new ProcedureBuilder(REPAIR_ROUTER_PROC).withDate(VALID_DATE_REPAIR).build();
        assertFalse(REPAIR_ROUTER_PROC.equals(editedRepair));

        // different hasCompleted -> returns false
        editedRepair = new ProcedureBuilder(REPAIR_ROUTER_PROC).withCompletion(VALID_HASCOMPLETED_FALSE)
                .build();
        assertFalse(REPAIR_ROUTER_PROC.equals(editedRepair));
    }

}
