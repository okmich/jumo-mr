/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumo.internals;

import java.util.Collection;

/**
 *
 * @author m.enudi
 * @param <T>
 * @param <V>
 */
public interface Reducer<T, V> {

    void reduce(String key, Iterable<T> itr, Collection<V> collection);
}
