package gitlet;

// TODO: any imports you need here

import java.io.File;
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
    private String nextStagedCommit;
    // Maps file name to its SHA1
    private Map<String, String> fileToSHA1 = new HashMap<>();
    // List of files stored by the commit
    private List<String> fileList = new ArrayList<>();

    /* TODO: fill in the rest of this class. */

    public void setNext(Commit nextCommitStaged) {
        this.nextStagedCommit = nextCommitStaged.toStatusSHA1();
    }

    public String getNextStagedCommitString() {
        return nextStagedCommit;
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

    public Commit getNextStagedCommit() {
        return Repository.getCommit(nextStagedCommit, Repository.STAGE);
    }

    public boolean isStageable(String fileName, String fileToAddSHA1) {
        return fileToSHA1.get(fileName) == null || !(fileToSHA1.get(fileName).equals(fileToAddSHA1));
    }

    public void stageFile(String fileName) {
        Commit nextStagedCommitObj = getNextStagedCommit();
        File fileToAdd = Utils.join(Repository.CWD, fileName);
        String fileToAddSHA1 = Utils.sha1(Utils.readContentsAsString(fileToAdd));
        if (isStageable(fileName, fileToAddSHA1)) {
            if (fileToSHA1.get(fileName) == null) {
                nextStagedCommitObj.fileList.add(fileName);
            }
            nextStagedCommitObj.fileToSHA1.put(fileName, fileToAddSHA1);
        } else {
            String originalSha1OfFile = fileToSHA1.get(fileName);
            if (originalSha1OfFile == null || !(nextStagedCommitObj.fileToSHA1.get(fileName).equals(originalSha1OfFile))) {
                nextStagedCommitObj.fileToSHA1.put(fileName, originalSha1OfFile);
            }
        }
    }

    // Sets the stage for the commit which is in staging mode (Must only be called for commit about to be commited next
    public void setStage(Commit parent) {
        for (String fileName : parent.fileList) {
            fileList.add(fileName);
            fileToSHA1.put(fileName, parent.fileToSHA1.get(fileName));
        }
    }

    public List<String> getFileList() {
        return fileList;
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
        return Utils.sha1(this.date.toString() + this.message + this.fileList + this.fileToSHA1 + this.parent);
    }

    public String toStatusSHA1() {
        return parent;
    }
}
