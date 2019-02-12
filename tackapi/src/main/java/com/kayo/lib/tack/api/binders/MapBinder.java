package com.kayo.lib.tack.api.binders;


import java.util.HashMap;
import java.util.Map;

/**
 * KayoSun
 * 2019-01-23
 * 23:12
 * ----------
 */
public class MapBinder<V extends Object> {
    private Map<String,V> mMap;

    public MapBinder(Map<String, V> mMap) {
        this.mMap = mMap;
        this.mMap = new HashMap<>();
    }
}
