# Book Service - DDD & Onion Architecture

Bu servis Domain-Driven Design (DDD) ve Onion Architecture prensiplerine uygun olarak geliştirilmiştir.

## 📐 Mimari Yapı

### Onion Architecture Katmanları

```
┌─────────────────────────────────────────────┐
│         Presentation Layer (API)             │
│              Controllers                     │
├─────────────────────────────────────────────┤
│        Application Layer                     │
│   DTOs & Application Services                │
├─────────────────────────────────────────────┤
│          Domain Layer (Core)                 │
│   Entities, Value Objects, Repositories      │
├─────────────────────────────────────────────┤
│      Infrastructure Layer                    │
│   JPA Entities, Repository Adapters          │
└─────────────────────────────────────────────┘
```

## 📁 Proje Yapısı

### 1. Domain Layer (İç Katman - Core)
**Paket:** `com.turkcell.book_service.domain`

#### Entities (Domain Modelleri)
- `Book` - Kitap aggregate root
- `Author` - Yazar entity
- `Publisher` - Yayınevi entity
- `Language` - Dil entity
- `Translator` - Çevirmen entity
- `BookCopy` - Kitap kopyası entity

**Özellikler:**
- Rich domain model: Business logic entity'lerde
- Validasyon kuralları domain içinde
- Framework bağımlılığı yok (saf Java)

#### Value Objects
- `Isbn` - ISBN validasyonu ile immutable value object
- `Email` - Email validasyonu
- `Money` - Para işlemleri için value object

#### Repository Interfaces (Ports)
- `BookRepository`
- `AuthorRepository`
- `PublisherRepository`
- `LanguageRepository`
- `TranslatorRepository`
- `BookCopyRepository`

**Not:** Repository'ler sadece interface olarak domain layer'da tanımlıdır. Implementasyon infrastructure layer'dadır (Dependency Inversion Principle).

---

### 2. Application Layer
**Paket:** `com.turkcell.book_service.application`

#### DTOs (Data Transfer Objects)
**Request DTOs:**
- `CreateBookRequest` - Yeni kitap oluşturma
- `UpdateBookRequest` - Kitap güncelleme
- `CreateBookCopyRequest` - Yeni kopya ekleme

**Response DTOs:**
- `BookResponse` - Kitap bilgisi yanıtı
- `BookCopyResponse` - Kopya bilgisi yanıtı

#### Application Services
- `BookApplicationService` - Use case'leri orkestra eder
  - CRUD operasyonları
  - Kitap arama işlemleri
  - Kopya yönetimi
  - Transaction yönetimi (@Transactional)

**Sorumluluklar:**
- Domain entity'lerini koordine eder
- DTO ↔ Domain entity dönüşümü
- Transaction yönetimi
- Business workflow'ları yönetir

---

### 3. Infrastructure Layer
**Paket:** `com.turkcell.book_service.infrastructure.persistence`

#### JPA Entities
- `JpaBookEntity`
- `JpaAuthorEntity`
- `JpaPublisherEntity`
- `JpaLanguageEntity`
- `JpaTranslatorEntity`
- `JpaBookCopyEntity`

**Özellikler:**
- Database mapping annotations (@Entity, @Table, vb.)
- Bidirectional relationships
- Domain entity conversion methods

#### Spring Data JPA Repositories
- `JpaBookRepository`
- `JpaAuthorRepository`
- `JpaPublisherRepository`
- `JpaLanguageRepository`
- `JpaTranslatorRepository`
- `JpaBookCopyRepository`

#### Repository Adapters (Ports Implementation)
- `BookRepositoryAdapter`
- `AuthorRepositoryAdapter`
- `PublisherRepositoryAdapter`
- `LanguageRepositoryAdapter`
- `TranslatorRepositoryAdapter`
- `BookCopyRepositoryAdapter`

**Sorumluluklar:**
- Domain repository interface'lerini implemente eder
- JPA Entity ↔ Domain Entity dönüşümü
- Database persistence yönetimi
- Managed entity handling

---

### 4. Presentation Layer (API)
**Paket:** `com.turkcell.book_service.controller`

#### REST Controllers
- `BookController` - Kitap API endpoint'leri
  - POST `/api/v1/books` - Kitap oluştur
  - GET `/api/v1/books` - Tüm kitapları listele
  - GET `/api/v1/books/{id}` - ID ile kitap getir
  - GET `/api/v1/books/isbn/{isbn}` - ISBN ile kitap getir
  - PUT `/api/v1/books/{id}` - Kitap güncelle
  - DELETE `/api/v1/books/{id}` - Kitap sil
  - GET `/api/v1/books/search?title=...` - Başlıkla ara
  - GET `/api/v1/books/author/{authorId}` - Yazara göre filtrele
  - GET `/api/v1/books/publisher/{publisherId}` - Yayınevine göre
  - GET `/api/v1/books/language/{languageId}` - Dile göre
  - GET `/api/v1/books/available` - Müsait kitaplar

