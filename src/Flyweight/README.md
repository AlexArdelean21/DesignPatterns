# Flyweight Pattern - Model Trees

Avem o aplicație care simulează o pădure, iar fiecare copac afișat pe ecran are caracteristici comune (tip, culoare, textură), dar și unele unice (poziție x/y). Pentru a economisi memorie, folosim **Flyweight Pattern** pentru a partaja obiectele care au starea comună.

Un obiect `TreeType` reprezintă starea **intrinsecă** (partajabilă) a unui copac, iar poziția în pădure este starea **extrinsecă** (dependentă de context).

Clasa `TreeFactory` se ocupă cu gestionarea instanțelor existente de tipuri de copaci, evitând duplicarea lor.

---

## 🧩 Clasificare simplificată

* **TreeType** → Flyweight (stare partajată: tip, culoare, textură)
* **Tree** → Context (stare unică: x, y)
* **TreeFactory** → Gestionarea instanțelor Flyweight
* **Main** → Clientul care generează pădurea

---

## 📐 UML Diagram

```text
+--------------------+
|     TreeType       |
|--------------------|
| - name             |
| - color            |
| - texture          |
|--------------------|
| +draw(x, y)        |
+--------------------+
           ^
           |
+--------------------+
| ConcreteTreeType   |
+--------------------+

+--------------------+
|     Tree           |
|--------------------|
| - x, y             |
| - type: TreeType   |
|--------------------|
| +draw()            |
+--------------------+

+--------------------+
|   TreeFactory      |
|--------------------|
| - treeTypes        |
|--------------------|
| +getTreeType(...)  |
+--------------------+

+--------------------+
|       Main         |
|--------------------|
| +main()            |
+--------------------+
```

---

## 📄 Snippet: Flyweight (`TreeType`)

```java
public class TreeType {
    private String name;
    private Color color;
    private String texture;

    public TreeType(String name, Color color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(color);
        g.drawString(name, x, y);
    }
}
```

---

## 📄 Snippet: Factory (`TreeFactory`)

```java
public class TreeFactory {
    private static List<TreeType> treeTypes = new ArrayList<>();

    public static TreeType getTreeType(String name, Color color, String texture) {
        for (TreeType type : treeTypes) {
            if (type.name.equals(name) && type.color.equals(color) && type.texture.equals(texture)) {
                return type;
            }
        }
        TreeType type = new TreeType(name, color, texture);
        treeTypes.add(type);
        return type;
    }
}
```

---

## 📄 Snippet: Context (`Tree`)

```java
public class Tree {
    private int x;
    private int y;
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Graphics g) {
        type.draw(g, x, y);
    }
}
```

---

## 📄 Snippet: Client (`Main`)

```java
public class Main {
    public static void main(String[] args) {
        Flyweight.Forest forest = new Flyweight.Forest();
        for (int i = 0; i < 1000; i++) {
            forest.plantTree(random(0, 500), random(0, 500), "Stejar", Color.GREEN, "leafy texture");
        }
        forest.draw(new Graphics());
    }
}
```

---

## 📄 Snippet: Helper (`Flyweight.Forest`)

```java
public class Flyweight.Forest {
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, Color color, String texture) {
        TreeType type = TreeFactory.getTreeType(name, color, texture);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    public void draw(Graphics g) {
        for (Tree tree : trees) {
            tree.draw(g);
        }
    }
}
```

---

## 🔁 Avantaje

* Economie mare de memorie (doar o instanță per tip de obiect partajat).
* Separare clară între starea comună și cea unică.
* Îmbunătățire performanță la scară mare (ex: milioane de obiecte).

---

## ❗ Observații

* Starea extrinsecă trebuie să fie furnizată de client la fiecare apel (nu poate fi stocată în flyweight).
* Flyweight se folosește în aplicații unde avem multe instanțe de obiecte similare.

---

## 🧠 Exemple reale

* Font rendering în editoare de text (fiecare caracter e un flyweight cu format partajat)
* Obiecte în jocuri (ex: soldați, copaci, gloanțe)
* Sisteme de cache
* Reprezentare grafică a hărților mari
