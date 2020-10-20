package io.flutter.plugins.webviewflutter.utils;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class FileUtils {
    public static File getTmpPicFile() {
        String state = Environment.getExternalStorageState();
        if ("mounted".equals(state)) {
            return getTmpPicFile(null);
        } else {
            throw new IllegalStateException("The media not mounted.");
        }
    }

    private static synchronized File getTmpPicFile(String random) {
        File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!pic.exists()) {
            pic.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        StringBuilder builder = new StringBuilder();
        builder.append("media_");
        builder.append(timeStamp);
        if (random != null) {
            builder.append("_").append(random);
        }

        builder.append(".jpg");
        String fileName = builder.toString();
        File file = new File(pic, fileName);
        if (file.exists()) {
            file = getTmpPicFile(UUID.randomUUID().toString());
        }

        return file;
    }

    /**
     * 删除文件夹
     */
    public static void deleteWhole(File file) {
        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                deleteWhole(childFile);
            }
            file.delete();
        } else if (file.exists()) {
            file.delete();
        }
    }
}
