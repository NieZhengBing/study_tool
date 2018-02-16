import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author M
 * @create 2018/2/16
 */
public class UseChm {
    HashMap<String,String> hashMap = new HashMap<String, String>();
    ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<String, String>();

    public String putIfAbsent(String key, String value) {
        int a;
        synchronized (hashMap) {
            if (hashMap.get(key) == null) {
                return hashMap.put(key, value);
            }  else {
                return hashMap.get(key);
            }
        }
    }

    public String useChm(String key, String value) {
        return chm.putIfAbsent(key, value);
    }
}
