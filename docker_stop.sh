source .env

echo "stop start"

sudo docker ps -a -q --filter "application_name=$APP_NAME"|grep -q . && docker stop $APP_NAME && docker rm -fv $APP_NAME

echo "stop complete"
