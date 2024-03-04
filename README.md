![68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6a6176612d2532334544384230302e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d6a617661266c6f676f436f6c6f723d7768697465](https://user-images.githubusercontent.com/91574484/183223270-e84f506c-f96c-4b3a-a479-02775e72c7da.svg)
![68747470733a2f2f696d672e736869656c64732e696f2f62616467652f737072696e672d2532333644423333462e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67266c6f676f436f6c6f723d7768697465](https://user-images.githubusercontent.com/91574484/183223271-aaabe2e2-949c-4786-a58c-09c854bd0be7.svg)
![68747470733a2f2f696d672e736869656c64732e696f2f62616467652f706f7374677265732d2532333331363139322e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d706f737467726573716c266c6f676f436f6c6f723d7768697465](https://user-images.githubusercontent.com/91574484/183223272-26186653-af11-4965-9e75-aa7aff822ce0.svg)
![68747470733a2f2f696d672e736869656c64732e696f2f62616467652f646f636b65722d2532333064623765642e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d646f636b6572266c6f676f436f6c6f723d7768697465](https://user-images.githubusercontent.com/91574484/183223275-be4285b4-6a22-42ef-99f7-681fa3e9bb62.svg)

# Getting Started

### 

Bu oluşturduğum e-ticaret projesinde ilk olarak auth-microservice içinde kullanıcı kayıt olma ve giriş yapma işlemleri eklenmiştir. Kayıt olan kullanıcıyı RabbitMq kullanarak customer-microservice tarafına müşteri olarak kayıt edilmektedir. Daha sonra kullanıcıya sepet tanımlamak için customer-microservice'den müşteri bilgilerini yine RabbitMq kullanarak cart-microservice tarafına yönlendiriyoruz, bu şekilde kullanıcının adına bir sepet tanımlanmış oluyor. Product-microservice içinde de ürün ekleme-güncelleme-silme-listeleme işlemleri, sepet güncelleme-listeleme-boşaltma işlemleri ve sepete ürün ekleme-çıkarma işlemleri eklenmiştir.

### Requirements
- JDK(19)
- PostgreSQL
- IDE (IntelliJ IDEA)

### Technologies
- Java
- Spring Boot(3.2.2)
- PostgresSQL
- Spring Boot Data JPA
- Lombok
- Mapstruct
- Validation
- Spring Boot Starter Test

### Architecture
- MicroService

### Containerization
- Docker
- Message Broker: RabbitMQ

### RabbitMQ Docker :
docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=user -m=128m  rabbitmq:3-management

###
-SPRING_AMQP_DESERIALIZATION_TRUST_ALL=true 
RabbitMQ kullanımında deserilize işlemi sırasında hata almamak için deserilize edecek microservice'in Environment Variable(Ortam Değişkenleri) kısmına eklenmelidir.
(auth-microservice, customer-microservice, product-microservice application sınıflarına ortam değişkenleri olarak eklenmelidir.) 

### JwtTokenManager Secret Key ve Issuer
SecretKey ve Issuer bilgilerimizi gizlemek için Environment Variable(Ortam Değişkenleri) kullandım.(HRMS_SECRETKEY = Enoca) ve (HRMS_ISSUER = Tolga)
Auth-microservice'nin application sınıfına ortam değişkeni olarak eklenmelidir.

### Postgres
- EnocaAuthDB
- EnocaCustomerDB
- EnocaProductDB
##
![auth](https://github.com/tolgatekk/EnocaChallenge/assets/114102074/ae9fa6eb-7f34-4ccb-9c4d-d20b65cf64fd)
##
![customer](https://github.com/tolgatekk/EnocaChallenge/assets/114102074/dd08f8be-0f3e-4da8-b618-5be47870d49d)
##
![product](https://github.com/tolgatekk/EnocaChallenge/assets/114102074/596552f6-e20b-4405-b702-86b45489cc96)



