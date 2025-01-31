package cli;

public class InputReader {
    public static ParsedCommand readCommand(String input) {
        String[] parts = input.split(" ");
        String name = parts[0];
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, parts.length - 1);
        return new ParsedCommand(name, args);
    }
}
