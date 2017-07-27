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
 * @param <K>
 * @param <V>
 */
public interface Mapper<K, V> {

    void map(K k, Collection<Pair<V>> collection);
}
