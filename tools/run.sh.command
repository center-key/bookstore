#!/bin/sh

#################
#  Run Project  #
#################

# Starts the Grails application

checkDbStarted() {
   ps -ax | grep mongod | grep dbpath
   if [ $? -ne 0 ]; then
      echo "ERROR: Database not running"
      exit
      fi
   }

cd $(dirname $0)
source env.sh.command
checkDbStarted
echo
cd $projectHome
echo "Compile"
echo "-------"
grails clean
grails compile ---gsp
echo
echo "Run"
echo "---"
grails run-app
