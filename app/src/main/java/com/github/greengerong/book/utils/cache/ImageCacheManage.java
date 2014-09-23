package com.github.greengerong.book.utils.cache;

import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * ***************************************
 * *
 * Auth: green gerong                     *
 * Date: 2014                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 * ****************************************
 */
public class ImageCacheManage {
    private static final String TAG = ImageCacheManage.class.getName();
    public static final String CACHE_DIRECTORY = Environment.getDownloadCacheDirectory() + "/book/";
    private final CacheManage cacheManage;

    public ImageCacheManage() {
        cacheManage = CacheManageFact.LRU_CACHE;
    }

    public byte[] get(String key) {
        final byte[] obj = cacheManage.get(key);
        byte[] bytes = obj == null ? getFromDisk(key) : obj;
        if (bytes != null) {
            cacheManage.put(key, bytes);
        }
        return bytes;
    }

    private byte[] getFromDisk(String key) {
        final File diskFile = getBytes(key);
        return diskFile.exists() ? getBytes(diskFile) : null;
    }

    private byte[] getBytes(File diskFile) {
        try {
            return FileUtils.readFileToByteArray(diskFile);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    private File getBytes(String key) {
        try {
            final String file = Base64.encodeToString(MessageDigest.getInstance("MD5").digest(key.getBytes()), Base64.DEFAULT);
            return new File(CACHE_DIRECTORY, file);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }


    public ImageCacheManage put(final String key, final byte[] value) {
        cacheManage.put(key, value);
        try {
            writeToFileAsync(key, value);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return this;
    }

    private void writeToFileAsync(final String key, final byte[] value) {
        new Thread() {
            @Override
            public void run() {
                try {
                    FileUtils.writeByteArrayToFile(getBytes(key), value);
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }.start();
    }
}
