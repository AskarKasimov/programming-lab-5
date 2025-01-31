package cli;

import exception.InvalidCommandException;

import java.util.Arrays;

public class CommandParser {
    public ParsedCommand parse(String[] args) throws InvalidCommandException {
        if (args.length == 0) {
            throw new InvalidCommandException("Команда не указана");
        }
        String commandName = args[0];
        String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
        return new ParsedCommand(commandName, commandArgs);
    }
}
