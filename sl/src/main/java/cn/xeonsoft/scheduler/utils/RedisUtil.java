package cn.xeonsoft.scheduler.utils;

import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    // Redis 服务器 IP
    private String address = "127.0.0.1";

    // Redis的端口号
    private int port = 6379;

    // 访问密码
    private String password = "";

    // 连接 redis 等待时间
    private int timeOut = 10000;

    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
    private int maxTotal = 1024;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8
    private int maxIdle = 200;

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private int maxWait = 10000;

    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
    private boolean testOnBorrow = true;

    // 连接池
    private JedisPool jedisPool = null;

    // 构造函数
    public RedisUtil() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            config.setTestOnBorrow(testOnBorrow);
            jedisPool = new JedisPool(config, address, port, timeOut, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取 Jedis 实例
    public Jedis getJedis() {
        if (jedisPool != null) {
            return jedisPool.getResource();
        }
        return null;
    }

    /**
     * 设置 过期时间
     *
     * @param key
     * @param seconds
     *            以秒为单位
     * @param value
     */
    public void setString(String key, int seconds, String value) {
        try {
            getJedis().setex(key, seconds, value);
        } catch (Exception e) {
        }
    }

    /**
     * 获取String值
     *
     * @param key
     * @return value
     */
    public String getString(String key) {
        if (getJedis() == null || !getJedis().exists(key)) {
            return null;
        }
        return getJedis().get(key);
    }

    /**
     * 设置 list
     *
     * @param <T>
     * @param key
     * @param value
     */
    public <T> void setList(String key, List<T> list) {
        try {
            getJedis().set(key.getBytes(), ObjectTranscoder.serialize(list));
        } catch (Exception e) {
        }
    }

    /**
     * 获取list
     *
     * @param <T>
     * @param key
     * @return list
     */
    public <T> List<T> getList(String key) {
        if (getJedis() == null || !getJedis().exists(key.getBytes())) {
            return null;
        }
        byte[] in = getJedis().get(key.getBytes());
        List<T> list = (List<T>) ObjectTranscoder.deserialize(in);
        return list;
    }

    /**
     * 设置 map
     *
     * @param <T>
     * @param key
     * @param value
     */
    public <T> void setMap(String key, Map<String, T> map) {
        try {
            getJedis().set(key.getBytes(), ObjectTranscoder.serialize(map));
        } catch (Exception e) {
        }
    }

    /**
     * 获取list
     *
     * @param <T>
     * @param key
     * @return list
     */
    public <T> Map<String, T> getMap(String key) {
        if (getJedis() == null || !getJedis().exists(key.getBytes())) {
            return null;
        }
        byte[] in = getJedis().get(key.getBytes());
        Map<String, T> map = (Map<String, T>) ObjectTranscoder.deserialize(in);
        return map;
    }
}