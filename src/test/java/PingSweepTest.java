import io.github.aks.network.PingSweep;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.net.InetAddress;
import java.util.List;

public class PingSweepTest {

    @Test
    void pingSweepingShouldDetectSomeHosts() throws Exception{
        List<String> hosts = PingSweep.discover("192.168.1", 200);
        for(String host : hosts){
            InetAddress inet = InetAddress.getByName(host);
            System.out.println(inet.getHostName());
        }
        assertNotNull(hosts, "There are devices connected to the same network, so it only makes sense the list is not null");
    }
    @Test
    void detectsLocalHost() throws Exception {
        boolean reachable = InetAddress.getByName("127.0.0.1").isReachable(1000);
        assertTrue(reachable, "Local host should be reachable");
    }
}
