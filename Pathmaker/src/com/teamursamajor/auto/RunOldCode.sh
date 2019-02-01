#!/bin/sh

echo "cleaning"
rm -rf ../../../../build/classes/com/teamursamajor/auto/OldCode/*.class
rm ../../../../bin/*
echo "building"
javac -d ../../../../build/classes *.java
cd ../../../../bin
jar --create --file OldPathMaker.jar --manifest ../src/com/teamursamajor/auto/OldCode/META-INF/OLD_MANIFEST.mf -C ../build/classes/ .
echo "running"
java -jar OldPathMaker.jar
cd ../src/com/teamursamajor/auto/OldCode