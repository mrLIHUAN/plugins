package io.flutter.plugins.webviewflutter.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.File;

public class IntentUtils {
    /**
     * 系统拍照和图库选择器
     */
    public static Intent imagePickerIntent(Context context, File file) {
        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, chooseImageIntent());
        //chooserIntent.putExtra(Intent.EXTRA_TITLE, "选择操作")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{cameraIntent(context, file)});
        return chooserIntent;
    }

    /**
     * 获取系统相机Intent
     */
    public static Intent cameraIntent(Context context, File file) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(context, file));
        return intent;
    }

    /**
     * 打开系统图库Intent
     */
    public static Intent chooseImageIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    private static Uri getUriForFile(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return FileProvider.getUriForFile(context, context.getPackageName(), file);
        } else {
            return Uri.fromFile(file);
        }
    }
}
