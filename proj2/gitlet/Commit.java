package gitlet;

// TODO: any imports you need here

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author TODO
 */
public class Commit implements Serializable {
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
    private Date date;
    private String parent;

    /* TODO: fill in the rest of this class. */
    // This constructor must be called by commits after initial commit and not be called by initial commit
    // Sets the date to the time this constructor was called, that is, when the commit is created
    public Commit(String message) {
        this.message = message;
        // date = Calendar.getInstance();
    }

    // Creates the initial commit by creating initial commit message, setting date to epoch time and setting its parent to null
    private Commit() {
        this.message = "initial commit";
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        cal.setTimeInMillis(0);
        date = cal.getTime();
        parent = null;
    }

    // Factory method to create initial commit by calling private constructor Commit()
    public static Commit createInitCommit() {
        return new Commit();
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy Z");
        return sdf.format(date);
    }

    public String getMessage() {
        return message;
    }

    public String getParent() {
        return this.parent;
    }

    public String toSHA1() {
        return Utils.sha1(this.toString());
    }
}
