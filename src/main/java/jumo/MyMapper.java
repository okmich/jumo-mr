/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumo;

import java.util.Collection;
import jumo.internals.Mapper;
import jumo.internals.Pair;

/**
 *
 * @author m.enudi
 */
public class MyMapper implements Mapper<String, String> {

    @Override
    public void map(String k, Collection<Pair<String>> collector) {
        //27729554427,'Network 1','16-Apr-2016','Loan Product 2',1801.00
        try {
            String[] parts = k.split(",");
            String month = parts[2].replace("'", "").substring(3, 6);
            String key = parts[1] + "|" + parts[3] + "|" + month;
            collector.add(new Pair<>(key, parts[4]));
        } catch (Exception e) {
            throw new RuntimeException("Error occured parsing " + k);
        }
    }
}
