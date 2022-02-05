package org.lili.cache.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class MainClass {
    public static void main(String[] args) {
//        HazelcastInstance hazelcast = Hazelcast.newHazelcastInstance(new Config());

        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap map = hz.getMap("my-distributed-map");
        map.put("1", "John");
        map.put("2", "Mary");
        map.put("3", "Jane");


        //hz.shutdown();
    }
}
