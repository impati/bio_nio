java -jar external_server-0.0.1-SNAPSHOT.jar \
--delay.membership=100 \
--delay.get-order=100 \
--delay.has-order-history=100 \
--delay.shop-categories=100 \
--delay.shop-franchise=100 \

scp -i "impati.pem" ../Desktop/external_server-0.0.1-SNAPSHOT.jar
ec2-user@ec2-43-203-203-247.ap-northeast-2.compute.amazonaws.com

- t4g.medium 기준으로 User 1000 , RPS 2100
- t4g.medium 기준으로 User 1500 , RPS 3000
- t4g.medium 기준으로 User 2000 , RPS 3700 , CPU 50 ~ 60% 
