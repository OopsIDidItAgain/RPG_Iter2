# RPG_Iter2

#Build Instructions
Build Instructions:
OOPS I Did It Again 

1.	Use the ‘cd’ command to get to the root of the OOPSIDidItAgain build directory
2.	Make sure you have Apache Maven Installed, using ‘which mvn’.
3.	Use the ‘ls’ command to check and see what files are there:
a.	A ‘pom.xml’ file
b.	A ‘app/’ folder
c.	A ‘README.md’
4.	 Run the command ‘mvn clean install’
5.	 Run the included .sh / .bat file to run the game:
a.	Via: ‘sh RunGame.sh’
b.	Or View ‘RunGame.bat’

Directories:

We have a app/ directory, which is where are the source code and resources are.
We have a cd_root directory, which is what will be placed at the root of the cd when we burn it
It's structure shoul be:
At the root of the CD we have our precompiled Jar and Script for running the Precompiled Jar
Inside the build/ directory, we have a copy of our git repo, so you could do:
cd cd_root/build/ && mvn clean install && sh RunGame.sh and it would work.
cd_root/
	build/app/.... same as the git repo
	build/pom.xml
	build/RunGame.sh -- This is for running after build
	build/RunGame.bat -- This is for running after build
	OOPSIDidItAgain_Iteration2.jar -- The precompiled binary
	RunGame.sh -- This is for running the precompiled binary
	RunGame.bat -- This is for running the precompiled binary
