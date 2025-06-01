package Command.TextEditor;

import java.util.Stack;

public class EditorInvoker {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command){
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo(){
        if (!undoStack.isEmpty()){
            Command command = undoStack.pop();
            command.execute();
            redoStack.push(command);
        }else{
            System.out.println("Nothing to undo!");
        }
    }

    public void redo(){
        if (!redoStack.isEmpty()){
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);

        }else{
            System.out.println("Nothing to redo!");
        }
    }
}

