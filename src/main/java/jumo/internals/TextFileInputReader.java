/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumo.internals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author m.enudi
 */
public class TextFileInputReader implements InputReader<String> {

    private String value;
    private BufferedReader bufferedReader;
    private final boolean headerFlag;

    public TextFileInputReader(boolean header) {
        this.headerFlag = header;
    }

    @Override
    public void beginRead(String inputPath) throws IOException {
        File file = new File(inputPath);
        if (!file.exists()) {
            throw new IllegalArgumentException("inputfile specified does not exist");
        }
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        if (headerFlag) {
            bufferedReader.readLine(); //read out the first line
        }
    }

    @Override
    public boolean hasNext() throws IOException {
        this.value = bufferedReader.readLine();
        return this.value != null;
    }

    @Override
    public String next() {
        return value;
    }
}
