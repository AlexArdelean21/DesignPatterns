package Command.TextEditor;

public class FormatCommand implements Command{
    private TextEditor editor;
    private String origianFomrat;
    private String format;
    private int from, to;

    public FormatCommand(TextEditor editor, String format, int from, int to) {
        this.editor = editor;
        this.format = format;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        origianFomrat = editor.getTextFrom(from, to);
        editor.applyFormat(from, to, format);
    }

    @Override
    public void undo() {
        editor.deleteText(from, to);
        editor.insertText(origianFomrat, from);
    }
}
