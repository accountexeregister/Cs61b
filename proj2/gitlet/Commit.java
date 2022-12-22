package gitlet;

// TODO: any imports you need here

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private String next;
    // Maps file name to its SHA1
    private Map<String, String> fileToSHA1 = new HashMap<>();

    /* TODO: fill in the rest of this class. */

    public void setNext(Commit next) {
        this.next = next.toSHA1();
    }

    public void addFilesFromStage(Commit parentCommit, Stage stage) {
        for (String commitFileName : parentCommit.getFileNames()) {
            String stageAdditionFileSHA1 = stage.getStagedForAdditionFileSHA1(commitFileName);
            Boolean stageRemovalFileSHA1 = stage.getStagedForRemovalFileSHA1(commitFileName);
            if (stageAdditionFileSHA1 != null) {
                fileToSHA1.put(commitFileName, stageAdditionFileSHA1);
                stage.unstage(commitFileName);
            } else if (stageRemovalFileSHA1 != null && stageRemovalFileSHA1) {
                fileToSHA1.remove(commitFileName);
            } else {
                fileToSHA1.put(commitFileName, parentCommit.fileToSHA1.get(commitFileName));
            }
        }

        for (String fileName : stage.getStageFileNames()) {
            fileToSHA1.put(fileName, stage.getStagedForAdditionFileSHA1(fileName));
        }
        saveCommit();
    }

    public void saveCommit() {
        Repository.writeCommit(this, this.toSHA1(), Repository.OBJECTS);
    }

    public boolean isTracked(String fileName) {
        return fileToSHA1.get(fileName) != null;
    }

    public void setParent(Commit parent) {
        this.parent = parent.toSHA1();
    }

    public Commit() {
        parent = null;
    }

    public void addCommitDetail(String message) {
        this.message = message;
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        date = cal.getTime();
    }

    public boolean isStageable(Stage stage, String fileName, String fileToAddSHA1) {
        return stage.getStagedForAdditionFileSHA1(fileName) == null || !(stage.getStagedForAdditionFileSHA1(fileName).equals(fileToAddSHA1));
    }

    public boolean fileExists(String fileName) {
        return getFileSHA1(fileName) != null;
    }

    public String getFileSHA1(String fileName) {
        return fileToSHA1.get(fileName);
    }

    // Factory method to create initial commit by calling private constructor Commit()
    public static Commit createInitCommit() {
        Commit commit = new Commit();
        commit.message = "initial commit";
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        cal.setTimeInMillis(0);
        commit.date = cal.getTime();
        return commit;
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
        return Utils.sha1(this.date.toString() + this.message + this.fileToSHA1 + this.parent);
    }
    
    public Set<String> getFileNames() {
        return fileToSHA1.keySet();
    }
}
