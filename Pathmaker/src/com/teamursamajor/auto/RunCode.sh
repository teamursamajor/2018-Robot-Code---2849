#!/bin/sh

echo "cleaning"
rm -rf ../../../../build/classes/com/teamursamajor/auto/*.class
rm ../../../../bin/*
echo "building"
javac -d ../../../../build/classes *.java
cd ../../../../bin
jar --create --file PathMaker.jar --manifest ../src/com/teamursamajor/auto/META-INF/MANIFEST.mf -C ../build/classes/ .
echo "running"
java -jar PathMaker.jar
cd ../src/com/teamursamajor/auto