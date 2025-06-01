package Command.TextEditor;

public class TypeCommand implements Command{
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
