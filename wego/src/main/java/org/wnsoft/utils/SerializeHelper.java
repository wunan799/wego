/**
 * Created by zhenghuasheng on 2015/11/5.
 * Modified by wunan on 2016/7/4
 */
package org.wnsoft.utils;

import java.io.*;

public class SerializeHelper {
    public static byte[] objectToByte(Object obj) throws WnException {
        if (obj == null) {
            throw new WnException("Object is null");
        }

        byte[] bytes = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            throw new WnException(e);
        }

        return bytes;
    }

    @SuppressWarnings("unchecked")
    public static <T> T byteToObject(byte[] bytes) throws WnException {
        if (bytes == null) {
            throw new WnException("Byte is null");
        }

        T t = null;
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = null;

        try {
            oi = new ObjectInputStream(new BufferedInputStream(bi));
            t = (T) oi.readObject();
            bi.close();
            oi.close();
        } catch (IOException e) {
            throw new WnException(e);
        } catch (ClassNotFoundException e) {
            throw new WnException(WnException.ERROR_NOTFOUND, e);
        }

        return t;
    }
}

