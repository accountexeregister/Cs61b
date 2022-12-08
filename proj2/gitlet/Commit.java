package gitlet;

// TODO: any imports you need here

import java.util.Calendar;

/**
 * Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author TODO
 */
public class Commit {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /**
     * The message of this Commit.
     */
    private String message;
    private Calendar date;
    private String parent;

    /* TODO: fill in the rest of this class. */
    // This constructor must be called by commits after initial commit and not be called by initial commit
    // Sets the date to the time this constructor was called, that is, when the commit is created
    public Commit(String message) {
        this.message = message;
        date = Calendar.getInstance();
    }

    // Creates the initial commit by creating initial commit message, setting date to epoch time and setting its parent to null
    private Commit() {
        this.message = "initial commit";
        date = Calendar.getInstance();
        date.setTimeInMillis(0);
        parent = null;
    }

    // Factory method to create initial commit by calling private constructor Commit()
    public static Commit createInitCommit() {
        return new Commit();
    }
}
