# RPG_Iter2

OOPS I Did It Again 
Running the Precompiled Binary
1.	Copy over all the files from the provided CD to your Hard Disk.
2.	Use the ‘cd’ command to get to the root of that folder.
3.	Run:
a.	On UNIX: sh RunGame.sh
b.	On Windows: RunGame.bat

Build Instructions
1.	Copy over the files form the cd to a folder on your Hard Disk
2.	Use the ‘cd’ command to get to the root of that folder
3.	Make sure you have Apache Maven Installed, using ‘which mvn’.
4.	Use the ‘cd’ command to ‘cd’ to the ‘build/’ directory
5.	Use the ‘ls’ command to check and see what files are there:
a.	A ‘pom.xml’ file
b.	A ‘app/’ folder
c.	A ‘README.md’
6.	 Run the command ‘mvn clean install’
7.	 Run the included .sh / .bat file to run the game:
a.	Via: ‘sh RunGame.sh’
b.	Or , on Windows, ‘RunGame.bat’
8.	(NOT NECESARRY) 
a.	To run the game manually, do:
b.	‘mvn clean install && java –jar target/OOPSIDidItAgain_Iteration2.jar’

Directories: (Information for the Group, not for Dave)

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

This way, we can just copy the 'cd_root' folder to the root directory of the CD-ROM we use.