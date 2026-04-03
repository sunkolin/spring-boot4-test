source .env

echo "docker stop running."

docker stop ${APP_NAME}

docker rm ${APP_NAME}

echo "docker stop complete."
