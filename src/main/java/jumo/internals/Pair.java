/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumo.internals;

/**
 *
 * @author m.enudi
 * @param <V>
 */
public final class Pair<V> {

    private final String key;
    private final V value;

    /**
     *
     * @param key
     * @param value
     */
    public Pair(String key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return the key
     */
    public final String getKey() {
        return key;
    }

    /**
     * @return the value
     */
    public final V getValue() {
        return value;
    }

}
