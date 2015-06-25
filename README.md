# JgitTest
Test JGIT Lib

1. Update src/main/resources/config.properties file, providing your test git repository url and temp directory to checkout the repository
2. Run following command to run as maven project.

`mvn exec:java -Dexec.mainClass="org.buddycode.jgit.main.CloneAndCommit" -Dexec.cleanupDaemonThreads="false"`
