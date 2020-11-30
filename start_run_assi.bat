
@echo off

	REM Author: Lim sai keat

	REM kill port
	set des_cd="%cd%"
	echo now system kill the used port 8080
	for /f "tokens=5" %%a in ('netstat -aon ^| find ":8080" ^| find "LISTENING"') do taskkill /f /pid %%a

	REM change path to the assignment directory
	echo :
	echo ============================================================
	echo Please change your directory to project directory.
	echo ============================================================
	echo :
	set /p conti=Do you want change position (Y/N):
	if "%conti%" == "Y" GOTO change
	if "%conti%" == "y" GOTO change
	GOTO end_if
:change
	set /p path=Please enter the directory: 
	cd "%path%"
:end_if

	REM go to tomcat
	echo starting aphache-tomcat-9.0.29 now
	cd server\bin
	set ex_cd = %cd%
	
	REM Build nessesary enviroment variable
	echo Building nessesary enviroment variable JAVA_HOME with value "%ProgramFiles%\Java\jdk*"
	@echo on
	setx JAVA_HOME "%ProgramFiles%\Java\jdk*\bin"
	setx JRE_HOME "%ProgramFiles%\Java\jre*\bin"
	@echo off

	REM start config tomcat
	echo start up aphache tomcat server now
	call startup.bat
	echo :
	echo ============================================================
	echo Starting aphache tomcat now, please wait a while . . . .
	echo ============================================================
	echo :
	pause
	
	cd "%des_cd%"

pause

	
