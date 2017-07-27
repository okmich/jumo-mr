/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumo.internals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;

/**
 *
 * @author m.enudi
 */
public abstract class OutputWriter<T> {

    private PrintStream ps;

    public void prepare(String outPath) throws IOException {
        File file = new File(outPath);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("outputdir specified is not a directory");
        }
        ps = new PrintStream(new FileOutputStream(new File(file, "Output.csv")), false);
    }

    public void write(Collection<T> itr) throws IOException {
        try {
            itr.forEach((t) -> {
                ps.println(t);
            });
            ps.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
    }
}
