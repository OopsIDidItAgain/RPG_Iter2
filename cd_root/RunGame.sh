#!/bin/bash

TARGET_FILE=OOPsIDidItAgain_Iteration2.jar
if [ ! -e $TARGET_FILE ]
then
	echo "Dave, did you move the files around?"
else
	java -jar $TARGET_FILE
fi
