package org.buddycode.jgit.exceptions;

/**
 * Created by samith on 6/25/15.
 */
public class RepositoryMgtException extends Exception {

    private static final long serialVersionUID = 6072661890886300392L;

    public RepositoryMgtException() {
    }

    public RepositoryMgtException(String s) {
        super(s);
    }

    public RepositoryMgtException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RepositoryMgtException(Throwable throwable) {
        super(throwable);
    }
}
