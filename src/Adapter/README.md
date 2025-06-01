# Adapter Pattern - Client Data Reader

Avem două surse diferite de date pentru clienți: una furnizează fișiere **XML**, cealaltă furnizează date în format **JSON** (simulând un API). Scopul este să oferim un mod **unificat** de a le citi, folosind **Adapter Design Pattern**.

Fiecare sursă (XML/JSON) are o clasă proprie (`XMLReader`, `JSONReader`) care oferă metode specifice. Pentru a le adapta la o interfață comună (`ClientDataReader`), creăm adaptoare (`XMLAdapter`, `JSONAdapter`).

---

## 🧩 Clasificare simplificată

* **ClientDataReader** → Interfața comună (Target)
* **XMLReader**, **JSONReader** → Clase existente (Adaptee)
* **XMLAdapter**, **JSONAdapter** → Adaptoare (Adapter)
* **Main** → Clientul care folosește interfața comună

---

## 📐 UML Diagram

```text
+-----------------------+
|  ClientDataReader     |
|-----------------------|
| +readClientData()     |
+-----------------------+
          ^
          |
  +------------------+     +------------------+
  |   XMLAdapter     |     |  JSONAdapter     |
  |------------------|     |------------------|
  | +readClientData()|     | +readClientData()|
  +------------------+     +------------------+
         ^                        ^
         |                        |
+----------------+     +----------------+
|   XMLReader     |     |   JSONReader   |
|----------------|     |----------------|
| +parseXML()    |     | +parseJSON()   |
+----------------+     +----------------+
          
                +-----------+
                |   Main    |
                +-----------+
```

---

## 📄 Snippet: Interfață comună

```java
public interface ClientDataReader {
    void readClientData();
}
```

---

## 📄 Snippet: XMLReader (Adaptee)

```java
public class XMLReader {
    public void parseXML(String path) {
        System.out.println("Reading data from XML file at: " + path);
    }
}
```

---

## 📄 Snippet: JSONReader (Adaptee)

```java
public class JSONReader {
    public void parseJSON(String jsonString) {
        System.out.println("Parsing JSON data: " + jsonString);
    }
}
```

---

## 📄 Snippet: XMLAdapter

```java
public class XMLAdapter implements ClientDataReader {
    private XMLReader reader;
    private String path;

    public XMLAdapter(XMLReader reader, String path) {
        this.reader = reader;
        this.path = path;
    }

    public void readClientData() {
        reader.parseXML(path);
    }
}
```

---

## 📄 Snippet: JSONAdapter

```java
public class JSONAdapter implements ClientDataReader {
    private JSONReader reader;
    private String jsonData;

    public JSONAdapter(JSONReader reader, String jsonData) {
        this.reader = reader;
        this.jsonData = jsonData;
    }

    public void readClientData() {
        reader.parseJSON(jsonData);
    }
}
```

---

## 🔁 Exemplu simplificat de apel:

```java
public class Main {
    public static void main(String[] args) {
        ClientDataReader xmlAdapter = new XMLAdapter(new XMLReader(), "clients.xml");
        ClientDataReader jsonAdapter = new JSONAdapter(new JSONReader(),
            "[{\"name\":\"Alex\", \"email\":\"alex@example.com\"}]");

        xmlAdapter.readClientData();
        jsonAdapter.readClientData();
    }
}
```
