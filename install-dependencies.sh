#!/usr/bin/env bash
DIR_BEFORE=$PWD

function installDependencies {

    cd ..

    printf "Installing dependencies... \n\n"

    if [[ -e stdf ]]; then
        printf "Removing existing stdf clone... \n\n"
        rm -drf stdf
    fi

    if [[ -e sysDepFiles ]]; then
        printf "Removing existing sysDepFiles clone... \n\n"
        rm -drf sysDepFiles
    fi

    git clone https://www.github.com/edgelord314/stdf
    cd stdf
    ./mvnw clean install

    cd ..
    git clone https://www.github.com/edgelord314/sysDepFiles
    cd sysDepFiles
    ./mvnw clean install

    cd ${DIR_BEFORE}

    printf "Saving success into file... \n\n"

    touch ${HOME}/.saltyDepsInstalled

    printf "Finished installing dependencies! \n\n"
}

printf "NOTE: You have to have Java 1.8 installed, no later release! \n\n"

if [[ -e $HOME/.saltyDepsInstalled ]]; then
    read -p "It seems like the dependencies for Salty Engine were already installed, do you wish to reinstall them? [y/n]" -n1 REINSTALL
    printf "\n"
    
    case ${REINSTALL} in

        [yY]* ) installDependencies
    esac

    else
        installDependencies
fi