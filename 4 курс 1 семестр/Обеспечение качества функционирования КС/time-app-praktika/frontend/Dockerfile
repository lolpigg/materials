FROM node:alpine
WORKDIR /time-app
COPY package*.json .
RUN npm install
COPY . .
EXPOSE 8954
CMD ["npm", "run", "dev"]