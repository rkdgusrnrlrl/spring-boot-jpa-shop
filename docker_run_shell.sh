#!/bin/bash
echo "Docker 빌드를 시작합니다."

DIR=$(pwd)
IMAGE_NAME="java_img"
IMAGE_VER="0.1"
CONT_NAME="java_cont"
if [[ "$(docker images -q $IMAGE_NAME:$IMAGE_VER 2> /dev/null)" == "" ]]; then
	  docker build -t $IMAGE_NAME:$IMAGE_VER .
  fi

  if [[ "$(docker ps -a | grep java_cont 2> /dev/null)" != "" ]]; then
	    docker rm -f $CONT_NAME
	fi

docker run -itd --name $CONT_NAME -p 81:8090 $IMAGE_NAME:$IMAGE_VER bash
