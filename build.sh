#!/usr/bin/env bash
############################################################################################################
# Copyright 2018 Malte Dostal                                                                              #
#                                                                                                          #
#    Licensed under the Apache License, Version 2.0 (the "License");                                       #
#    you may not use this file except in compliance with the License.                                      #
#    You may obtain a copy of the License at                                                               #
#                                                                                                          #
#        http://www.apache.org/licenses/LICENSE-2.0                                                        #
#                                                                                                          #
# Unless required by applicable law or agreed to in writing, software                                      #
# distributed under the License is distributed on an "AS IS" BASIS,                                        #
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                 #
# See the License for the specific language governing permissions and                                      #
# limitations under the License.                                                                           #
############################################################################################################

# This script will build the Salty Engine library to 2 jars: the one with dependencies and a main-attribute within the
# Manifest and the other one is a jar without dependencies and without a main-attribute.
# You don't need maven installed for this to work, however you have to have java 1.8 and no later version set as default
# If you execute this with the option -run it will run the jar with the dependencies
#

VERSION=$(printf 'VERSION=${project.version}\n0\n' | ./mvnw org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate | grep '^VERSION' | cut -f2 -d"=")

if [ "$1" == "-help" ]; then
    printf "\n"
    printf "This little script builds the maven project \"salty-engine\" version "
    echo ${VERSION}
    printf "\n"
    printf "POSSIBLE OPTIONS:\n"
    printf "    -run : runs the built jar\n"
    printf "    -help : display this little crappy help\n"

    exit 0
fi

./mvnw clean install

JAR=$(ls target | sort | grep ^salty-engine | head -1)

if [ "$1" == "-run" ]; then
    java -jar target/${JAR}
fi

if [ "$1" == "-help" ]; then
    printf POSSIBLE OPTIONS:
    printf -run : runs the built jar
    printf -help : display this little crappy help
fi