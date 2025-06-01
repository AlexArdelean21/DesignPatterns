package Command.TextEditor;

public class Main {
    public static void main(String[] args){
        EditorInvoker invoker = new EditorInvoker();
        TextEditor editor = new TextEditor();
        String text = "Hello Word\n";

        Command typeCommand = new TypeCommand(editor, text, 0);
        invoker.executeCommand(typeCommand);


//        Command deleteCommand = new DeleteCommand(editor, 0, text.length() - 1);
//        System.out.println("\n");
//        invoker.executeCommand(deleteCommand);

        invoker.undo();
        System.out.println("After 1x undo: " + editor.getContent());

        invoker.undo();
        System.out.println("After 2x undo: " + editor.getContent());

        invoker.redo();
        System.out.println("After 1x redo: " + editor.getContent());
    }
}
