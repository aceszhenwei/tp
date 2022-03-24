package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HASCOMPLETED_TRUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.procedure.Procedure;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.ProcedureBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddProcCommand.
 */
public class AddProcCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    // new Model without any Clients
    private Model newModel = new ModelManager();
    private Procedure toBeAddedProcedure = new ProcedureBuilder().build();

    @Test
    public void execute_properIndexWithProperProcedure_success() {
        // guaranteed to add a non-duplicated Procedure since starting from a new Client
        Client newClient = new ClientBuilder().build();
        newModel.addClient(newClient);

        AddProcCommand addProcCommand = new AddProcCommand(INDEX_FIRST_CLIENT, toBeAddedProcedure);

        String expectedMessage = String.format(AddProcCommand.MESSAGE_SUCCESS, toBeAddedProcedure);

        Model expectedModel = new ModelManager(new AddressBook(newModel.getAddressBook()), new UserPrefs());

        Client clientToAddProc = newModel.getFilteredClientList().get(0);
        Client clientWithAddedProcedure = new ClientBuilder(clientToAddProc)
                .withProcedures(addProcCommand.procListWithAddedProperProc(clientToAddProc.getProcedures())).build();

        expectedModel.setClient(clientToAddProc, clientWithAddedProcedure);

        assertCommandSuccess(addProcCommand, newModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullIndexWithProperProcedure_failure() {
        assertThrows(NullPointerException.class, () -> new AddProcCommand(null, toBeAddedProcedure));
    }

    @Test
    public void execute_improperIndexWithProperProcedure_failure() {
        // out of bounds index
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        AddProcCommand addProcCommand = new AddProcCommand(outOfBoundIndex , toBeAddedProcedure);
        assertCommandFailure(addProcCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_properIndexWithNullProcedure_failure() {
        assertThrows(NullPointerException.class, () -> new AddProcCommand(INDEX_FIRST_CLIENT, null));
    }

    @Test
    public void execute_properIndexWithDuplicateProcedure_failure() {
        Client newClient = new ClientBuilder().build();
        newModel.addClient(newClient);

        AddProcCommand addProcCommand = new AddProcCommand(INDEX_FIRST_CLIENT, toBeAddedProcedure);

        Client clientWithAddedProcedure = new ClientBuilder(newClient)
                .withProcedures(addProcCommand.procListWithAddedProperProc(newClient.getProcedures())).build();

        newModel.setClient(newClient, clientWithAddedProcedure);

        // executing again, guaranteed to be a duplicate Procedure
        assertCommandFailure(addProcCommand, newModel, AddProcCommand.MESSAGE_DUPLICATE_PROCEDURE);
    }

    @Test
    public void equals() {
        final AddProcCommand standardCommand = new AddProcCommand(INDEX_FIRST_CLIENT, DESC_REPAIR);

        // same values -> returns true
        Procedure copyProcedure = new ProcedureBuilder().withInfo(VALID_INFO_REPAIR).withDate(VALID_DATE_REPAIR)
                .withCost(VALID_COST_REPAIR).withCompletion(VALID_HASCOMPLETED_TRUE).build();
        AddProcCommand commandWithSameValues = new AddProcCommand(INDEX_FIRST_CLIENT, copyProcedure);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddProcCommand(INDEX_SECOND_CLIENT, DESC_REPAIR)));

        // different Procedure -> returns false
        assertFalse(standardCommand.equals(new AddProcCommand(INDEX_FIRST_CLIENT, DESC_REPLACE)));
    }
}
