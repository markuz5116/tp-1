package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Assignment;

public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assignment to the module. "
            + "Parameters: "
            + PREFIX_MODULE + "TITLE "
            + PREFIX_ASSIGNMENT + "DESCRIPTION "
            + PREFIX_DEADLINE + "DEADLINE"
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_ASSIGNMENT + "TP v1.2 "
            + PREFIX_DEADLINE + "28/3/2021 2359";

    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in the module";

    private final Assignment toAdd;

    /**
     * Creates an AddPersonCommand to add the specified {@code Person}
     */
    public AddAssignmentCommand(Assignment assignment) {
        requireNonNull(assignment);
        toAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAssignment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.addAssignment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAssignmentCommand // instanceof handles nulls
                && toAdd.equals(((AddAssignmentCommand) other).toAdd));
    }
}