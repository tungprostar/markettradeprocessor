# Market trade processor
 A Simple market trade processor app ....
 
# An idea behind
I am going to build a market trade processor which consumes trade messages via an endpoint, processes those messages in some way and delivers a frontend of processed information based on the consumed messages.

Trade messages will be POST’ed to a single endpoint and will take the JSON form of:
```
{
    "userId": "134256",
    "currencyFrom": "EUR",
    "currencyTo": "GBP",
    "amountSell": 1000,
    "amountBuy": 747.10,
    "rate": 0.7471,
    "timePlaced" : "24-JAN-15 10:27:44",
    "originatingCountry" : "FR"
}
```
The system has to process these messages and display results in UI with a realtime visualisation of messages being processed.
 
# Approach taken
For message consumption: easy and average implementation ideas have been use while fininshing the app
- Easy part: Consumed messages are saved to H2 DB (memory DB)
- Average part: I've implemented rate limmiting using bucket4j
User can only post request to api /message within maximum token in the bucket , after 6s, if the bucket is not full yet, add 1 token to the bucket.
- Hard part: I've implemented simple RabbitMQ ... cần thêm ý tưởng
For message Processor:
- Easy: Just fetch all consumed data and directly display it on frontend
- Because I've implement RabbitMQ but I don't have time to implement Webstomp (user for realtime data), in frontend, in order to kindly recreate that, I use interval, every 1s, call api to fetch newest result (I know this is a bad practice but just to demonstrate how things work)
For Message Frontend:
- Easy: Render a list of consumed messages

For message processor

# Overall architecture

![image](https://user-images.githubusercontent.com/47554751/155500110-cb7ebdc1-996c-43ba-969e-de89073fbffe.png)


# How to run and test the result
Install rabbitmq with docker:
```
docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3.7-management
```
Clone git repository and run Spring boot app
H2 database link: http://localhost:8080/h2
Clone frontend git repository and run these command
```
npm install
```
to start web server:
```
ng serve
```
Market trade frontend url: http://localhost:4200
![image](https://user-images.githubusercontent.com/47554751/155517065-1a2f0992-cbc0-403a-8b6a-c5dcd5f5e102.png)

Testing rate limiting api
open postman
using POST method with this url: http://localhost:8080/message
payload is the message above
![image](https://user-images.githubusercontent.com/47554751/155517295-c1758874-d8cc-4c01-b6a2-ccfe8c520d65.png)

In response header there is a field which will give us information of number of tokens remaning
Try to hit more 10 times:
![image](https://user-images.githubusercontent.com/47554751/155517478-ae35a825-c3db-4bdf-b782-37af9073b390.png)


