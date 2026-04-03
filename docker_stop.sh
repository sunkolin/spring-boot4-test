#!/bin/bash

source .env

echo "docker stop running."

sudo docker stop ${APP_NAME}

sudo docker rm ${APP_NAME}

echo "docker stop complete."
