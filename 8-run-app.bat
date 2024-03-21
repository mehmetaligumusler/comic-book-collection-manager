@echo off
@setlocal enableextensions
@cd /d "%~dp0"

echo Running Application
java -jar comic_manager_app/target/comic_manager_app-1.0-SNAPSHOT.jar

echo Operation Completed!
pause