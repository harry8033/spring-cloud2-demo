package com.pf.code.db;

import com.pf.code.entity.DataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Ru He
 * Date: Created in 2019/1/31.
 * Description:
 */
public class DbModelFactory {

    private static Map<Integer, DbModelProvider> providers = new HashMap<>();

    private DbModelFactory(){

    }

    public static DbModelProvider getProvider(DataSource ds){
        if(providers.containsKey(ds.getId())){
            return providers.get(ds.getId());
        }
        DbModelProvider p = new DbModelProvider(ds);
        providers.put(ds.getId(), p);
        return p;
    }
}
