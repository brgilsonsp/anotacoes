#FROM node:latest as package
#MAINTAINER Douglas Quintanilha
#ENV NODE_ENV=development
#COPY . /var/www
#WORKDIR /var/www
#RUN npm install 
#ENTRYPOINT ["npm", "start"]
#EXPOSE 3000

#Imagem criada apenas para buildar a aplicação
FROM node:latest as package
ENV NODE_ENV=development
COPY . /var/www
WORKDIR /var/www
RUN npm install 

#Imagem de 'produção'
FROM node:latest
WORKDIR /var/www
# copia o pacote buildado da imagem acima
COPY --from=package /var/www/ /var/www/
ENTRYPOINT ["npm", "start"]
EXPOSE 3000