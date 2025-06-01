package Command.TextEditor;

public class TextEditor {
    public StringBuilder content = new StringBuilder();
    public void insertText(String text, int position){
        content.insert(position, text);
    }
    public void deleteText(int from, int to){
        content.delete(from, to);
    }
    public void applyFormat(int from, int to, String style){
        System.out.println("Applied " + style + " to: " + content.substring(from, to));
    }

    public String getContent() {
        return content.toString();
    }

    public String getTextFrom(int from, int to){
        return content.substring(from, to);
    }
}

