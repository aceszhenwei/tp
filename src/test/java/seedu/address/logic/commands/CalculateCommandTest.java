package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class CalculateCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
}
