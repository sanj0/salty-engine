#!/usr/bin/env bash
DIR_BEFORE=$PWD

function installDependencies {

    printf "Installing dependencies... \n\n"

    echo Downloading sources for stdf...
    git clone https://www.github.com/edgelord314/stdf
    cd stdf
    echo Installing stdf...
    ./mvnw clean install

    echo Downloading sources for sysDepFiles...
    git clone https://www.github.com/edgelord314/sysDepFiles
    cd sysDepFiles
    echo Installing sysDepFiles...
    ./mvnw clean install

    cd ${DIR_BEFORE}

    printf "Finished installing dependencies! \n\n"
    echo Cleaning up temprary files...
    rm -drf tmp314
}

printf "NOTE: You have to have Java 1.8 installed, no later release! \n\n"
echo Creating directory tmp314...
mkdir tmp314
installDependencies
