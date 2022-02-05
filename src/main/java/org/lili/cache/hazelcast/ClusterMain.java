package org.lili.cache.hazelcast;

import com.hazelcast.core.Cluster;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;

import java.util.Set;

public class ClusterMain {
    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        Cluster cluster = hz.getCluster();
        Member localMember = cluster.getLocalMember();
        Set<Member> setMembers = cluster.getMembers();
        System.out.println(cluster);
        System.out.println(setMembers);
    }
}
