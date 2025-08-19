FROM node:19.5.0

LABEL authors="sergio"

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

COPY . .


RUN apt-get update && apt-get install -y curl

RUN apt install -y openjdk-17-jdk


EXPOSE 4000

CMD ["node", "./bin/www"]
