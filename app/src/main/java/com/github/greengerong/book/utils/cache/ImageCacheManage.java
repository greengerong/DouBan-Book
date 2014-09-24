package com.github.greengerong.book.utils.cache;

import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.github.greengerong.book.utils.LogUtils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

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
    public static final String CACHE_DIRECTORY = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/book/";
    private final CacheManage cacheManage;

    public ImageCacheManage() {
        cacheManage = CacheManageFact.getLruCache();
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
        final File diskFile = getFile(key);
        return diskFile.exists() ? getBytes(diskFile) : null;
    }

    private byte[] getBytes(File diskFile) {
        try {
            return FileUtils.readFileToByteArray(diskFile);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }

    private File getFile(String key) {
        try {
            final String file = Base64.encodeToString(key.getBytes(), Base64.NO_WRAP);
            return new File(CACHE_DIRECTORY, file + ".bak");
        } catch (Exception e) {
            Log.e(TAG, LogUtils.getStackTrace(e));
        }
        return null;
    }


    public ImageCacheManage put(final String key, final byte[] value) {
        cacheManage.put(key, value);
        try {
            writeToFileAsync(key, value);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return this;
    }

    private void writeToFileAsync(final String key, final byte[] value) {
        new Thread() {
            @Override
            public void run() {
                try {
                    final File file = getFile(key);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileUtils.writeByteArrayToFile(file, value);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, e.toString());
                }
            }
        }.start();
    }
}
