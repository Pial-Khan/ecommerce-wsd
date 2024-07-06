# ecommerce-wsd
It's an ecommerce backend application for a reputed organization. It will serve necessary data and perform tasks through exposed APIs.

## Requirements
docker setup (latest)
maven version>3
Git
## Running the application locally
Clone the project from git repository
Url: https://github.com/Pial-Khan/ecommerce-wsd.git
1. mvn clean install -Pdev -DskipTests
2. docker-compose build
3. docker-compose up
4. docker-compose logs -f logstash or navigate to docker-compose logs -f logstash (Optional for log visualization)
