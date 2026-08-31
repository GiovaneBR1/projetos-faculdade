@echo off
echo Compilando...
if not exist out mkdir out

:: 1. Lista todos os arquivos .java dentro da pasta src e salva num arquivo temporario
dir /s /B src\*.java > sources.txt

:: 2. Pede pro compilador ler esse arquivo e compilar TODAS as classes de todos os pacotes
javac -encoding UTF-8 -d out @sources.txt

:: 3. Apaga o arquivo temporario para manter a pasta limpa
del sources.txt

if %ERRORLEVEL% NEQ 0 (
    echo ERRO: falha na compilacao.
    pause
    exit /b 1
)
echo Compilacao OK. Executando...
echo.

:: 4. Executa o programa chamando o NOME COMPLETO da classe (pacote.Classe)
java -cp out main.Main

pause