package com.example.coder.jiandan_md.util;

import java.util.List;

/**
 * Created by coder on 16/9/12.
 */
public interface CallBackService {

    void onSuccess(int page, Object object);

    void onError(int page,String errorMsg);
}
