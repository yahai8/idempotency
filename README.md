# idempotency
## 基于spring boot+redis+拦截器实现接口幂等性，譬如：订单支付等

访问localhost:8080/token,获取token
![](https://github.com/MuYaHai/idempotency/blob/master/images/%E6%89%B9%E6%B3%A8%202019-12-15%20210480.jpg)

然后使用jmeter进行并发测试，开启50个线程并发访问
![](https://github.com/MuYaHai/idempotency/blob/master/images/%E6%89%B9%E6%B3%A8%202019-12-15%20210418.jpg)

定义请求头header并携带token
![](https://github.com/MuYaHai/idempotency/blob/master/images/%E6%89%B9%E6%B3%A8%202019-12-15%20210417.jpg)

访问http://localhost:8080/idempotent/save
![](https://github.com/MuYaHai/idempotency/blob/master/images/%E6%89%B9%E6%B3%A8%202019-12-15%20210416.jpg)

可以看出，50个线程并发访问只有一个接口访问成功，这就实现了接口幂等性
![](https://github.com/MuYaHai/idempotency/blob/master/images/%E6%89%B9%E6%B3%A8%202019-12-15%20210415.jpg)

数据库里面也只插入了一条信息
![](https://github.com/MuYaHai/idempotency/blob/master/images/%E6%89%B9%E6%B3%A8%202019-12-15%20210419.jpg)
