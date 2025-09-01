java -jar external_server-0.0.1-SNAPSHOT.jar \
--delay.membership=100 \
--delay.get-order=100 \
--delay.has-order-history=100 \
--delay.shop-categories=100 \
--delay.shop-franchise=100 \
--delay.coupon-issuable=100 \
--delay.member-coupon=100 \
--delay.shop-coupon=100 \
--delay.coupon=100 > app.log 2>&1 &

scp -i "impati.pem" ../Desktop/external_server-0.0.1-SNAPSHOT.jar
ec2-user@${ip}:/home/ec2-user

scp -i "impati.pem" ../Desktop/nio_server-0.0.1-SNAPSHOT.jar
ec2-user@${ip}:/home/ec2-user

### external server test

- t4g.medium 기준으로 User 1000 , RPS 2100
- t4g.medium 기준으로 User 1500 , RPS 3000
- t4g.medium 기준으로 User 2000 , RPS 3700 , CPU 50 ~ 60%

## java install

```
sudo dnf -y update
sudo dnf -y install java-17-amazon-corretto-headless
java -version
```

### nio server test

- {"memberCoupons":39407,"hasOrderHistory":176909,"coupons":39382,"shopCoupons":148516,"issuable":39263,"getCategories":
  176866,"getOrder":176883,"getFranchise":176872,"membership":176896}
- t4g.medium 기준으로 User 300 , RPS 360 , CPU 80 ~ 90%
- t4g.medium 기준으로 User 200 , RPS 300 , CPU 75%
