package FindLauncher;

import Find.*;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;

public class FindLauncher {
    @Option(name = "-r", usage = "search in a subdirectory.")
    private boolean flagR;

    @Option(name = "-d", metaVar = "directory", usage = "search in a directory")
    private String path;

    @Argument(usage = "input File Name")
    private String file;

    public static void main(String[] args){
        new FindLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        }
        catch (CmdLineException e) {
            System.err.println("find [-r] [-d directory] filename.txt");
            parser.printUsage(System.err);
            return;
        }
        if (path == null) path = new File(".").getAbsolutePath();
        Find find = new Find(flagR, path, file);

        try {
            find.find();
        } catch (IOException e) {
            System.err.println("File error");
            e.printStackTrace();
        }
    }
}
