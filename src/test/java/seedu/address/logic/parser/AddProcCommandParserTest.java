package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COST_DESC_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.COST_DESC_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.INFO_DESC_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.INFO_DESC_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COST_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INFO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_REPAIR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddProcCommand;
import seedu.address.model.procedure.Cost;
import seedu.address.model.procedure.Date;
import seedu.address.model.procedure.Information;
import seedu.address.model.procedure.Procedure;
import seedu.address.testutil.ProcedureBuilder;

public class AddProcCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProcCommand.MESSAGE_USAGE);

    private AddProcCommandParser parser = new AddProcCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, INFO_DESC_REPAIR, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + INFO_DESC_REPAIR, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + INFO_DESC_REPAIR, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 z/string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_INFO_DESC + COST_DESC_REPAIR
                + DATE_DESC_REPAIR, Information.MESSAGE_CONSTRAINTS); // invalid info
        assertParseFailure(parser, "1" + INFO_DESC_REPAIR + COST_DESC_REPAIR
                + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INFO_DESC_REPAIR + INVALID_COST_DESC
                + DATE_DESC_REPAIR, Cost.MESSAGE_CONSTRAINTS); // invalid cost

        // valid info followed by invalid info. The test case for invalid info followed by valid info
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + INFO_DESC_REPAIR + INVALID_INFO_DESC
                + DATE_DESC_REPAIR + COST_DESC_REPAIR, Information.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_INFO_DESC + INVALID_DATE_DESC
                        + COST_DESC_REPAIR, Information.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + INFO_DESC_REPAIR + DATE_DESC_REPAIR
                + COST_DESC_REPAIR;

        Procedure procedureToBeAdded = new ProcedureBuilder().withInfo(VALID_INFO_REPAIR).withDate(VALID_DATE_REPAIR)
                .withCost(VALID_COST_REPAIR).build();

        AddProcCommand expectedCommand = new AddProcCommand(targetIndex, procedureToBeAdded);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + INFO_DESC_REPLACE + INFO_DESC_REPAIR
                + DATE_DESC_REPLACE + DATE_DESC_REPAIR + COST_DESC_REPLACE + COST_DESC_REPAIR;

        Procedure procedureToBeAdded = new ProcedureBuilder().withInfo(VALID_INFO_REPAIR).withDate(VALID_DATE_REPAIR)
                .withCost(VALID_COST_REPAIR).build();

        AddProcCommand expectedCommand = new AddProcCommand(targetIndex, procedureToBeAdded);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + INVALID_INFO_DESC + INFO_DESC_REPAIR + INVALID_DATE_DESC
                + DATE_DESC_REPAIR + INVALID_COST_DESC + COST_DESC_REPAIR;

        Procedure procedureToBeAdded = new ProcedureBuilder().withInfo(VALID_INFO_REPAIR).withDate(VALID_DATE_REPAIR)
                .withCost(VALID_COST_REPAIR).build();

        AddProcCommand expectedCommand = new AddProcCommand(targetIndex, procedureToBeAdded);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
