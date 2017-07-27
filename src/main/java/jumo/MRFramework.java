/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumo;

import jumo.internals.OutputWriter;
import jumo.internals.InputReader;
import jumo.internals.Pair;
import jumo.internals.Mapper;
import jumo.internals.Reducer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author m.enudi
 * @param <K1>
 * @param <V1>
 * @param <V2>
 */
public class MRFramework<K1, V1, V2> {

    private String inputFilePath;
    private String outputFilePath;
    /**
     * mapper
     */
    private Mapper<K1, V1> mapper;
    /**
     * reducer
     */
    private Reducer<V1, V2> reducer;
    /**
     * inputReader
     */
    private InputReader<K1> inputReader;
    /**
     * outputWriter
     */
    private OutputWriter<V2> outputWriter;

    private Collection<Pair<V1>> mapCollector;
    private Collection<V2> reduceCollector;

    /**
     *
     * @throws java.io.IOException
     */
    public void run() throws IOException {
        //check configurations
        assertConfig();
        //initialize the map collector
        mapCollector = new ArrayList<>();
        //start reading
        this.inputReader.beginRead(inputFilePath);
        //check directory to write
        this.outputWriter.prepare(outputFilePath);
        //next 
        while (this.inputReader.hasNext()) {
            //map 
            this.mapper.map(this.inputReader.next(), mapCollector);
        }
        //shuffle
        Map<String, List<V1>> pairList = shuffle();
        //initialize the reduce collector
        reduceCollector = new ArrayList<>();
        //reduce
        pairList.entrySet().forEach((entry) -> {
            reducer.reduce(entry.getKey(), (Iterable<V1>) entry.getValue(), this.reduceCollector);
        });
        //output reduced
        outputWriter.write(reduceCollector);
    }

    /**
     *
     * @return
     */
    private Map<String, List<V1>> shuffle() {
        Map<String, List<V1>> coll = new HashMap<>();
        String key;
        for (Pair<V1> pv : mapCollector) {
            key = pv.getKey();
            if (coll.containsKey(key)) {
                coll.get(key).add(pv.getValue());
            } else {
                List<V1> vlist = new ArrayList<>();
                vlist.add(pv.getValue());
                coll.put(key, vlist);
            }
        }
        return coll;
    }

    /**
     *
     * @param mapper
     */
    public void setMapper(Mapper<K1, V1> mapper) {
        this.mapper = mapper;
    }

    /**
     *
     * @param reducer
     */
    public void setReducer(Reducer<V1, V2> reducer) {
        this.reducer = reducer;
    }

    /**
     * @param inputReader the inputReader to set
     */
    public void setInputReader(InputReader<K1> inputReader) {
        this.inputReader = inputReader;
    }

    /**
     * @param outputWriter the outputWriter to set
     */
    public void setOutputWriter(OutputWriter<V2> outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * @param inputFilePath the inputFilePath to set
     */
    protected void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    /**
     * @param outputFilePath the outputFilePath to set
     */
    protected void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    /**
     *
     */
    private void assertConfig() {
        if (this.inputFilePath == null) {
            throw new IllegalArgumentException("Please specify the input file to be read");
        }
        if (this.inputReader == null) {
            throw new IllegalArgumentException("No instance of jumo.InputReader found");
        }
        if (this.mapper == null) {
            throw new IllegalArgumentException("No instance of jumo.Mapper found");
        }
        if (this.outputFilePath == null) {
            throw new IllegalArgumentException("Please specify the path for the output file");
        }
        if (this.outputWriter == null) {
            throw new IllegalArgumentException("No instance of jumo.OutputWriter found");
        }
        if (this.reducer == null) {
            throw new IllegalArgumentException("No instance of jumo.Reducer found");
        }
    }
}
