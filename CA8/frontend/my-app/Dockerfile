# Fetching the latest node image on alpine linux
FROM node:alpine AS development

# Declaring env
ENV NODE_ENV development

# Setting up the work directory
WORKDIR /react-app

# Installing dependencies
COPY ./package*.json /react-app

RUN npm install

# Copying all the files in our project
COPY . .

EXPOSE 8080
EXPOSE 8083

# Starting our application
CMD ["npm","start"]












# # build environment
# FROM node:alpine as build
# WORKDIR /app
# COPY . .
# RUN yarn
# RUN yarn build

# # production environment
# FROM nginx:stable-alpine
# COPY --from=build /app/build /usr/share/nginx/html
# COPY --from=build /app/nginx/nginx.conf /etc/nginx/conf.d/default.conf

# # Expose port 80
# EXPOSE 80
# EXPOSE 8080
# EXPOSE 8083

# # Start Nginx
# CMD ["nginx", "-g", "daemon off;"]
