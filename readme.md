1. Для запуска на локальной машине в Envoroment Variable   укажите API_KEY=<ваш API ключ или IAM> и FOLDER_ID=<folderid на яндекс cloud>, приложение запуститься на 8080 порте

2. Для создания контейнера в Docker
  Пропишите команду:
  `docker build --tag=<Ваше название образа>`
  Для запуска контейнера: 
  `docker run docker run -p <Ваш порт>:8080 -e API_KEY=<api или iam от яндекс клауда>  -e FOLDER_ID=<folderid от яндекс клауда>  <Название образа>`
