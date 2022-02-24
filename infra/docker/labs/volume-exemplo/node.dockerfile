# Imagem base, a nossa imagem será criada em cima da imagem do node:latest que será obtida do repositório do docker hub
FROM node:latest

# definicição de rótulos que será exibido no docker inspect, nesse caso colocamos o autor da imagem
LABEL org.opencontainers.image.authors=="Gilson Souza"

# Definição das variáveis de ambiente da imagem, para cada nova variável inserir uma nova linha
ENV PORT=3000

# Conteúdo que será copiado para a imagem, nesse caso copiaremos todo o conteúdo do diretório atual. Para cada nova copia adicionar uma nova linha
COPY . /var/www

# diretório que será acessado assim que a imagem for criada
WORKDIR /var/www

# Comando que será executado assim que a imagem for criada. Para novo comando inserir uma nova linha
RUN npm install

# Comando que será executado assim que o container for criado
ENTRYPOINT [ "npm", "start"]

# porta que será exposta para fora do container. Para cada nova porta adicionar uma nova linha
EXPOSE $PORT