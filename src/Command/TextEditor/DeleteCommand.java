package Command.TextEditor;

public class DeleteCommand implements Command{
    private TextEditor editor;
    private int from, to;
    private String deletedText;

    public DeleteCommand(TextEditor editor, int from, int to) {
        this.editor = editor;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        deletedText = editor.getTextFrom(from, to);
        editor.deleteText(from, to);
    }

    @Override
    public void undo() {
        editor.insertText(deletedText, from);
    }
}
