## Aplikacja ma na celu prezentację współpracy frameworka Hibernate oraz bazy danych PostgreSQL.

W celu uruchomienia aplikacji należy uruchomić skrypt runApplication.sh.

## Przykłady wykorzystanie frameworka Hibernate:

### Tworzenie klasy, której obiekt reprezentuje rekord w tabeli bazy danych:

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
że przeznaczeniem klasy jest prezentowanie danych znajdujących się w bazie danych.  

Adnotacja @Table służy do przemapowania nazwy klasy na nazwę tabeli w bazie danych. 
W przypadku braku parametru "name" framework wykorzystuje nazwę klasy jako nazwę tabeli.  

Istotną rzeczą wymaganą do poprawnego stworzenia klasy Entity jest zagwarantowanie 
istnienia domyślnego,bezargumentowego konstruktora oraz pola oznaczonego adnotacją @Id.  

Zastosowanie adnotacji @Column jest podobne do adnotacji @Table, 
z tą różnicą, że służy do mapowania pola klasy na kolumne tabeli. 

Zaprezentowana powyżej klasa zostanie przemapowana do tabeli reprezentowaej 
przez poniższy kod:

```postgres-sql
CREATE TABLE customers (
  customer_id   SMALLINT              NOT NULL,
  company_name  CHARACTER VARYING(40) NOT NULL,
  contact_name  CHARACTER VARYING(30)
);

````