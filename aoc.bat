@echo off
set scriptPath=%~dp0

if ["%JAVA_HOME%"] == [""] (
    echo "JAVA_HOME not set"
    exit /b 1
)

echo "building project jars"
CALL mvnw.cmd --batch-mode --quiet clean install

echo "launching aoc command '%*'"
%JAVA_HOME%\bin\java -jar %SCRIPT_DIR%/aoc-runner/target/aoc.jar %*