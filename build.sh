#!/usr/bin/env bash
#!/bin/bash

# This script will build the Salty Engine library to 2 jars: the one with dependencies and a main-attribute within the
# Manifest and the other one is a jar without dependencies and without a main-attribute.
# You don't need maven installed for this to work, however you have to have java 1.8 and no later version set as default
# If you execute this with the option -run it will run the jar with the dependencies
#

VERSION=$(printf 'VERSION=${project.version}\n0\n' | ./mvnw org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate | grep '^VERSION' | cut -f2 -d"=")
JAR=$(ls target | sort | grep ^salty-engine | head -1)

if [ "$1" == "-help" ]; then
    printf "\n"
    printf "This little script builds the maven project \"salty-engine\" version "
    echo $VERSION
    printf "\n"
    printf "POSSIBLE OPTIONS:\n"
    printf "    -run : runs the built jar\n"
    printf "    -help : display this little crappy help\n"

    exit 0
fi

./mvnw clean install

if [ "$1" == "-run" ]; then
    java -jar target/$JAR
fi

if [ "$1" == "-help" ]; then
    printf POSSIBLE OPTIONS:
    printf -run : runs the built jar
    printf -help : display this little crappy help
fi