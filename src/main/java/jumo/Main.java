/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumo;

import java.io.IOException;
import static java.util.Arrays.asList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toMap;
import jumo.internals.OutputWriter;
import jumo.internals.TextFileInputReader;

/**
 *
 * @author m.enudi
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Map<String, String> opts = parseCmdLine(args);

        LOG.info("Configuring application..");
        MRFramework<String, String, String> mrf = new MRFramework();
        mrf.setInputFilePath(getParam(opts, "inputfile"));
        mrf.setInputReader(new TextFileInputReader(Boolean.parseBoolean(getParam(opts, "inputheader"))));
        mrf.setMapper(new MyMapper());
        mrf.setOutputFilePath(getParam(opts, "outputdir"));
        mrf.setOutputWriter(new OutputWriter<String>() {});
        mrf.setReducer(new MyReducer());

        try {
            LOG.info("processing...");
            //
            mrf.run();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        LOG.info("Complete!");
    }

    private static Map<String, String> parseCmdLine(String[] args) {
        return asList(args).stream().map((String t) -> {
            String[] parts = t.split("=");
            return new String[]{parts[0].substring(1), parts[1]};
        }).collect(toMap((String[] t) -> t[0], (String[] t) -> t[1]));
    }

    /**
     *
     * @param map
     * @param key
     * @return
     */
    private static String getParam(Map<String, String> map, String key) {
        if (!map.containsKey(key)) {
            System.err.println("Cannot find argument - " + key);
            System.err.println("\n\nRequired arguments: -inputfile=<inputfile> -outputdir=<outputdir> -inputheader=<inputheader>");
            System.err.println("\tinputfile= file to be processed"
                    + "\n\tinputheader= set as true if the <inputfile> contains the a header row as the first line"
                    + "\n\toutputdir=the directory where the output file Output.csv will be stored");
            System.exit(-1);
        }
        return map.get(key);
    }
}
