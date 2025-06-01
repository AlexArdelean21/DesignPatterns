# Command Pattern - Text Editor

Avem o clasă numită `TextEditor`, care acționează ca **Receiver** și conține metodele reale de lucru cu textul: **inserare**, **ștergere** și **formatare**.

Pentru fiecare operație (ex: tastare, ștergere, formatare), creăm o **clasă separată** care implementează o interfață comună numită `Command`, ce definește metodele `execute()` și `undo()`.

În metoda `execute()` din fiecare clasă concretă, apelăm funcțiile reale din `TextEditor`.

În plus, creăm o clasă **Invoker** (de exemplu `EditorInvoker`) care reține o listă de comenzi deja executate sau anulate, și care oferă funcționalități de `undo()` și `redo()`.

Comenzile sunt stocate sub forma interfeței `Command`, ceea ce permite `Invoker`-ului să le trateze într-un mod generic, indiferent de tipul comenzii.

---

## 🧩 Clasificare simplificată

- **TextEditor** → Receiver
- **Command** → Interfață comună cu `execute()` și `undo()`
- **TypeCommand**, **DeleteCommand**, **FormatCommand** → Comenzi concrete
- **EditorInvoker** → Clasa care execută și controlează comenzile (undo/redo)
- **Main** → Clientul care creează tot și lansează comenzile

---

## 📐 UML Diagram

```text
+-------------------+           +----------------------+           +------------------+
|     Command       |<----------|   TypeCommand        |<----------|   Main           |
|-------------------|           |----------------------|           |------------------|
| +execute()        |           | +execute()           |           | +main()          |
| +undo()           |           | +undo()              |           +------------------+
+-------------------+           +----------------------+
        ^                                    ^
        |                                    |
        |                        +------------------------+
        |                        |   DeleteCommand        |
        |                        +------------------------+
        |                        | +execute()             |
        |                        | +undo()                |
        |                        +------------------------+
        |                                    ^
        |                        +------------------------+
        |                        |   FormatCommand        |
        |                        +------------------------+
        |                        | +execute()             |
        |                        | +undo()                |
        |                        +------------------------+
        |
+-------------------+
|  EditorInvoker    |
|-------------------|
| +executeCommand() |
| +undo()           |
| +redo()           |
+-------------------+
        |
        v
+-------------------+
|   TextEditor      |
|-------------------|
| +insertText()     |
| +deleteText()     |
| +applyFormat()    |
+-------------------+
```

---

## 📄 Snippet: Receiver (`TextEditor`)

```java
public class TextEditor {
    private StringBuilder content = new StringBuilder();

    public void insertText(String text, int position) {
        content.insert(position, text);
    }

    public void deleteText(int from, int to) {
        content.delete(from, to);
    }

    public void applyFormat(int from, int to, String style) {
        System.out.println("Applied " + style + " to: " + content.substring(from, to));
    }

    public String getContent() {
        return content.toString();
    }

    public String getTextRange(int from, int to) {
        return content.substring(from, to);
    }
}
```

---

## 📄 Snippet: Invoker (`EditorInvoker`)

```java
public class EditorInvoker {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command last = undoStack.pop();
            last.undo();
            redoStack.push(last);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            undoStack.push(cmd);
        }
    }
}
```

---

## 📄 Snippet: ConcreteCommand (`TypeCommand`)

```java
public class TypeCommand implements Command {
    private TextEditor editor;
    private String text;
    private int position;

    public TypeCommand(TextEditor editor, String text, int position) {
        this.editor = editor;
        this.text = text;
        this.position = position;
    }

    @Override
    public void execute() {
        editor.insertText(text, position);
    }

    @Override
    public void undo() {
        editor.deleteText(position, position + text.length());
    }
}
```

---

## 🔁 Exemplu simplificat de apel:

```java
EditorInvoker invoker = new EditorInvoker();
TextEditor editor = new TextEditor();

invoker.executeCommand(new TypeCommand(editor, "Salut", 0));
invoker.undo();
invoker.redo();
```
