# syntax=docker/dockerfile:1
FROM node:18-alpine AS build
COPY --chown=node:node . /home/node/src
WORKDIR /home/node/src
RUN npm install
RUN npm run build
RUN ls

FROM nginx:1.23
EXPOSE 80
COPY --from=build --chown=nginx:nginx /home/node/src/build/ /usr/share/nginx/html/