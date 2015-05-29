#!/bin/sh

#########################
#  Project Environment  #
#########################

# Example install folders:
#    ~/apps
#       /grails
#          /grails-3.0.0
#          /grails-3.0.1
#
# Sets JAVA_HOME, MONGODB_HOME, and GRAILS_HOME and adds them to the path.

echo
echo "Environment"
echo "-----------"
cd ..
projectHome=$(pwd)
export JAVA_HOME=$(/usr/libexec/java_home)
export MONGODB_HOME=~/apps/mongodb/$(ls ~/apps/mongodb | grep mongodb | tail -1)
export GRAILS_APP_NAME=$(basename $(pwd))
export GRAILS_HOME=~/apps/grails/$(ls ~/apps/grails | grep grails | tail -1)
export GRAILS_VERSION=$(echo $GRAILS_HOME | sed s/.*grails-//)
export PATH=$PATH:$MONGODB_HOME/bin:$GRAILS_HOME/bin
echo "App Name: $GRAILS_APP_NAME"
echo "Java:     $JAVA_HOME"
echo "MongoDB:  $MONGODB_HOME"
echo "Grails:   $GRAILS_HOME"
echo "Path:     $PATH"
echo
