package gitlet;

/**
 * Driver class for Gitlet, a subset of the Git version-control system.
 *
 * @author TODO
 */
public class Main {

    /**
     * Usage: java gitlet.Main ARGS, where ARGS contains
     * <COMMAND> <OPERAND1> <OPERAND2> ...
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        String firstArg = args[0];
        switch (firstArg) {
            case "init":
                // TODO: handle the `init` command
                Repository.initGitlet();
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                if (args.length < 2) {
                    return;
                }
                String fileName = args[1];
                Repository.add(fileName);
                break;
            // TODO: FILL THE REST IN
            case "commit":
                if (args.length < 2) {
                    return;
                }
                String message = args[1];
                Repository.commit(message);
                break;
            case "log":
                Repository.log();
                break;
            case "delete":
                Repository.deleteFiles();
                break;
        }
    }
}
