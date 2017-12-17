# hibernate-postgresDemonstration

## Aplikacja ma na celu prezentację współpracy frameworka Hibernate oraz bazy danych PostgreSQL.

- [Uruchomienie maszyny wirtualnej.](#uruchomienie-maszyny-wirtualnej)
- [Konfiguracja frameworka Hibernate z użyciem pliku persistence.xml w celu wykorzystania api JPA.](#konfiguracja-frameworka-hibernate-z-użyciem-pliku-persistence-xml-w-celu-wykorzystania-api-jpa)
- [Konfiguracja frameworka Hibernate z użyciem pliku hibernate.cfg.xml w celu wykorzystania api Hibernate.](#konfiguracja-frameworka-hibernate-z-użyciem-pliku-hibernate-cfg-xml-w-celu-wykorzystania-api-hibernate)
- [Tworzenie klasy Entity.](#tworzenie-klasy-reprezentującej-rekord-w-tabeli-bazy-danych)
- [Mapowanie relacji typu wiele do jednego.](#mapowanie-relacji-typu-wiele-do-jednego)
- [Dwustronna relacja wiele do jednego.](#dwustronna-relacja-wiele-do-jednego)
- [Relacja jeden do jednego.](#relacja-jeden-do-jednego)
- [Relacja wiele do wielu.](#relacja-wiele-do-wielu)
- [Dodawanie obiektu Entity poprzez api JPA.](#zapis-obiektu-do-bazy-danych-poprzez-api-jpa)
- [Pobieranie obiektu Entity poprzez api JPA.](#wczytywanie-obiektu-z-bazy-danych-poprzez-api-jpa)
- [Aktualizowanie obiektu Entity poprzez api JPA.](#aktualizowanie-obiektu-entity-poprzez-api-jpa)
- [Usuwanie obiektu Entity poprzez api JPA.](#usuwanie-obiektu-entity-poprzez-api-jpa)
- [Wykonywanie operacji na bazie danych z użyciem języka JPQL.](#wykonywanie-operacji-na-bazie-danych-z-użyciem-języka-jpql)
- [Wykonywanie operacji na bazie danych z użyciem natywnego języka SQL.](#wykonywanie-operacji-na-bazie-danych-z-użyciem-natywnego-języka-sql)
- [Linki do dodatkowych tutoriali.](#linki-do-dodatkowych-tutoriali)


### Uruchomienie maszyny wirtualnej.

[Link do pobrania maszyny wirtualnej.](https://drive.google.com/file/d/1p-qOe4X9nEZTBtCIoQ8BsU3Ubrf5cu6C/view?usp=sharing)  

Login użytkownika maszyny wirtualnej: user  
Hasło: user  

Użytkownik postgreSQL: postgres  
Hasło: postgres  

Żródła projektu znajdują się na pulpicie w katalogu hibernate-postgresDemonstration.  

![sources](https://github.com/MRejdych/hibernate-postgresDemonstration/blob/master/img/src.png)  

Na maszynie zainstalowano lekkie i poręczne IDE Visual Studio Code. To IDE nie jest najlepszym wyborem 
do pisania kodu w Javie, natomiast bardzo dobrze nadaje się do przeglądania istniejącego kodu.  

![ide](https://github.com/MRejdych/hibernate-postgresDemonstration/blob/master/img/vscode.png)  

W celu uruchomienia kontenera z przygotowaną bazą danych PostgreSQL należy uruchomić skrypt 
createDemoDatabase.sh w katalogu postgres, który utworzy kontener z wykorzystaniem Docker'a.  
Jeżeli polecenie sudo docker ps pokaże aktywny kontener "postgres" ten krok można pominąć.  

![docker](https://github.com/MRejdych/hibernate-postgresDemonstration/blob/master/img/dockerps.png)  

Następnie w celu uruchomienia aplikacji w katalogu głównym projektu należy wywołać komendę ./gradlew run.  
  
Po poprawnym zainicjalizowaniu aplikacji automatycznie zostanie uruchomiona przeglądarka internetowa z otwartą 
stroną startową umożliwiającą interakcje z aplikacją.  

Zainstalowany klient bazy danych PostgreSQL służący do zarządzania i przeglądania danych to 
aplikacja działająca w terminalu o nazwie PSQL.  

W celu jej uruchomienia należy:  
Wywołać komendę sudo docker -it postgres bash w celu "wejścia" do kontenera z bazą danych.  
Wywołać komendę psql.  
Wywołać komendę \c demodb w celu przełączenia się na bazę danych "demodb".  

![[psql]](https://github.com/MRejdych/hibernate-postgresDemonstration/blob/master/img/psql.png)
 

## Opis frameworka Hibernate i przykłady wykorzystania.

### Konfiguracja frameworka Hibernate z użyciem pliku persistence xml w celu wykorzystania api JPA

[Dokumentacja pliku persistence.xml](https://docs.oracle.com/cd/E16439_01/doc.1013/e13981/cfgdepds005.htm)

```xml
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
             http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
             version="2.1">

    <persistence-unit name="entityManager">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <class>app.entities.Category</class>
        <class>app.entities.Customer</class>
        <class>app.entities.CustomerDemographic</class>

        <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQL94Dialect"/> <!-- DB Dialect -->
            <property name="hibernate.hbm2ddl.auto" value="validate"/> <!-- create / create-drop / update / validate -->

            <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/> <!-- DB Driver -->
            <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/demodb"/> <!-- BD Mane -->
            <property name="javax.persistence.jdbc.user" value="postgres"/> <!-- DB User -->
            <property name="javax.persistence.jdbc.password" value="postgres"/> <!-- DB Password -->
            <property name="hibernate.enable_lazy_load_no_trans" value="true"/>
        </properties>

    </persistence-unit>

</persistence>

```

### Konfiguracja frameworka Hibernate z użyciem pliku hibernate cfg xml w celu wykorzystania api Hibernate

[Dokumentacja pliku hibernate.cfg.xml](https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/session-configuration.html)

```xml
<hibernate-configuration>
    <session-factory>
        <property name="dialect">org.hibernate.dialect.PostgreSQL94Dialect</property> <!-- DB Dialect -->
        <property name="hbm2ddl.auto">validate</property> <!-- create / create-drop / update / validate -->

        <property name="connection.driver_class">org.postgresql.Driver</property> <!-- DB Driver -->
        <property name="connection.url">jdbc:postgresql://localhost:5432/demodb</property><!-- BD Mane -->
        <property name="connection.username">postgres</property> <!-- DB User -->
        <property name="connection.password">postgres</property> <!-- DB Password -->
        <property name="hibernate.enable_lazy_load_no_trans">true</property>
        <property name="connection.pool_size">5</property>
        <property name="transaction.factory_class">org.hibernate.transaction.JDBCTransactionFactory</property>

        <!-- mapping class using annotation -->
        <mapping class="app.entities.Category"/>
        <mapping class="app.entities.Customer"/>
        <mapping class="app.entities.CustomerDemographic"/>
    </session-factory>

</hibernate-configuration>

```

### Tworzenie klasy reprezentującej rekord w tabeli bazy danych.

```java
@Entity
@Table(name = "customers")
class Customer implements Serializable {
    protected Customer() {}
  
    public Customer(String companyName, String contactName) {
        this.companyName = companyName;
        this.contactName = contactName; 
    }
  
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @SequenceGenerator(name = "pk_sequence", sequenceName = "customer_id_seq", allocationSize = 1)
    @Column(name = "customer_id", nullable = false)
    private short customerId;
  
    @NotNull
    @Column(name = "company_name", nullable = false, length = 40)
    private String companyName;
  
    @Column(name = "contact_name", length = 30)
    private String contactName;
  
}
```

Adnotacja @Entity służy do poinformowania Hibernate, 
że przeznaczeniem klasy jest odzwierciedlanie danych znajdujących się w bazie danych.  

Adnotacja @Table służy do przemapowania nazwy klasy na nazwę tabeli w bazie danych. 
W przypadku braku parametru "name" framework wykorzystuje nazwę klasy jako nazwę tabeli.  

Istotną rzeczą wymaganą do poprawnego stworzenia klasy Entity jest zagwarantowanie 
istnienia domyślnego, bezargumentowego konstruktora oraz pola oznaczonego adnotacją @Id.  

Zastosowanie adnotacji @Column jest podobne do adnotacji @Table, 
z tą różnicą, że służy do mapowania pola klasy na kolumne tabeli. 

Zaprezentowana powyżej klasa zostanie przemapowana do tabeli reprezentowanej 
przez poniższy kod:

```sql
CREATE TABLE customers (
  customer_id   SMALLINT              NOT NULL,
  company_name  CHARACTER VARYING(40) NOT NULL,
  contact_name  CHARACTER VARYING(30)
);

```

### Mapowanie relacji typu wiele do jednego

Aby zmapować relację wiele do jednego pomiędzy tabelą customers a tabelą categories należałoby 
dodać do klasy Customers pole typu Category z adnotacjami @ManyToOne oraz @JoinColumn.    
"category_id" to nazwa kolumny klucza obcego w tabeli customers oraz nazwa klucza głównego 
w tabeli categories.  

```java
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    Category category;
```

### Dwustronna relacja wiele do jednego

Aby móc przechowywać referencje do zależnych obiektów type Customer w obiekcie klasy
Category należy dodać kolekcję z adnotacją @OneToMany.

```java
    @OneToMany(mappedBy = "customer", fetch = LAZY, cascade = ALL)
    List<Customer> customers = new ArrayList<>();
```
  
Parametr mappedBy informuje, że klucz obcy został już zdefioniowany przez klasę Customer.
  
Parametr fetch określa, czy kolekcja ma być pobrana w trybie natychmiastowym(EAGER) czy w trybie
leniwym (LAZY, domyślna wartość).
  
Parametr cascade określa, jakie operacje wykonane na obiekcie category mają być 
wykonywane kaskadowo na zależnych obiektach customer  
( ALL | ALL_DELETE_ORPHAN | DELETE | DELETE_ORPHAN | EVICT | LOCK | MERGE | NONE | 
PERSIST | REFRESH | REPLICATE | UPDATE )


### Relacja jeden do jednego

W klasie Customer:  

```java
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    Category category;
```

W klasie Category:  

```java
    @OneToOne(mappedBy = "customer")
    Customer customer;
```

### Relacja wiele do wielu

Relacja wiele do wielu pomiędzy tabelami customers oraz customer_demographics z tabelą łącznikową 
customer_customer_demographics:  


W klasie Customer:  

```java
    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinTable(
            name = "customer_customer_demographics",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_type_id")}
    )
    private List<CustomerDemographic> customerDemographics = new ArrayList<>();
```

W klasie CustomerDemographics:  

```java
    @ManyToMany(fetch = LAZY, cascade = {PERSIST, MERGE, DETACH, REFRESH})
    @JoinTable(
            name="customer_customer_demographics",
            joinColumns = {@JoinColumn(name = "customer_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    private List<Customer> customers = new ArrayList<>();
```

Parametr joinColumns wskazuje kolumne z kluczem głównym tabeli, natomiast parametr inverseJoinColumns
 wskazuje kolumne z kluczem obcym wskazującym na klucz główny przeciwległej tabeli.  
 
Tabela łącznikowa nie musi być mapowana przez klasę z adnotacją @Entity ponieważ framework 
Hibernate automatycznie utworzy lub wykorzysta tabele o nazwie przypisanej do parametru name.


### Uzyskiwanie obiektu EntityManager służącego do wykonywania operacji na obiektach Entity.

W celu utworzenia obiektu EntityManagerFactory należy wywołać następującą metodę:  
emf = Persistence.createEntityManagerFactory("entityManager");  
Jej argument to nazwa persistence-unit podane w pliku persistence.xml.  
Podczas działania aplikacji powinien zostać utworzony tylko jeden obiekt EntityManagerFactory.  
W celu uzyskania obiektu EntityManager należy wywołać metodę entityManagerFactory.getEntityManager().  

[Dokumentacja interfejsu EntityManager](https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManager.html)  
[Dokumentacja interfejsu EntityManagerFactory](https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManagerFactory.html)


```java
public class DatabaseUtils {
    private static EntityManagerFactory emf;
    private EntityManager em;

    public DatabaseUtils() {
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("entityManager");
        }
    }
    
    public EntityManager getEntityManager() {
        if (emf != null){
            return emf.createEntityManager();
        }
        else return null;
    }
    
    public EntityManager getEntityManager(){
        if(emf != null){
            return emf.createEntityManager();
        } else return null;
    }
}
```

### Rozpoczynanie transakcji.

W celu rozpoczęcia transakcji należy wywołać metodę obiektu klasy EntityManager o nazwie getTransaction(),  
następnie na uzyskanym obiekcie należy wywołać metodę begin().  

```java
public class DatabaseUtils {
    private static EntityManagerFactory emf;
    private EntityManager em;

    public DatabaseUtils() {
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("entityManager");
        }
    }
    
    public void openConnection() {
        if (emf != null) {
            em = emf.createEntityManager();
            em.getTransaction().begin();
        } 
    }
}
```


### Zatwierdzanie transakcji oraz zamknięcie obiektu EntityManager.

Po zakończeniu operacji należy wywołać metodę obiektu transakcji "commit()". Jeżeli planowane są 
kolejne operacje na bazie danych należy rozpocząć następną transakcje, w przeciwnym wypadku należy 
zamknąć obiekt typu EntityManager wywołując na nim metodę close().  


```java
public class DatabaseUtils {
    private static EntityManagerFactory emf;
    private EntityManager em;

    public DatabaseUtils() {
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("entityManager");
        }
    }
    
    public void openConnection() {
            if (emf != null) {
                em = emf.createEntityManager();
                em.getTransaction().begin();
            }
        }
        
    public void closeConnection() {
        if (connOpened) {
            em.getTransaction().commit();
            em.close();
        }
    }
}
```

### Zapis obiektu do bazy danych poprzez api JPA.

W celu zapisu obiektu Entity do bazy danych wystarczy wywołać metodę obiektu klasy EntityManager o 
nazwie "persist(Object object)" podając jako jej argument obiekt, który chcemy zapisać.  
Przed wywołaniem metody należy otworzyć transakcję, po zakończeniu wszystkich operacji należy ją 
zatwierdzić i zamknąć obiekt EntityManager.  


```java
DatabaseUtils dbutils = new DatabaseUtils();
EntityManager em = dbutils.getEntityManager();

em.getTransaction().begin();

Customer customer = new Customer("Company name", "Contact name");

em.persist(customer);

em.getTransaction().commit();
em.close();
```

### Wczytywanie obiektu z bazy danych poprzez api JPA.

W celu pobrania obiektu Entity o określonym id z bazy danych wystarczy wywołać metodę obiektu klasy EntityManager o 
nazwie "find(Class<T> entityClass, Object entityId)" podając jako jej argumenty klasę obiektu oraz jego id.    
Przed wywołaniem metody należy otworzyć transakcję, po zakończeniu wszystkich operacji należy ją 
zatwierdzić i zamknąć obiekt EntityManager.  

```java
DatabaseUtils dbutils = new DatabaseUtils();
EntityManager em = dbutils.getEntityManager();

em.getTransaction().begin();

Short customerId = 1;
Customer customer = em.find(Customer.class, customerId);

em.getTransaction().commit();
em.close();
```

### Aktualizowanie obiektu Entity poprzez api JPA.

W celu zapisania zaktualizowanego obiektu Entity do bazy danych wystarczy zmienić stan obiektu 
Entity i  po zakończeniu wszystkich operacji zatwierdzić transakcję i zamknąć obiekt EntityManager.  
Hibernate automatycznie wykryje zmiany i zapisze nowy stan obiektu w dogodnym dla siebie momencie.  

```java
DatabaseUtils dbutils = new DatabaseUtils();
EntityManager em = dbutils.getEntityManager();

em.getTransaction().begin();

Short customerId = 1;
Customer customer = em.find(Customer.class, customerId);
customer.setCompanyName("Different company name");

em.getTransaction().commit();
em.close();
```

### Usuwanie obiektu Entity poprzez api JPA.

W celu usunięcia obiektu Entity z bazy danych wystarczy wywołać metodę obiektu klasy EntityManager o 
nazwie "remove(Object entity)" podając jako jej argument obiekt, który chcemy usunąć.    
Przed wywołaniem metody należy otworzyć transakcję, po zakończeniu wszystkich operacji należy ją 
zatwierdzić i zamknąć obiekt EntityManager.  

```java
DatabaseUtils dbutils = new DatabaseUtils();
EntityManager em = dbutils.getEntityManager();

em.getTransaction().begin();

Short customerId = 1;
Customer customer = em.find(Customer.class, customerId);
em.remove(customer);

em.getTransaction().commit();
em.close();
```

### Wykonywanie operacji na bazie danych z użyciem języka JPQL.

[Dokumentacja języka JPQL](https://docs.oracle.com/html/E13946_01/ejb3_langref.html)  
[Dokumentacja interfejsu TypedQuery<X>](https://docs.oracle.com/javaee/7/api/javax/persistence/TypedQuery.html)  

W celu wykonania zapytania w języku JPQL należy uzyskać obiekt typu TypedQuery wywołując metodę obiektu 
EntityManager "createQuery(String query)" podając jako argument pożądane zapytanie.  
Następnie należy wywołać na uzyskanym obiekcie jedną z metod interfejsu TypedQuery w zależności 
od rodzaju zapytania.  

```java
DatabaseUtils dbutils = new DatabaseUtils();
EntityManager em = dbutils.getEntityManager();

em.getTransaction().begin();

TypedQuery<Customer> jpqlQuery = em.createQuery("SELECT c FROM Customer c ORDER BY c.customerId", Customer.class);
List<Customer> customersList = jpqlQuery.getResultList();

em.getTransaction().commit();
em.close();
```

### Wykonywanie operacji na bazie danych z użyciem natywnego języka SQL.

[Dokumentacja PostgreSQL](https://www.postgresql.org/docs/9.5/static/index.html)  
[Dokumentacja interfejsu Query](https://docs.oracle.com/javaee/7/api/javax/persistence/Query.html)  

W celu wykonania natywnego zapytania SQL należy uzyskać obiekt typu Query wywołując metodę obiektu 
EntityManager "createNativeQuery(String query)" podając jako argument pożądane zapytanie.  
Następnie należy wywołać na uzyskanym obiekcie jedną z metod interfejsu Query w zależności 
od rodzaju zapytania.  

```java
DatabaseUtils dbutils = new DatabaseUtils();
EntityManager em = dbutils.getEntityManager();

em.getTransaction().begin();

Query sqlQuery = em.createNativeQuery("Select * From customers ORDER BY customer_id", Customer.class);

List<Customer> customersList = (List<Customer>) sqlQuery.getResultList();

em.getTransaction().commit();
em.close();
```

### Linki do dodatkowych tutoriali.
[Docker](http://training.play-with-docker.com/)  
[Spring MVC](https://spring.io/guides/gs/serving-web-content/)  
[Spring Boot](https://spring.io/guides/gs/spring-boot/)  
[Thymeleaf](http://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html)  
[Github flavored Markdown cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)  