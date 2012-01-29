#!/bin/sh

APPREV=$1
# if [ "x${APPREV}" == "x" ]; then
#     echo Defaulting to app rev 1 -- specify cmdline parameter to change
# 	APPREV=1
# fi

rm -f  /home/brada/bin/apache-karaf-2.2.5/deploy/*.jar
cp bundles-unversioned/app-rev-${APPREV}/*.jar /home/brada/bin/apache-karaf-2.2.5/deploy/

