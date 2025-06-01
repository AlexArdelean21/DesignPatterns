# Composite Pattern - File System Tree

Avem o interfață `FileSystemComponent`, care definește o structură unificatoare pentru componente individuale (**fișiere**) și compuse (**foldere**).

Clasele `File` și `Folder` implementează această interfață, dar doar `Folder` permite adăugarea altor elemente (comportament de compozit). Astfel, putem crea structuri recursive care reprezintă arborescențe.

---

## 🧹 Clasificare simplificată

* **FileSystemComponent** → interfață comună pentru orice obiect
* **File** → frunză (nu conține altele)
* **Folder** → compozit (conține altele)
* **Main** → clientul care construiește arborele

---

## 📊 UML Diagram

```text
+----------------------------+
| FileSystemComponent        |
|----------------------------|
| +display()                 |
+----------------------------+
        ^            ^
        |            |
+---------------+ +------------------+
| Folder        | | File             |
|---------------| |------------------|
| - name        | | - name           |
| - children    | | +display()       |
| +add(...)     | +------------------+
| +display()    |
+---------------+
```

---

## 📄 Snippet: Component (`FileSystemComponent`)

```java
public interface FileSystemComponent {
    void display();
}
```

---

## 📄 Snippet: Frunză (`File`)

```java
public class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("File: " + name);
    }
}
```

---

## 📄 Snippet: Compozit (`Folder`)

```java
public class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    @Override
    public void display() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent component : children) {
            component.display();
        }
    }
}
```

---

## 🔄 Exemplu simplificat de apel:

```java
public class Main {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder docs = new Folder("docs");
        Folder images = new Folder("images");

        File file1 = new File("cv.pdf");
        File file2 = new File("cat.jpg");
        File file3 = new File("notes.txt");

        docs.add(file1);
        images.add(file2);
        root.add(docs);
        root.add(images);
        root.add(file3);

        root.display();
    }
}
```

---

## ✅ Observații:

* Arborele este compus recursiv prin obiecte `Folder` care conțin `File` sau alte `Folder`.
* Interfața comună permite tratarea unitară – nu contează dacă afisezi un folder sau un fișier.
* Se poate extinde ușor și folosi pentru structuri ierarhice reale: meniuri, categorii de produse, DOM HTML etc.
