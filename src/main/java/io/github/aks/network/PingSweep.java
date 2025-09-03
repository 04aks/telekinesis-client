package io.github.aks.network;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PingSweep {
    public static List<String> discover(String subnet, int timeout) throws InterruptedException{
        List<String> reachableHosts = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executor = Executors.newFixedThreadPool(50);

        for(int i = 1; i < 255; i++){
            int hostId = i;
            executor.submit(() -> {
                String host = subnet + "." + hostId;
                try{
                    InetAddress inet = InetAddress.getByName(host);
                    if(inet.isReachable(timeout)){
                        reachableHosts.add(host);
                    }
                }catch(Exception ignored){
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        return reachableHosts;
    }
}
