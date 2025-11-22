# Microservice Library App

Modern kütüphane yönetimi için tasarlanmış bu proje, Spring Boot 3.5.7 & Java 21 tabanlı mikroservislerden oluşur. Domain bazlı modüller DDD + CQRS yaklaşımını kullanırken, operasyonel servislerin geri kalanı klasik N-Katmanlı (Controller → Service → Repository) yapı ile geliştirilmiştir. Servisler Spring Cloud Gateway ve Eureka üzerinden ölçeklenir, konfigürasyonlar Config Server üzerinden tek noktadan yönetilir, Kafka ise ceza hesaplama sürecinde etkin olarak kullanılır. Tüm bileşenler Docker imajlarına sahiptir ve `docker compose` ile tek komutla ayağa kalkar.

## Genel Bakış
- **Mikroservis mimarisi:** Her bounded context için ayrı Spring Boot servisi, her servis kendi PostgreSQL veritabanına sahip.
- **Book-service:** DDD + Onion Architecture + CQRS; komut ve sorgu boru hatları domain model ile izole çalışır.
- **Diğer domain servisleri:** Customer, Borrow, Fine ve Review servisleri tipik N-Katmanlı yaklaşımda; controller servis katmanına, servis repository katmanına bağlı.
- **Olay tabanlı ceza akışı:** Borrow-service geciken emanetleri Kafka üzerinden yayınlar, Fine-service aynı olayı tüketip otomatik ceza üretir.
- **Gateway & Discovery:** Spring Cloud Gateway, Eureka kayıtlarını kullanarak yük dengeleme ve merkezi giriş sağlar.
- **Config Server:** Tüm `application-*.yml` dosyaları `configurations/` altında toplanır; servisler tek endpoint üzerinden konfigürasyon çeker.
- **Tamamı dockerize:** Her servis için Dockerfile mevcut, `docker-compose.yml` Kafka, Kafka UI ve veritabanı kapsayıcılarıyla birlikte tüm ortamı orkestre eder.

## Servisler ve Roller
| Servis | Port | Mimari | Rol |
| --- | --- | --- | --- |
| `book-service` | 8081 | DDD + CQRS + Onion | Kitap, yazar, kopya, dil, yayınevi ve çevirmen yönetimi |
| `borrow-service` | 8083 | N-Katmanlı | Ödünç alma/rezervasyon akışları, Kafka üzerinden gecikme olayı üretir |
| `fine-service` | 8085 | N-Katmanlı | Kafka `BorrowOverdueEvent` tüketir, otomatik ceza kayıtları üretir |
| `customer-service` | 8082 | N-Katmanlı | Üye lifecycle yönetimi, benzersiz e-posta/telefon kontrolleri |
| `review-service` | 8086 → 8083 | N-Katmanlı | Kitap yorum & puanlamaları (docker-compose port yönlendirmesi ile) |
| `config-server` | 8787 | Spring Cloud Config | Tüm servisler için merkezi konfigürasyon kaynağı |
| `discovery-server` | 8761 | Eureka | Servis kayıt/keşfi, gateway için hedef listesi sağlar |
| `gateway-server` | 8888 | Spring Cloud Gateway | API giriş noktası, yük dengeleme ve route/security |
| `kafka` & `kafka-ui` | 9092/9094 & 8079 | Event Streaming | Ceza süreci için mesajlaşma & gözlem UI |
| PostgreSQL DB'ler | 5432-5436 | Stateful | Her servis için ayrık veri katmanı (book/customer/borrow/fine/review) |

## Book-service — DDD + CQRS + Onion
Book-service domain mantığını merkezde tutar; application katmanı komut/sorgu handler’larını orkestre eder, persistence katmanı adapter’lar ile domain repository arayüzlerini gerçekleştirir. CQRS pattern’i `core/cqrs` paketindeki sözleşmeler ve komut/sorgu handler sınıflarıyla uygulanır; `application/*/command` ve `application/*/query` paketlerindeki handler’lar Domain → Repository → DTO dönüşümlerini üstlenir.

Bu yapı sayesinde:
- Domain entity’leri (ör. `Book`, `Author`, `BookCopy`) framework bağımlılıklarından bağımsız kalır.
- Komutlar (create/update/delete) ve sorgular (list/find/availability) farklı handler zincirlerinde yönetilir; okuma-yazma ölçeklenmesi kolaylaşır.
- Persistence adapter’ları JPA entity’leri ile domain model arasında dönüşüm yapar, repository sözleşmesi bozulmaz.

## Diğer Servislerde N-Katmanlı Mimari
Customer, Borrow, Fine ve Review servisleri REST controller → service → repository zinciriyle ilerler. Controller’lar HTTP sözleşmesini yönetirken, servis katmanı transaction ve iş kurallarını gerçekleştirir, repository katmanı persistence erişimini kapsüller. Örneğin `customer-service` modülünde `CustomerController`, tüm HTTP isteklerini `CustomerApplicationService` arabirimine iletir; `CustomerApplicationServiceImpl` sınıfı üyelik kurallarını çalıştırır ve `CustomerRepository` ile veriyi kalıcı hale getirir.

