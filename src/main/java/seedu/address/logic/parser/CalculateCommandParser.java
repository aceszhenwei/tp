package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.dateWithoutTime.DateWithoutTime;
import seedu.address.logic.commands.CalculateCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class CalculateCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the CalculateCommand
     * and returns a CalculateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CalculateCommand parse(String args) throws ParseException {
        try {
            DateWithoutTime date = ParserUtil.parseDateWithoutTime(args);
            System.out.println(date);
            return new CalculateCommand(date);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DateWithoutTime.MESSAGE_CONSTRAINTS), pe);
        }
    }
}
