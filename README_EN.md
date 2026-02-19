# **DAM1 Practical Exam — Programming + Development Environments**

**Theme:** Sports championship / Olympics (console, Java 21)

---

## **Context**

The school organizes a **championship** (Olympics-style). There are **athletes** and **results** (positions) in different events.

You must build a **console** Java application to:

1. **Read** athletes and results from a file (**CSV recommended**)
2. Store the information in memory
3. Compute a **points ranking** and simple queries
4. **Write** the output to a result file

> Important: this repository
>
>
> **does not include implementations**
>

---

# **1) Development Environments Requirements (ED)**

## **1.1 GitHub (Fork + Project + Issues + PR)**

1. **FORK** this repository to your account.
2. Create a **GitHub Project** (board) in your fork with columns:
    - Pending (To do)
    - In progress
    - In review
    - Done
3. Create at least **3 Issues** (tasks) and add them to the Project.
4. Work using branches feature/*.
5. **Any change you want to integrate into main must go through a Pull Request (PR)** (direct merge is not allowed).
6. **Each PR must be approved by you** (self-review) before merging.
7. Each PR must link an Issue (e.g., Closes #3).

---

# **2) Maven Requirements (ED) — mandatory**

## **2.1 Java**

The project must compile with **Java 21**.

## **2.2 Required dependencies (add to**

## **pom.xml**

## **)**

You must add exactly these dependencies and versions:

- **Apache Commons CSV**
    - groupId: org.apache.commons
    - artifactId: commons-csv
    - version: 1.14.1
- **JUnit Jupiter (JUnit 5)**
    - groupId: org.junit.jupiter
    - artifactId: junit-jupiter
    - version: 5.10.2
    - scope: test
- **Mockito JUnit Jupiter**
    - groupId: org.mockito
    - artifactId: mockito-junit-jupiter
    - version: 5.12.0
    - scope: test

## **2.3 Plugins**

- The **maven-surefire-plugin** (tests) is **already configured** in this repository and **you must not modify it**.
- You must configure the **exec-maven-plugin** to run with mvn exec:java:
    - groupId: org.codehaus.mojo
    - artifactId: exec-maven-plugin
    - version: 3.6.3
    - <mainClass>es.fplumara.dam1.campeonato.app.Main</mainClass>

✅ These must work:

- mvn clean test
- mvn exec:java

---

# **3) Programming Requirements**

## **3.1 Layers and packages**

You must organize code using these packages:

- es.fplumara.dam1.campeonato.app
- es.fplumara.dam1.campeonato.model
- es.fplumara.dam1.campeonato.repository
- es.fplumara.dam1.campeonato.service
- es.fplumara.dam1.campeonato.io
- es.fplumara.dam1.campeonato.exception

---

## **3.2 Class diagram — Model**

```
classDiagram
direction TB

class Puntuable {
  <<interface>>
  +int getPuntos()
}

class TipoPrueba {
  <<enumeration>>
  CARRERA
  SALTO
  LANZAMIENTO
}

class Participante {
  <<abstract>>
  -String id
  -String nombre
  -String pais
  +String getId()
  +String getNombre()
  +String getPais()
}

class Deportista {
  +String getPais()
}

class Resultado {
  -String id
  -String idPrueba
  -TipoPrueba tipoPrueba
  -String idDeportista
  -int posicion
  +String getId()
  +String getIdPrueba()
  +TipoPrueba getTipoPrueba()
  +String getIdDeportista()
  +int getPosicion()
  +int getPuntos()
}

class LineaRanking {
  -String idDeportista
  -String nombre
  -String pais
  -int puntos
  +String getIdDeportista()
  +String getNombre()
  +String getPais()
  +int getPuntos()
}

Participante <|-- Deportista
Puntuable <|.. Resultado
Resultado --> TipoPrueba
```

**Scoring (simple)**

- Position 1 → 5 points
- Position 2 → 3 points
- Position 3 → 1 point
- Any other → 0 points

> This logic must be in Resultado.getPuntos() (using the Puntuable interface), and the event type is represented by the TipoPrueba enum.
>

---

## **3.3 Repositories — 2 repositories (non-generic)**

Repositories must store data in memory using an internal Map.

### **3.3.1 What the repository methods mean**

- save(...)
    - Saves the element in the repository (internally in a Map).
- findById(...) -> Optional<...>
    - Finds by id. If it does not exist, returns Optional.empty().
- listAll() -> List<...>
    - Returns a list with **all** elements.
- findByPais(String pais) -> List<Deportista> *(only in DeportistaRepository)*
    - Returns athletes from a given country (collection filtering).
    - Use of List is evaluated here (Streams are recommended).

> delete() is not required to keep the exam shorter.
>

### **3.3.2 Diagram — Repositories (Map + Optional + List)**

```
classDiagram
direction TB

class DeportistaRepository {
  <<interface>>
  +void save(Deportista d)
  +Optional~Deportista~ findById(String id)
  +List~Deportista~ listAll()
}

class ResultadoRepository {
  <<interface>>
  +void save(Resultado r)
  +Optional~Resultado~ findById(String id)
  +List~Resultado~ listAll()
  +boolean existsByPruebaYDeportista(String idPrueba, String idDeportista)
}

class DeportistaRepositoryImpl {
  -Map~String,Deportista~ datos
  +void save(Deportista d)
  +Optional~Deportista~ findById(String id)
  +List~Deportista~ listAll()
}

class ResultadoRepositoryImpl {
  -Map~String,Resultado~ datos
  +void save(Resultado r)
  +Optional~Resultado~ findById(String id)
  +List~Resultado~ listAll()
  +boolean existsByPruebaYDeportista(String idPrueba, String idDeportista)
}

DeportistaRepository <|.. DeportistaRepositoryImpl
ResultadoRepository <|.. ResultadoRepositoryImpl
```

---

## **3.4 Service — 1 service that uses 2 repositories**

### **3.4.1 Custom exceptions (mandatory)**

Create and use these exceptions in ...exception:

- DuplicadoException (when trying to save something with an existing id)
- NoEncontradoException (when referencing an id that does not exist)
- OperacionNoPermitidaException (when a championship rule prevents the operation)

### **3.4.2 Diagram — Service**

```
classDiagram
direction TB

class CampeonatoService {
  -DeportistaRepository deportistaRepo
  -ResultadoRepository resultadoRepo
  +void registrarDeportista(Deportista d)
  +void registrarResultado(Resultado r)
  +List~LineaRanking~ ranking()
  +List~Resultado~ resultadosDePais(String pais)
  +Set~String~ paisesParticipantes()
}

class DeportistaRepository {
  <<interface>>
  +void save(Deportista d)
  +Optional~Deportista~ findById(String id)
  +List~Deportista~ listAll()
  +List~Deportista~ findByPais(String pais)
}

class ResultadoRepository {
  <<interface>>
  +void save(Resultado r)
  +Optional~Resultado~ findById(String id)
  +List~Resultado~ listAll()
  +boolean existsByPruebaYDeportista(String idPrueba, String idDeportista)
}

CampeonatoService --> DeportistaRepository
CampeonatoService --> ResultadoRepository
```

### **3.4.3 Service rules (mandatory)**

Create CampeonatoService with these rules:

- registrarDeportista(Deportista d)
    - if d is null or id/name/country is null/blank → IllegalArgumentException
    - if an athlete with that id already exists → DuplicadoException
    - if OK → save
- registrarResultado(Resultado r)
    - if r is null or id/idPrueba/idDeportista is null/blank → IllegalArgumentException
    - if tipoPrueba is null → IllegalArgumentException
    - if posicion <= 0 → IllegalArgumentException
    - if a result with that id already exists → DuplicadoException
    - if the athlete does not exist → NoEncontradoException
    - championship rule: an athlete can have **only 1 result per event**
        - if resultadoRepo.existsByPruebaYDeportista(idPrueba, idDeportista) → OperacionNoPermitidaException
    - if OK → save
- ranking() -> List<LineaRanking>
    - sums points per athlete based on Resultado.getPuntos()
    - returns list ordered by points (descending)
    - collection filtering/transformation is evaluated here (Streams recommended)
- resultadosDePais(String pais) -> List<Resultado>
    - first gets athletes from that country using deportistaRepo.findByPais(pais)
    - then returns results whose idDeportista is in that list (collection filtering/transformation)
- paisesParticipantes() -> Set<String>
    - returns the set of countries (no duplicates) from registered athletes (**mandatory use of Set**)

**Mandatory data structures in the project**

- Map: in DeportistaRepositoryImpl and ResultadoRepositoryImpl (storage)
- List: in listAll() and in ranking/filtering
- Set: in paisesParticipantes()

---

## **3.5 File reading and writing (CSV recommended)**

You must implement reading and writing using **CSV** files (recommended) with Apache Commons CSV.

### **Input format**

### **deportistas.csv**

```
id,nombre,pais
D001,Ana,ES
D002,Bruno,PT
D003,Carla,ES
```

### **Input format**

### **resultados.csv**

```
id,idPrueba,tipoPrueba,idDeportista,posicion
R001,P001,CARRERA,D001,1
R002,P001,CARRERA,D002,2
R003,P002,SALTO,D003,3
```

### **Mandatory output**

### **ranking.csv**

You must write a ranking.csv file:

```
idDeportista,nombre,pais,puntos
D001,Ana,ES,5
D002,Bruno,PT,3
D003,Carla,ES,1
```

> You may create classes in ...io to read/write. The evaluation checks that the program correctly
>
>
> **reads**
>
> **writes**
>

---

# **4) Unit tests (JUnit + Mockito) — mandatory**

You must create unit tests for CampeonatoService using Mockito (mock repositories).

**You are not given a fixed list of tests.**

You must design the tests you consider necessary **covering all possible cases**:

- OK cases
- duplicates
- not found cases
- validations (null/blank, position <= 0)
- rule “1 result per event and athlete”
- ranking (sums and ordering)

Requirements:

- use @ExtendWith(MockitoExtension.class)
- repository mocks
- at least in OK cases, use verify(...)

---

# **5) Main program (minimum)**

In app.Main, demonstrate a simple flow (no complex menu), using fixed paths:

1. Create repositories (DeportistaRepositoryImpl, ResultadoRepositoryImpl)
2. Create CampeonatoService
3. Read deportistas.csv and register athletes (service)
4. Read resultados.csv and register results (service)
5. Print to console:
    - paisesParticipantes() (Set)
    - ranking() (sorted List)
    - resultadosDePais("ES") (filtered List)
6. Write ranking.csv to disk

---

## **Submission**

- Link to your fork (public or accessible)
- main must pass: mvn clean test
- Must run: mvn exec:java
- PRs and Project must reflect your work