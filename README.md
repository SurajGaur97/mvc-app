# mvc-app
This project is full monolithic application. Created on Spring MVC architecture with Hibernate-JPA to SQL Server datatbase connectivity for Sql dependency I have attached as zip file with name 'Sql Jdbc 4.0 Jar.zip'. Find it and attach it in maven dependencies location where it shows missing from in the project.

###############################################

It required Tomcate 8.0 to run the application.

###############################################

For typing all these commands you need to install git from: https://git-scm.com/downloads
And after installation you need to go to you project directory then open here the windows cmd here.
And then after you can try the bolow written command:

For Checking Status of your request :::::::::::::::::::::::::::::::::::

    git status

For Pushing files, type the following commands ::::::::::::::::::::::::

    git add .

    git commit -m "Message"

    git push origin master (branch name is 'master' here).

For pulling you request when something went wrong you can run the follong command ::::::::::

    git pull origin master



Create a new repository on the command line ::::::::::::::::::::::::::::

    #This command will create a .git folder to project root directory for holding all config related to git and for maintaning its history od committing file too.
    git init

    #for adding a new file named: README.md
    git add README.md

    #For adding all the files existing im the dorectory you have cheesen and opened the cmd
    git add .

    #its for commiting the file added as above
    git commit -m "first commit"

    #selecting the branch where it will commit
    git branch -M master

    #Setting the location on github to store the file
    git remote add origin https://github.com/SurajGaur97/mvc-app.git

    #pushing the file onto the provided loction on github
    git push -u origin master
    
    
Push an existing repository from the command line ::::::::::::::::::::::

    git remote add origin https://github.com/SurajGaur97/mvc-app.git
    
    git branch -M master
    
    git push -u origin master
