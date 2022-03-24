package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Plan;
import seedu.address.model.procedure.Procedure;
import seedu.address.model.tag.Tag;

/**
 * Add a procedure of an existing client in the address book.
 */
public class AddProcCommand extends Command {

    public static final String COMMAND_WORD = "addProc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Procedure to the identified Client "
            + "by the index number used in the displayed client list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_INFORMATION + "INFORMATION "
            + PREFIX_COST + "COST "
            + PREFIX_DATE + "DATE \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INFORMATION + "Install modem "
            + PREFIX_COST + "10.5 "
            + PREFIX_DATE + "20/03/2022 11:30 ";

    public static final String MESSAGE_SUCCESS = "New Procedure added: %1$s"; // Test needed
    public static final String MESSAGE_DUPLICATE_PROCEDURE = "This Procedure is already listed to the Client";

    private final Index index;
    private final Procedure procedure;

    /**
     * @param index of the Client in the filtered client list to add Procedure to
     * @param procedure Procedure to add to the Client
     */
    public AddProcCommand(Index index, Procedure procedure) {
        requireNonNull(index);
        requireNonNull(procedure);

        this.index = index;
        this.procedure = procedure;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToAddProc = lastShownList.get(index.getZeroBased());
        Client clientWithAddedProc = null;

        try {
            clientWithAddedProc = clientWithAddedProcFrom(clientToAddProc);
        } catch (CommandException err) {
            throw new CommandException(MESSAGE_DUPLICATE_PROCEDURE);
        }

        model.setClient(clientToAddProc, clientWithAddedProc);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, procedure));
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToAddProc}
     * edited with {@code procListWithAddedProc}.
     */
    private Client clientWithAddedProcFrom(Client clientToAddProc) throws CommandException {
        assert clientToAddProc != null;

        Name name = clientToAddProc.getName();
        Phone phone = clientToAddProc.getPhone();
        Email email = clientToAddProc.getEmail();
        Plan plan = clientToAddProc.getPlan();
        Address address = clientToAddProc.getAddress();
        Set<Tag> tags = clientToAddProc.getTags();
        List<Procedure> updatedProcedures = new ArrayList<>();

        try {
            updatedProcedures.addAll(procListWithAddedProc(clientToAddProc.getProcedures()));
        } catch (CommandException err) {
            throw new CommandException(MESSAGE_DUPLICATE_PROCEDURE);
        }

        return new Client(name, phone, email, address, plan,
                tags, updatedProcedures);
    }

    private List<Procedure> procListWithAddedProc(List<Procedure> procedureList)
            throws CommandException {

        List<Procedure> updatedProcedureList = new ArrayList<>();
        for (int i = 0; i < procedureList.size(); i++) {
            if (procedureList.get(i).equals(procedure)) {
                throw new CommandException(MESSAGE_DUPLICATE_PROCEDURE);
            }
            updatedProcedureList.add(procedureList.get(i));
        }
        updatedProcedureList.add(procedure);
        return updatedProcedureList;
    }

    /**
     * Returns a list of Procedures, guaranteed to add a properly defined new Procedure.
     * Used internally for testing.
     */
    public List<Procedure> procListWithAddedProperProc(List<Procedure> procedureList) {

        List<Procedure> updatedProcedureList = new ArrayList<>();
        for (int i = 0; i < procedureList.size(); i++) {
            updatedProcedureList.add(procedureList.get(i));
        }
        updatedProcedureList.add(procedure);
        return updatedProcedureList;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddProcCommand)) {
            return false;
        }

        // state check
        AddProcCommand otherCommand = (AddProcCommand) other;
        return index.equals(otherCommand.index)
                && procedure.equals(otherCommand.procedure);
    }
}
