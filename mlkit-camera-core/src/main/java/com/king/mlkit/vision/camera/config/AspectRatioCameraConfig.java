/*
 * Copyright (C) Jenly, MLKit Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.king.mlkit.vision.camera.config;

import android.content.Context;
import android.util.DisplayMetrics;

import com.king.mlkit.vision.camera.CameraScan;
import com.king.mlkit.vision.camera.util.LogUtils;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;

/**
 * 相机配置：根据纵横比配置相机，使输出分析的图像尽可能的接近屏幕的比例
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public final class AspectRatioCameraConfig extends CameraConfig {

    /**
     * 纵横比
     */
    private int mAspectRatio;

    public AspectRatioCameraConfig(Context context) {
        super();
        initTargetAspectRatio(context);
    }

    /**
     * 初始化 {@link #mAspectRatio}
     *
     * @param context
     */
    private void initTargetAspectRatio(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        LogUtils.d(String.format(Locale.getDefault(), "displayMetrics: %dx%d", width, height));

        float ratio = Math.max(width, height) / (float) Math.min(width, height);
        if (Math.abs(ratio - CameraScan.ASPECT_RATIO_4_3) < Math.abs(ratio - CameraScan.ASPECT_RATIO_16_9)) {
            mAspectRatio = AspectRatio.RATIO_4_3;
        } else {
            mAspectRatio = AspectRatio.RATIO_16_9;
        }
        LogUtils.d("aspectRatio: " + mAspectRatio);
    }

    @NonNull
    @Override
    public Preview options(@NonNull Preview.Builder builder) {
        return super.options(builder);
    }

    @NonNull
    @Override
    public CameraSelector options(@NonNull CameraSelector.Builder builder) {
        return super.options(builder);
    }

    @NonNull
    @Override
    public ImageAnalysis options(@NonNull ImageAnalysis.Builder builder) {
        builder.setTargetAspectRatio(mAspectRatio);
        return super.options(builder);
    }
}
