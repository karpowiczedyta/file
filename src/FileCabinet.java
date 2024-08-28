import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

interface Cabinet {
    // zwraca dowolny element o podanej nazwie
    Optional<Folder>
    findFolderByName(String name);

    // zwraca wszystkie foldery podanego rozmiaru SMALL/MEDIUM/LARGE
    List<Folder> findFoldersBySize(String size);

    //zwraca liczbę wszystkich obiektów tworzących strukturę
    int count();
}

public class FileCabinet implements Cabinet, MultiFolder {
    private List<Folder> folders;

    private String name;

    private String size;

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return getFolders().stream()
                .filter((folderName)-> folderName.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return getFolders().stream()
                .filter((folderSize)-> folderSize.getSize().equals(size))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
       return (int) getFolders().stream().count();
    }

    @Override
    public List<Folder> getFolders() {
        return folders;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSize() {
        return size;
    }
}

interface Folder {
    String getName();
    String getSize();
}

interface MultiFolder extends Folder {
    List<Folder> getFolders();
}
