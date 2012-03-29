#!/bin/sh

APPREV=$1
DEPLOY_DIR=/home/brada/bin/apache-karaf-2.2.5/deploy/
echo Deploying bundles of application revision ${APPREV} to container ${DEPLOY_DIR} 

# if [ "x${APPREV}" == "x" ]; then
#     echo Defaulting to app rev 1 -- specify cmdline parameter to change
# 	APPREV=1
# fi

rm -f ${DEPLOY_DIR}*.jar
cp bundles-unversioned/app-rev-${APPREV}/*.jar ${DEPLOY_DIR}
ls -la ${DEPLOY_DIR}

