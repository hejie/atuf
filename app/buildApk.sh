#!/bin/bash


echo "(1) 构建app， 生成 apk 和 ap， 同时把 ap 文件发布到本地仓库"

../gradlew clean assembleDebug

echo "(2) 发布基线包"
../gradlew publish
cat ./build/publications/maven/pom-default.xml
echo "(3) ap 模块发布到本地仓库成功"

echo "(4) 安装基线apk"
echo "adb install -r ./build/outputs/apk/*.apk"
adb install -r  ./build/outputs/apk/*.apk
