#FROM node:lts-alpine3.12
#FROM node:19-bullseye
FROM node:debian:11.6
WORKDIR /app
COPY . .
RUN npm install
CMD [ "npm", "start" ]
EXPOSE 9999