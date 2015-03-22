#!/bin/bash

TARGET_FILE=`pwd`/target/OOPSIDidItAgain_Iteration2.jar
if [ ! -e $TARGET_FILE ]
then
	echo "Dave, There is a good chance you forgot to build, try:
mvn clean install && sh RunGame.sh"
else
	java -jar $TARGET_FILE
fi
