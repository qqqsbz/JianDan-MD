package com.example.coder.jiandan_md.util;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Looper;

import java.io.File;

/**
 * Created by coder on 16/9/21.
 */
public class JDMDMediaScannerConnectionClient implements MediaScannerConnection
        .MediaScannerConnectionClient {

//    private boolean isSmallPic;
    private File newFile;
    private MediaScannerConnection mediaScannerConnection;

    public JDMDMediaScannerConnectionClient(File newFile) {
//        this.isSmallPic = isSmallPic;
        this.newFile = newFile;
    }

    public void setMediaScannerConnection(MediaScannerConnection mediaScannerConnection) {
        this.mediaScannerConnection = mediaScannerConnection;
    }

    @Override
    public void onMediaScannerConnected() {
        mediaScannerConnection.scanFile(newFile.getAbsolutePath(),null);
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        Looper.prepare();

        Looper.loop();
    }
}