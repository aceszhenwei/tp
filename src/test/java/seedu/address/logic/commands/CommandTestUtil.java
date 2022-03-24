package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.procedure.Procedure;
import seedu.address.testutil.EditClientDescriptorBuilder;
import seedu.address.testutil.ProcedureBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_NAME_APPLE = "Apple";
    public static final String VALID_NAME_BURGER = "Burger King";
    public static final String VALID_PHONE_APPLE = "11111111";
    public static final String VALID_PHONE_BURGER = "22222222";
    public static final String VALID_EMAIL_APPLE = "apple@example.com";
    public static final String VALID_EMAIL_BURGER = "burger@example.com";
    public static final String VALID_ADDRESS_APPLE = "Block 312, Apple Street 1";
    public static final String VALID_ADDRESS_BURGER = "Block 123, Burger Street 3";
    public static final String VALID_PLAN_APPLE = "EXPRESS 200MBps";
    public static final String VALID_PLAN_BURGER = "NORMAL 100MBps";
    public static final String VALID_TAG_TECH = "technology";
    public static final String VALID_TAG_FASTFOOD = "fastfood";

    public static final String NAME_DESC_APPLE = " " + PREFIX_NAME + VALID_NAME_APPLE;
    public static final String NAME_DESC_BURGER = " " + PREFIX_NAME + VALID_NAME_BURGER;
    public static final String PHONE_DESC_APPLE = " " + PREFIX_PHONE + VALID_PHONE_APPLE;
    public static final String PHONE_DESC_BURGER = " " + PREFIX_PHONE + VALID_PHONE_BURGER;
    public static final String EMAIL_DESC_APPLE = " " + PREFIX_EMAIL + VALID_EMAIL_APPLE;
    public static final String EMAIL_DESC_BURGER = " " + PREFIX_EMAIL + VALID_EMAIL_BURGER;
    public static final String ADDRESS_DESC_APPLE = " " + PREFIX_ADDRESS + VALID_ADDRESS_APPLE;
    public static final String ADDRESS_DESC_BURGER = " " + PREFIX_ADDRESS + VALID_ADDRESS_BURGER;
    public static final String PLAN_DESC_APPLE = " " + PREFIX_PLAN + VALID_PLAN_APPLE;
    public static final String PLAN_DESC_BURGER = " " + PREFIX_PLAN + VALID_PLAN_BURGER;
    public static final String TAG_DESC_FASTFOOD = " " + PREFIX_TAG + VALID_TAG_FASTFOOD;
    public static final String TAG_DESC_TECH = " " + PREFIX_TAG + VALID_TAG_TECH;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME; // empty string not allowed for names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "burger!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_PLAN_DESC = " " + PREFIX_PLAN; // empty string not allowed for plans
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "tech*"; // '*' not allowed in tags

    public static final EditCommand.EditClientDescriptor DESC_APPLE;
    public static final EditCommand.EditClientDescriptor DESC_BURGER;

    public static final String VALID_DATE_REPAIR = "21/04/2022 11:50";
    public static final String VALID_DATE_REPLACE = "18/03/2022 14:55";
    public static final String VALID_INFO_REPAIR = "Repair Router";
    public static final String VALID_INFO_REPLACE = "Replace Wires";
    public static final String VALID_COST_REPAIR = "13.50";
    public static final String VALID_COST_REPLACE = "5.05";
    public static final String VALID_HASCOMPLETED_TRUE = "true";
    public static final String VALID_HASCOMPLETED_FALSE = "false";

    public static final String INFO_DESC_REPAIR = " " + PREFIX_INFORMATION + VALID_INFO_REPAIR;
    public static final String INFO_DESC_REPLACE = " " + PREFIX_INFORMATION + VALID_INFO_REPLACE;
    public static final String COST_DESC_REPAIR = " " + PREFIX_COST + VALID_COST_REPAIR;
    public static final String COST_DESC_REPLACE = " " + PREFIX_COST + VALID_COST_REPLACE;
    public static final String DATE_DESC_REPAIR = " " + PREFIX_DATE + VALID_DATE_REPAIR;
    public static final String DATE_DESC_REPLACE = " " + PREFIX_DATE + VALID_DATE_REPLACE;

    // empty string not allowed for info
    public static final String INVALID_INFO_DESC = " " + PREFIX_INFORMATION;
    // hyphen not allowed in dates
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "21-04-2022 11:50";
    // costs support two decimal places max
    public static final String INVALID_COST_DESC = " " + PREFIX_COST + "13.555";

    public static final Procedure DESC_REPAIR;
    public static final Procedure DESC_REPLACE;

    static {
        DESC_APPLE = new EditClientDescriptorBuilder().withName(VALID_NAME_APPLE)
                .withPhone(VALID_PHONE_APPLE).withEmail(VALID_EMAIL_APPLE).withAddress(VALID_ADDRESS_APPLE)
                .withTags(VALID_TAG_FASTFOOD).build();
        DESC_BURGER = new EditClientDescriptorBuilder().withName(VALID_NAME_BURGER)
                .withPhone(VALID_PHONE_BURGER).withEmail(VALID_EMAIL_BURGER).withAddress(VALID_ADDRESS_BURGER)
                .withTags(VALID_TAG_TECH, VALID_TAG_FASTFOOD).build();
        DESC_REPAIR = new ProcedureBuilder().withInfo(VALID_INFO_REPAIR).withDate(VALID_DATE_REPAIR)
                .withCost(VALID_COST_REPAIR).withCompletion(VALID_HASCOMPLETED_TRUE).build();
        DESC_REPLACE = new ProcedureBuilder().withInfo(VALID_INFO_REPLACE).withDate(VALID_DATE_REPLACE)
                .withCost(VALID_COST_REPLACE).withCompletion(VALID_HASCOMPLETED_FALSE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            System.out.println(expectedCommandResult.getFeedbackToUser());
            System.out.println(result.getFeedbackToUser());
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered client list and selected client in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Client> expectedFilteredList = new ArrayList<>(actualModel.getFilteredClientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredClientList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }

}
