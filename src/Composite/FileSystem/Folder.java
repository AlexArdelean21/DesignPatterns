package Composite.FileSystem;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent{
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
