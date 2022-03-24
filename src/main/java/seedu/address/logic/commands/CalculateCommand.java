package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.util.List;

import seedu.address.commons.core.dateWithoutTime.DateWithoutTime;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;


/**
 * Get cost from a givenDate
 */
public class CalculateCommand extends Command {

    public static final String COMMAND_WORD = "calculate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Calculates the total cost of all procedures on a given date\n"
            + "Parameters: date (must be a valid date)\n"
            + "Example: " + COMMAND_WORD + " 02/02/2022";

    public static final String MESSAGE_CALCULATE_COST_SUCCESS = "Total Cost: %1$s";

    public static final String MESSAGE_INVALID_DATE_FAILURE = "Invalid date!";

    private final DateWithoutTime targetDate;

    public CalculateCommand(DateWithoutTime date) {
        this.targetDate = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();
        BigDecimal totalCost = new BigDecimal(0);

        for (int i = 0; i < lastShownList.size(); i++) {
            Client currentClient = lastShownList.get(i);
            BigDecimal currentCost = new BigDecimal(currentClient.getCostOnDate(targetDate));
            totalCost = totalCost.add(currentCost);
        }
        String totalCostString = totalCost.toPlainString();

        System.out.println(totalCostString);
        return new CommandResult(String.format(MESSAGE_CALCULATE_COST_SUCCESS, totalCostString));
    }
}
