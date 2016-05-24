Last update of Document: 25.11.2011

==============================================
Before installation
==============================================

1. You have to have installed Apache Ant. It is avaible on Mac OS, Linux, Windows. 
2. To check if you already have installed type in console/terminal/commandline

  ant -version
it should response with something like that: 
  Apache Ant(TM) version 1.8.2 compiled on October 14 2011

3. If not continue with installation. All information how to do it are on the page : 
http://ant.apache.org/


4. WARNING FOR WINDOWS USERS(and maybe oder:))!! In order BBuilder to work you have to make some ajustments in instalation of Ant. 

- go to folder where you had installed ANT
- go to  lib folder
- remove file ant-jsch (best option is to move it to other folder, renaming will not work).




==============================================
How to install BBuilder for specified project:
============================================== 

1. First we have to get it from repository. 

2. From copy_to_project folder we copy all files to the project we want to build. It should be placed in the top folder of our project. 

3. In the project.properties we set parameters (they are already filled with default data). We put there only names of folders with out urls. When properties are in top folder this where ant start to look. Properties are well documented in files itself. 

4. In file private.properties we set our private data. 

You have to put definitive path to BBuilder
path.bbuilder = /Users/piotr/Documents/Workspace/BBuilder/devel

===WARNING====
in order path to work correctly you should put "/" instead of "\" (also on Windows). 

7. We should commit to project build.xml and project.properties. Private.properties should not be commited. As this files could be different for each developer and can cause SVN conflicts.  

=====================================
How to use BBuilder:
===================================== 

Just with your console go to folder of project and type: 

ant  sshDev (will send NOT compressed, NOT combined files on server in one language)
ant sshTest (will send compressed, combined data on server in one language)
ant sshProd (will send compressed, combined data on server in all avaible languages)

Also when this documented is outdated please check import.xml file to see what is avaible. 

======================================
Languages info
======================================

BBuilder need languages files to work. For debug you should add something like that 

<script type="text/javascript" name="debug" src="./i18n/pl-pl.js"></script>

everything with name="debug" will be removed during releasing proccess. 

After that BBuilder will add languages as set in project.properties



