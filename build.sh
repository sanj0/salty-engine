#!/usr/bin/env bash
############################################################################################################
# This software was published under the MIT License.                                                       #
# The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE #
#                                                                                                          #
# Copyright (c) since 2018 by the Salty Engine developers,                                                 #
# maintained by Malte Dostal                                                                               #
#                                                                                                          #
# Permission is hereby granted, free of charge, to any person obtaining a copy                             #
# of this software and associated documentation files (the "Software"), to deal                            #
# in the Software without restriction, including without limitation the rights                             #
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell                                #
# copies of the Software, and to permit persons to whom the Software is                                    #
# furnished to do so, subject to the following conditions:                                                 #
#                                                                                                          #
# The above copyright notice and this permission notice shall be included in all                           #
# copies or substantial portions of the Software.                                                          #
#                                                                                                          #
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR                               #
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,                                 #
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE                              #
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER                                   #
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,                            #
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE                            #
# SOFTWARE.                                                                                                #
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