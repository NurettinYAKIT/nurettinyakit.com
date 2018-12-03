FROM node:8-alpine as builder

COPY fe-stock-angular /fe-stock-angular

WORKDIR /fe-stock-angular

RUN npm install
RUN $(npm bin)/ng build

FROM nginx:alpine

COPY --from=builder /fe-stock-angular/dist/* /usr/share/nginx/html

EXPOSE 80

