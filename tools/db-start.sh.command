#!/bin/sh

###############
#  Launch DB  #
###############

# Starts MongoDB

cd $(dirname $0)
source env.sh.command
cd $MONGODB_HOME/..
mkdir -p db
echo "Shell:"
echo "   $ mongo"
echo "   > show dbs"
echo "   > exit"
echo
echo "Starting MongoDB..."
echo "Use ^C to shutdown"
ulimit -n 1000  #prevent "soft rlimits too low" warning
mongod --dbpath db
