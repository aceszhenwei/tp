package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FASTFOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.testutil.TypicalProcedures.REPAIR_ROUTER_PROC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.client.Client;


/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ARTFRIEND = new ClientBuilder().withName("Artfriend")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("art")
            .build();
    public static final Client BOSS = new ClientBuilder().withName("Boss")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("clothes")
            .withProcedures(new ArrayList<>(List.of(REPAIR_ROUTER_PROC)))
            .build();
    public static final Client CARLS = new ClientBuilder().withName("Carl's Jr").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Client DOMINOS = new ClientBuilder().withName("Domino's").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("fastfood").build();
    public static final Client ELLES = new ClientBuilder().withName("Elle's").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz Sewing").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Client GEORGE = new ClientBuilder().withName("George Electronics").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Client HOON = new ClientBuilder().withName("Hoon Noodles").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Client OWNDAYS = new ClientBuilder().withName("Owndays").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client APPLE = new ClientBuilder().withName(VALID_NAME_APPLE).withPhone(VALID_PHONE_APPLE)
            .withEmail(VALID_EMAIL_APPLE).withAddress(VALID_ADDRESS_APPLE).withPlan("EXPRESS 200MBps")
            .withTags(VALID_TAG_FASTFOOD).build();
    public static final Client BURGER = new ClientBuilder().withName(VALID_NAME_BURGER).withPhone(VALID_PHONE_BURGER)
            .withEmail(VALID_EMAIL_BURGER).withAddress(VALID_ADDRESS_BURGER).withPlan("NORMAL 100MBps")
            .withTags(VALID_TAG_TECH, VALID_TAG_FASTFOOD).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client : getTypicalClients()) {
            ab.addClient(client);
        }
        return ab;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ARTFRIEND, BOSS, CARLS, DOMINOS, ELLES, FIONA, GEORGE));
    }
}
