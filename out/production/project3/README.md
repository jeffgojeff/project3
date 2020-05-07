Project 3 due May 11 at 2 p.m.

[Tasks](PROJECT3.md)

# Project 3 submission

1. If you have cloned src repository, navigate to local code directory where they reside and pull the code 
```
cd src
git pull
```

2. navigate to local src folder ; copy project3 folder

3. navigate to <NET_ID> folder; paste project3 folder

 * make changes to java code in **<NET_ID>/project3** folder and save changes using IDE or text editor of choice
 * you **can** change all .java files, keep the functionality the same and use Swing/AWT components only 
 * make changes to README.md to include the commandline on how to run the test

```
javac -d classes .\src\project3\*.java .\<Net_ID>\project3\*.java
java -cp .\classes project3.MovieReviewApp
```

6. Occasionally check in changes to  **<NET_ID>** repository to save work. Make sure you are in CS3354/<NET_ID> folder for git command-line:

```
cd <NET_ID>
git add project3/*.java
git commit -m "Project 3 Swing GUI progress notes"
gitk
```
gitk will show you the status, close it to continue
```
git push origin:<NET_ID>
```
7. Make sure to commit and push **final** submission before the deadline!

# Other Class resources 
* [lectures](canvas.txstate.edu)
* [material](git.txstate.edu/CS3354/material)
