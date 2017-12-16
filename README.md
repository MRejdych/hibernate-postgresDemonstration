# hibernate-postgresDemonstration

## Aplikacja ma na celu prezentację współpracy frameworka Hibernate oraz bazy danych PostgreSQL.

- [Uruchomienie maszyny wirtualnej.](#uruchomienie-maszyny-wirtualnej.)
- [Konfiguracja frameworka Hibernate z użyciem pliku persistence.xml w celu wykorzystania api JPA.](#Konfiguracja-frameworka-Hibernate-z-użyciem-pliku-persistence.xml-w-celu-wykorzystania-api-JPA)
- [Konfiguracja frameworka Hibernate z użyciem pliku hibernate.cfg.xml w celu wykorzystania api Hibernate.](#Konfiguracja-frameworka-Hibernate-z-użyciem-pliku-hibernate.cfg.xml-w-celu-wykorzystania-api-Hibernate)
- [Tworzenie klasy Entity.](#Tworzenie-klasy-reprezentującej-rekord-w-tabeli-bazy-danych.)
- [Mapowanie relacji typu wiele do jednego.](#Mapowanie-relacji-typu-wiele-do-jednego)
- [Dwustronna relacja wiele do jednego.](#Dwustronna-relacja-wiele-do-jednego)
- [Relacja jeden do jednego.](#Relacja-jeden-do-jednego)
- [Relacja wiele do wielu.](#Relacja-wiele-do-wielu)

### Uruchomienie maszyny wirtualnej.

[Link do pobrania maszyny wirtualnej.](https://drive.google.com/open?id=0B34zDl4oehXuTjY1V0RRQ3lRbW8)  

Login użytkownika maszyny wirtualnej: user  
Hasło: user  

Użytkownik postgreSQL: postgres  
Hasło: postgres  

Żródła projektu znajdują się na pulpicie w katalogu hibernate-postgresDemontration.  

![sources](https://github.com/MRejdych/hibernate-postgresDemonstration/blob/master/img/src.png)  

Na maszynie zainstalowano lekkie i poręczne IDE Visual Studio Code. To IDE nie jest najlepszym wyborem 
do pisania kodu w Javie, natomiast jest bardzo dobrze nadaje się do przeglądania istniejącego kodu.  

![ide](https://github.com/MRejdych/hibernate-postgresDemonstration/blob/master/img/vscode.png)  

W celu uruchomienia kontenera z przygotowaną bazą danych PostgreSql należy uruchomić skrypt 
createDemoDatabase.sh w katalogu postgres, który utworzy kontener z wykorzystaniem Docker'a.  
Jeżeli polecenie sudo docker ps pokaże aktywny kontener "postgres" ten krok można pominąć.  

![docker](https://github.com/MRejdych/hibernate-postgresDemonstration/blob/master/img/dockerps.png)  

Następnie w celu uruchomienia aplikacji w katalogu głównym projektu należy wywołać komendę ./gradlew run.  
  
Po poprawnym zainicjalizowaniu aplikacji automatycznie zostanie uruchomiona przeglądarka internetowa z otwartą 
stroną startową umożliwiającą interakcje z aplikacją. 

## Opis frameworka Hibernate i przykłady wykorzystania.

### Konfiguracja frameworka Hibernate z użyciem pliku persistence.xml w celu wykorzystania api JPA

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

### Konfiguracja frameworka Hibernate z użyciem pliku hibernate.cfg.xml w celu wykorzystania api Hibernate

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
