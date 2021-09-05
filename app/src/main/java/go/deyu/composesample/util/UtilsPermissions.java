/*
 * Copyright (c) 2019. Gogolook. All rights reserved.
 */

package go.deyu.composesample.util;

/**
 * Created by Sheng-Yuan, Wang on 2019/3/6.
 */

import android.Manifest.permission;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

/** Utility class to help with runtime permissions. */
// TODO: [Dialer] Using the UtilsPermission of Kuma to replace.
public class UtilsPermissions {

    public static boolean hasPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

}

