#!/bin/bash

echo "(1) 修改依赖的源码和版本"
echo "    修改App工程源码"
echo "    修改versionName"

echo "(2)  重新构建apk， 生成patch 包  --info --debug"

../gradlew clean assembleDebug -DapVersion=1.0.0 -DversionName=1.0.1 --stacktrace

echo "(3) 上传 patch"
adb push ./build/outputs/tpatch-devDebug/update-*.json /sdcard/Android/data/com.omd/cache/update.json
adb push ./build/outputs/tpatch-devDebug/patch-*.tpatch /sdcard/Android/data/com.omd/cache

echo "(4) 点击动态部署完成安装 "
