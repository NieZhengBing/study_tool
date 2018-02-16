package delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * @author M
 * @create 2018/2/16
 */
public class PutInCache implements Runnable{
    private DelayQueue<CacheBean<User>> queue;

    public PutInCache(DelayQueue<CacheBean<User>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        CacheBean<User> cacheBean = new CacheBean<>("1", "5s", new User("Mark"), 5000);
        CacheBean<User> cacheBean2 = new CacheBean<>("1", "3s", new User("Mike"), 3000);
        queue.offer(cacheBean);
        System.out.println("put in cache: " + cacheBean.getId() + ":" + cacheBean.getName());
        queue.offer(cacheBean2);
        System.out.println("puut in cache: " + cacheBean2.getId() + ":" + cacheBean2.getName());
    }
}