Bu yaklaşım, servislerin daha klasik CRUD ihtiyaçları için sade bir yapı sunarken, domain kurallarını servis katmanında merkezi olarak toplar.

## Kafka ile Ceza Süreci (Borrow → Fine)
Geciken iade senaryosunda Borrow-service, `BorrowOverdueEvent` nesnesini Kafka’ya yollar; Fine-service aynı binding’i tüketip otomatik ceza üretir. Borrow tarafında `BorrowOverdueEventPublisher` sınıfı `StreamBridge` aracılığıyla `borrowOverdue-out-0` binding’ine mesaj gönderirken, Fine tarafında `BorrowOverdueEventConsumer` bu binding’i dinler ve `BorrowOverdueFineProcessor` sınıfına yönlendirir. Processor, `FineRepository` ile idempotent kayıt açar ve `FineServiceProperties` üzerinden günlük gecikme ücretini hesaplar.

## Altyapı ve Konfigürasyon
`docker-compose.yml`, Kafka kümesini, Config Server’ı, Eureka’yı, Gateway’i ve tüm domain servislerini aynı ağ üzerinde ayağa kaldırır. Her servis Docker imajından çalışır, `SPRING_CONFIG_IMPORT` ile Config Server’a bağlanır, Gateway ve Discovery load balancing sağlar.

Bu yapı:
- Config Server’ı `./configurations` klasörüne bağlayarak tüm `application-*.yml` dosyalarını tek yerden yönetir.
- Gateway’in Eureka kayıtları üzerinden istekleri ilgili servislere yönlendirmesini sağlar; çoklu instance açıldığında otomatik yük dengeleme yapılır.
- Kafka ve Kafka UI kapsayıcılarını ceza akışı için devreye alır.
- Her servis için bağımsız PostgreSQL veritabanı kapsayıcıları başlatır (veri izolasyonu ve bağımsız ölçekleme).

## Docker ile Ayağa Kaldırma
1. Gerekli araçlar: Docker Desktop (20.10+), Docker Compose v2, 16 GB RAM önerilir.
2. Depoyu klonlayın ve kök dizine geçin:
   ```bash
   git clone https://github.com/BeytullahCetin/microservice-library-app.git
   cd microservice-library-app
   ```
3. Tüm ortamı başlatın:
   ```bash
   docker compose up --build
   ```
4. Servislerin hazır olması birkaç dakika sürebilir. Sırasıyla Config Server (8787), Discovery (8761), Gateway (8888) ve domain servisleri ayağa kalkacaktır.
5. Doğrulamalar:
   - Config Server: `http://localhost:8787/{application}/{profile}`
   - Eureka: `http://localhost:8761`
   - Gateway sağlığı: `http://localhost:8888/actuator/health`
   - Kafka UI: `http://localhost:8079`
6. Kapatmak için `Ctrl+C` ve ardından `docker compose down -v` komutunu kullanın.

## Lokal Geliştirme
Tüm ortamı Docker’da çalıştırmak yerine servisleri tek tek ayağa kaldırabilirsiniz:
1. Config Server’ı native profile ile başlatın:
   ```bash
   cd config-server
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=native
   ```
2. Discovery ve Gateway’i sırasıyla çalıştırın (`SPRING_PROFILES_ACTIVE=dev`).
3. Geliştirmek istediğiniz servise geçip profil seçerek çalıştırın:
   ```bash
   cd ../book-service
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
   ```
4. Testleri çalıştırmak için:
   ```bash
   ./mvnw clean verify
   ```
5. Servis konfigürasyonlarını `configurations/<service>/application-dev.yml` dosyalarından yönetin; değişiklik yaptığınızda ilgili servisi yeniden başlatmanız yeterli.

## İzleme ve Operasyon
- Her servis Spring Boot Actuator ile sağlık uç noktaları sunar (`/actuator/health`, `/actuator/info`).
- Kafka UI üzerinden `BorrowOverdueEvent` kuyruğunu gözlemleyebilir, başarısız mesajları yeniden işleyebilirsiniz.
- Gateway üzerinden tüm dış istekler geçtiği için rate limiting, security ve tracing kuralları tek noktadan uygulanabilir.
- Loglar docker-compose çıktısında merkezi olarak görülebilir; ihtiyaca göre `docker logs <service>` kullanılabilir.

## Geliştirici Notları
- Java 21 + Maven Wrapper ile geliyor; ek kurulum gerektirmeden `./mvnw` komutları kullanılabilir.
- Kodlama standartları: Book-service tarafında domain katmanı framework bağımlılığı içermiyor, diğer servislerde MapStruct/Lombok destekleri ile sade DTO dönüştürmeleri mevcut.
- Event şemaları Spring Cloud Stream ile yönetildiğinden Binder değişimi (ör. RabbitMQ) minimum değişiklikle yapılabilir.
- Yeni servis eklerken Config & Discovery & Gateway zincirine entegrasyon için mevcut docker-compose bloğunu örnek alabilirsiniz.
