# 2018-Robot-Code---2849
2018 Ursa Major 2849 Welcome to the 2018 Programming Conglomeration GitHub Repository, this is where all of our code will be placed.

###Git Quick Reference for team members

We are no longer using git's command line interface, instead we will use Eclipse's built-in git capabilities (which we shoud have been doing this whole time). Only use the git CLI if you REALLY need to. This guide will cover the entire installation process from nothing to a full Eclipse environment.

#####Setting up Eclipse with FRC plugins

Follow the Java instructions from this site to install Java, Eclipse, and the FRC plugins:
https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/599679-installing-eclipse-c-java

#####Adding navx-mxp libraries to your project

We use a sensor called the NavX MXP, which requires some outside libraries.
Download and extract this file to any folder: https://www.kauailabs.com/public_files/navx-mxp/navx-mxp.zip
Run the setup.exe and accept all defaults.

#####Cloning the repository into your Eclipse workspace

In Eclipse, use the menus to navigate File -> Import -> Git -> Projects from Git
Select Clone URI
In the URI field, enter https://github.com/teamursamajor/2018-Robot-Code---2849.git
This should fill out the fields below it.
Do not worry about the Connection block, just ensure that HTTPS is selected.
Enter your github username and password in the username and password fields.
When all of that information is filled, select Next.
Click Next again.
Change the directory where the code will be stored if you want, but that is not recommended. Click Next.
Make sure Import existing Eclipse Projects is selected at the top, then click Next.
Select the 2018-Robot project and click Finish.

If you have any problems with this process, contact Hershal or Charlie through GroupMe or some other means.

#####Pulling Changes

When you start your work for the day, always be sure to Pull, which adds changes made on other computers to your project.
Try not to make any changes before Pulling, as this will create a merge situation which can be stressful.

To Pull, right-click the main project directory 2018-Robot, then Team -> Pull.

If there is an issue that requires a Merge, go to the #####Merge section.

#####Committing and Pushing Changes

When you make an edit to a file (or add a file), a '>' will appear next to the changed files and directories. This indicates that you have an uncommitted change.
Committing changes is essentially a hard save that also adds information about when the change was made as well as exactly what changes were made.
To make committing changes easy, open the Git Staging view by selecting Window (at the top) -> Show View -> Other -> Git -> Git Staging.
This will open a tab in your Eclipse window (move it around if it's way too small). The Unstaged Changes box has all of your changes that will NOT be committed. The Staged Changes box contains all changes that WILL be committed. If a change you want is in the Unstaged Files box, drag it into the Staged Changes box.
In the Commit Message box, write a useful message that explains the changes you made. Appropriate messages would look like: "Fixed errors in Robot.java. Added Drive.java. Removed unnecessary code from Arm.java."
After writing your message, make sure your name and email is in the Author and Committer boxes, then click Commit. Clicking Commit and Push will Commit your changes as well as Push them to the github repository.
Commit often, push when you want others to use your work, and always Commit and Push at the end of the day.

#####Merging

Merging occurs when your committed changes do not match the committed changes pulled down from github. When you Pull and there is a conflict, Eclipse will alert you to the conflict and a red circle will appear on your files on the left. It's distressing.
The files with the red circle need to have conflicts resolved. Open the file and look for the mess of >>>>>>>>>>>>>>> and various other text that Eclipse shows as errors. Keep the code you want to commit and delete the code and text that you do not want. If you are not sure if some code is necessary, ask around or use your best intuition. If you make the wrong decision, git allows us to reclaim any lost code, so don't worry too much.

There are special programs called Merge Tools that will handle this process much better, but they will not be covered here. I recommend Meld if you want to try one.

######################THE FOLLOWING GUIDE IS FOR GIT CLI AND SHOULD NOT BE USED UNLESS ABSOLUTELY NECESSARY. SEE ABOVE FOR UPDATED GUIDE

#####Add repo to your computer

In the command line, type

git clone https://github.com/teamursamajor/2018-Robot-Code---2849
This clones the 2018 Robot Code repository to your computer, Git will now track any changes you make to existing files.

#####Adding files If you created a new file or changed a file, such as "Drive.java", you will need to tell Git to track this change. Simply type

git add Drive.java
replacing Drive.java with the file you want to add, Git will now "stage" that change.

#####Deleting files If you want to remove a file from the repository, type

git rm Drive.java
or whatever file you want to delete. This deletes the file from your computer as well.

#####Checking your files Typing

git status
will tell you the status of your files, such as what has been added, modified, or deleted. Files under "Changes to be committed:" will be committed to the repository, while files under "Changes not staged for commit:" will need to be added before committing to make the changes permanent.

#####Committing: Your code needs to be committed after you have made changes. Otherwise, the changes will only stay on your computer and not be added to the online repository. Typing

git commit
will launch a text editor and allow you to add a message to your commit. The commit message should describe the changes you made, and if you leave it blank, it will not commit. If you want to skip opening the editor, typing

git commit -m [message]
where [message] is your message will allow you to add a message without opening the editor. You can also skip the "add" command with

git commit -a
which will automatically add all files that have been changed. It will NOT add files that have been created. To add all content modified/created/removed (potentially dangerous, be aware when using) use the command

git add -A
#####Pushing: After committing, you must push your code to the repository to save your changes. Simply type

git push
and you will be prompted for your username and password. Use teamursamajor's username and password.

Good practice is to commit often and push your commits at the end of the day. This is your responsibility. Sign your commits with your name or your programming name at the end of each commit.
