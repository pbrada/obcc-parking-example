#!/bin/sh

SRC_BASE=sources/ctx-subst-bundles
DEST_BASE=bundles-unversioned/app-rev
BUNDLE_PREFIX=obcc-parking-example

echo --- copying generated bundles to their "repository" in ${DEST_BASE} folder
echo --- app rev 1
SRC=${SRC_BASE}-1/generated
DEST=${DEST_BASE}-1

rm -f ${DEST}/*
cp ${SRC}/*.jar ${DEST}

echo --- app rev 2
SRC=${SRC_BASE}-2/generated
DEST=${DEST_BASE}-2

rm -f ${DEST}/*
cp ${SRC}/*.jar ${DEST}
cp ${SRC_BASE}-1/generated/${BUNDLE_PREFIX}.statsbase.jar ${DEST}
cp ${SRC_BASE}-1/generated/${BUNDLE_PREFIX}.dashboard.jar ${DEST}
cp ${SRC_BASE}-1/generated/${BUNDLE_PREFIX}.carpark.jar ${DEST}

echo --- app rev 3
SRC=${SRC_BASE}-3/generated
DEST=${DEST_BASE}-3

rm -f ${DEST}/*
cp ${SRC}/*.jar ${DEST}
cp ${SRC_BASE}-1/generated/${BUNDLE_PREFIX}.statsbase.jar ${DEST}
cp ${SRC_BASE}-1/generated/${BUNDLE_PREFIX}.carpark.jar ${DEST}

echo --- app rev 4
SRC=${SRC_BASE}-4/generated
DEST=${DEST_BASE}-4

rm -f ${DEST}/*
cp ${SRC}/*.jar ${DEST}
cp ${SRC_BASE}-3/generated/${BUNDLE_PREFIX}.dashboard.jar ${DEST}

echo --- app rev 5
SRC=${SRC_BASE}-5/generated
DEST=${DEST_BASE}-5

rm -f ${DEST}/*
cp ${SRC}/*.jar ${DEST}
cp ${SRC_BASE}-3/generated/${BUNDLE_PREFIX}.dashboard.jar ${DEST}
cp ${SRC_BASE}-4/generated/${BUNDLE_PREFIX}.statsbase.jar ${DEST}
cp ${SRC_BASE}-4/generated/${BUNDLE_PREFIX}.gate.jar ${DEST}

echo --- app rev 6
SRC=${SRC_BASE}-6/generated
DEST=${DEST_BASE}-6

rm -f ${DEST}/*
cp ${SRC}/*.jar ${DEST}
cp ${SRC_BASE}-3/generated/${BUNDLE_PREFIX}.dashboard.jar ${DEST}
cp ${SRC_BASE}-4/generated/${BUNDLE_PREFIX}.statsbase.jar ${DEST}
cp ${SRC_BASE}-4/generated/${BUNDLE_PREFIX}.gate.jar ${DEST}
cp ${SRC_BASE}-5/generated/${BUNDLE_PREFIX}.roadsign.jar ${DEST}
cp ${SRC_BASE}-5/generated/${BUNDLE_PREFIX}.carpark.jar ${DEST}

