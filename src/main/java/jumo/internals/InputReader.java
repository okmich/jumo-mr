/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumo.internals;

import java.io.IOException;

/**
 *
 * @author m.enudi
 * @param <T>
 */
public interface InputReader<T> {

    void beginRead(String inputPath) throws IOException;

    boolean hasNext() throws IOException;

    T next();

}
