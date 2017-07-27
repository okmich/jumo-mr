/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumo;

import java.math.BigDecimal;
import java.util.Collection;
import jumo.internals.Reducer;

/**
 *
 * @author m.enudi
 */
public class MyReducer implements Reducer<String, String> {

    @Override
    public void reduce(String key, Iterable<String> itr, Collection<String> collector) {
        BigDecimal total = BigDecimal.ZERO;
        int count = 0;

        for (String s : itr) {
            count++;
            total = total.add(new BigDecimal(s));
        }

        String[] parts = key.split("\\|");
        String resultFormat = String.format("%s,%s,%s,%s,%d", parts[0], parts[1], parts[2], total.toString(), count);
        collector.add(resultFormat);
    }

}
