

package com.omd;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.taobao.atlas.dex.util.FileUtils;
import com.taobao.atlas.dexmerge.MergeCallback;
import com.taobao.atlas.update.AtlasUpdater;
import com.taobao.atlas.update.model.UpdateInfo;
import com.taobao.atlas.update.util.PatchInstaller;
import com.taobao.atlas.update.util.PatchMerger;

import java.io.File;
import java.io.IOException;

public class Updater {

    public static void update(final Context context) {

        File updateInfo = new File(context.getExternalCacheDir(), "update.json");

        if (!updateInfo.exists()) {
            Log.e("update", "更新信息不存在，请先 执行 buildTpatch.sh");
            toast("更新信息不存在，请先 执行 buildTpatch.sh", context);
            return;
        }

        String jsonStr = new String(FileUtils.readFile(updateInfo));
        UpdateInfo info = JSON.parseObject(jsonStr, UpdateInfo.class);

        File patchFile = new File(context.getExternalCacheDir(), "patch-" + info.updateVersion + "@" + info.baseVersion + ".tpatch");

        try {
//            AtlasUpdater.update(info, patchFile);

            PatchMerger patchMerger = new PatchMerger(info, patchFile, new MergeCallback() {
                @Override
                public void onMergeResult(boolean result, String bundleName) {
                    if (result) {
                        toast(bundleName + "merge success!", context);
                    }else {
                        toast(bundleName + "merge failed!", context);

                    }
                }
            });

            try {
                patchMerger.merge();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("mergeOutputs:",String.valueOf(patchMerger.mergeOutputs.size()));
            PatchInstaller patchInstaller = new PatchInstaller(patchMerger.mergeOutputs, info);

            patchInstaller.install();

            Log.e("update", "update success");
            toast("更新成功，请重启app", context);
        } catch (Throwable e) {
            e.printStackTrace();
            toast("更新失败, " + e.getMessage(), context);
        }


    }

    private static void toast(final String msg, final Context context) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