- `BookCopyController` - Kitap kopyası endpoint'leri
  - POST `/api/v1/book-copies` - Kopya oluştur
  - GET `/api/v1/book-copies/book/{bookId}` - Kitabın kopyaları
  - GET `/api/v1/book-copies/barcode/{barcode}` - Barkod ile getir
  - POST `/api/v1/book-copies/{barcode}/borrow` - Ödünç al
  - POST `/api/v1/book-copies/{barcode}/return` - İade et

#### Exception Handling
- `GlobalExceptionHandler` - Merkezi exception yönetimi
  - Validation errors
  - Business rule violations
  - Not found errors

---

## 🔑 Temel Prensipler

### 1. Dependency Rule (Bağımlılık Kuralı)
- **İç katmanlar dış katmanları bilmez**
- Domain layer hiçbir framework'e bağımlı değil
- Bağımlılıklar her zaman içe doğru (Dependency Inversion)

### 2. Domain-Driven Design
- **Rich Domain Model**: Business logic entity'lerde
- **Ubiquitous Language**: Domain terminolojisi kod içinde
- **Aggregate Root**: Book aggregate root'tur
- **Value Objects**: Immutable, validasyonlu değer nesneleri

### 3. Clean Architecture
- **Separation of Concerns**: Her katmanın kendi sorumluluğu
- **Testability**: Domain logic framework'ten bağımsız test edilebilir
- **Flexibility**: Infrastructure değişiklikleri domain'i etkilemez

### 4. Repository Pattern
- Domain repository interface'leri (ports)
- Infrastructure adapter implementasyonları
- Domain ve persistence katmanları ayrı

---

## 🏗️ Entity İlişkileri

### Book (Aggregate Root)
- **Publisher**: Many-to-One
- **Language**: Many-to-One
- **Authors**: Many-to-Many
- **Translators**: Many-to-Many
- **BookCopies**: One-to-Many

### BookCopy
- **Book**: Many-to-One
- **Status**: Enum (AVAILABLE, BORROWED, RESERVED, LOST, DAMAGED)

---

## 🔄 Veri Akışı

### Create Book Flow:
```
1. Controller receives CreateBookRequest (DTO)
2. Application Service validates and processes
3. Fetches related entities (Publisher, Language, Authors)
4. Creates Domain Book entity
5. Repository Adapter converts to JPA entity
6. Saves to database via Spring Data JPA
7. Converts back to Domain entity
8. Application Service converts to BookResponse
9. Controller returns response
```

### Circular Dependency Prevention:
- JPA Entity conversion methods circular referansları önlemek için optimize edildi
- Book → BookCopy ilişkisinde sadece gerekli alanlar yüklenir
- Repository Adapter'lar managed entity'leri kullanır

---

## 🛠️ Teknolojiler

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **Spring Cloud Config**
- **Spring Cloud Netflix Eureka**
- **Jakarta Validation**
- **H2 Database** (Development)
- **Maven**

---

## 📝 Best Practices

1. **Domain Entity Validations**: Domain entity'ler kendi validasyonlarını yapar
2. **DTO Validations**: API katmanında Jakarta Validation ile
3. **Transaction Management**: Application Service katmanında
4. **Exception Handling**: Global exception handler ile merkezi
5. **Immutable Value Objects**: Value object'ler immutable
6. **Factory Methods**: DTO → Domain conversions için static factory methods
7. **No Circular Dependencies**: Entity conversion'larda dikkatli handling

---

## 🚀 Çalıştırma

```bash
# Derleme
./mvnw clean compile

# Test
./mvnw test

# Çalıştırma
./mvnw spring-boot:run

# Package
./mvnw clean package
```

---

## 📊 Database Schema

H2 Console: `http://localhost:8080/h2-console` (dev profile aktif olduğunda)

### Tables:
- `books`
- `authors`
- `publishers`
- `languages`
- `translators`
- `book_copies`
- `book_authors` (join table)
- `book_translators` (join table)

---

## 🎯 Sonraki Adımlar

1. ✅ Integration testleri ekle
2. ✅ API documentation (Swagger/OpenAPI)
3. ✅ Database migration (Flyway/Liquibase)
4. ✅ Caching layer (Redis)
5. ✅ Event-driven communication
6. ✅ CQRS pattern implementation

---

**Proje Durumu:** ✅ Başarıyla derlendi ve Onion Architecture + DDD prensiplerine uygun şekilde yapılandırıldı.

