package org.buddycode.jgit.main;


import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.buddycode.jgit.agents.GitAgent;
import org.buddycode.jgit.agents.JGitAgent;
import org.buddycode.jgit.config.Configs;
import org.buddycode.jgit.exceptions.RepositoryMgtException;

import java.io.Console;
import java.io.File;
import java.io.IOException;

/**
 * Created by samith on 7/25/14.
 */
public class CloneAndCommit {

    public static final String SAMPLE_FILE_NAME = "sample_file.txt";
    public static final String REMOTE_REPO_URL = "remoteRepoUrl";
    public static final String WORKING_DIRECTORY = "workingDirectory";
    static final Log log = LogFactory.getLog(CloneAndCommit.class);
    public static final String COMMIT_MESSAGE = "commitMessage";

    public static void main(String[] args) {
        String remoteRepoUrl = Configs.getProerty(REMOTE_REPO_URL);
        String directory = Configs.getProerty(WORKING_DIRECTORY);
        String CommitMsg = Configs.getProerty(COMMIT_MESSAGE);
        GitAgent client = new JGitAgent();

        Console console = System.console();
        console.printf("Please enter your username: ");
        String username = console.readLine();
        console.printf("Please enter your password: ");
        char[] passwordChars = console.readPassword();
        String password = new String(passwordChars);

        client.setCredentials(username, password);
        File cloneToDirectory = new File(directory);
        if(cloneToDirectory.exists()) {
            try {
                FileUtils.deleteDirectory(cloneToDirectory);
            } catch (IOException e) {
                log.error("Error while deleting directory: "+directory,e);
                System.exit(1);
            }
        }

        try {
            client.cloneRepo(remoteRepoUrl, false, cloneToDirectory);
            File sampleFile = new File(cloneToDirectory.getAbsolutePath()+File.separator+
                                       SAMPLE_FILE_NAME);
            if (sampleFile.createNewFile()){
                log.info("Sample  File is created!. File: " + cloneToDirectory.getAbsolutePath()+File.separator+
                         SAMPLE_FILE_NAME);
            }else{
                log.info("File already exists. File: " + cloneToDirectory.getAbsolutePath()+File.separator+
                         SAMPLE_FILE_NAME);
            }
            client.add(cloneToDirectory,false,cloneToDirectory);
            client.commit(CommitMsg, true, cloneToDirectory);
        } catch (RepositoryMgtException e) {
            log.error("Error while doing git operations.",e);
        } catch (IOException e) {
            log.error("Error while creating sample file",e);
        }
    }

}
