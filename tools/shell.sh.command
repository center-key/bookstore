#!/bin/sh

###################
#  Project Shell  #
###################

# Sets up environment and opens a shell prompt

cd $(dirname $0)
source env.sh.command
cd $projectHome
echo "Shell"
echo "-----"
echo "Commands:"
echo "$ grails run-app   # Launch application"
echo "$ grails test-app  # Run tests"
echo "$ grails clean     # Delete compiled resources"
echo
bash
