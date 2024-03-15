@echo off
set scriptPath=%~dp0
set aocJar=%scriptPath%/aoc-runner/target/aoc.jar

if ["%JAVA_HOME%"] == [""] (
    echo "JAVA_HOME not set"
    exit /b 1
)
set buildJars=false
if ["%1"] == "solve" set buildJars=true
if not exist "%aocJar%" set buildJars=true

if "%buildJars%"=="true" (
    echo "building project jars"
    CALL mvnw.cmd --batch-mode --quiet clean install
)

echo "launching aoc command '%*'"
%JAVA_HOME%\bin\java -jar "%aocJar%" %*