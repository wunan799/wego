/**
 * Created by wunan on 16-9-26.
 */
package org.wnsoft.repository;

import org.wnsoft.utils.SerializeHelper;
import org.wnsoft.utils.WnException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class JedisRepo {
    private static final String REDIS_URI
            = "redis://admin:ESVULXIDSHXWEZYR@sl-aus-syd-1-portal.2.dblayer.com:15146";
    private Jedis jedis;

    public JedisRepo() {
        URI uri = null;
        try {
            uri = new URI(REDIS_URI);
        } catch (URISyntaxException e) {
            throw new WnException(e);
        }

        jedis = new Jedis(uri);
    }

    public void save(String id, Object object) {
        checkConnect();
        jedis.set(id.getBytes(), SerializeHelper.objectToByte(object));
    }

    public void append(String id, Object object) {
        checkConnect();
        jedis.rpush(id.getBytes(), SerializeHelper.objectToByte(object));
    }

    public <T> T load(String id) {
        checkConnect();
        byte[] bytes = jedis.get(id.getBytes());
        return SerializeHelper.byteToObject(bytes);
    }

    public <T> List<T> loadList(String id) {
        checkConnect();
        List<byte[]> byteList = jedis.lrange(id.getBytes(), 0, -1);
        List<T> objectList = new ArrayList<T>(byteList.size());

        for (byte[] bytes : byteList) {
            objectList.add(SerializeHelper.byteToObject(bytes));
        }

        return objectList;
    }

    public void remove(String id, Object object) {
        checkConnect();
        jedis.lrem(id.getBytes(), 1, SerializeHelper.objectToByte(object));
    }

    public void del(String id) {
        checkConnect();
        jedis.del(id.getBytes());
    }

    private void checkConnect() {
        try {
            doCheckConnect();
        } catch (JedisConnectionException e) {
            jedis.ping();
        }
    }

    private void doCheckConnect() {
        try {
            jedis.ping();
        } catch (JedisConnectionException e) {
            jedis.disconnect();
            throw (e);
        }
    }
}
