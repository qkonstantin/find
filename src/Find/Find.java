package Find;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Find {
    private final boolean flagR;
    private final String path;
    private final String file;

    public Find(boolean flagR, String path, String file) {
        this.flagR = flagR;
        this.path = path;
        this.file = file;
    }


    public void find() throws IOException {
        String[] f = file.split(", ");
        boolean result = false;
        File directory = new File(path);
        File[] names = directory.listFiles();


        if (flagR) {
            List<File> filesInFolder = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            for (String name: f) {
                for (int i = 0; i < filesInFolder.size(); i++) {
                    if (filesInFolder.get(i).toString().contains(name)) {
                        System.out.println("Найдено: " + filesInFolder.get(i));
                    }
                }
            }
        }
        else {
            for (String name: f) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].toString().equals(path + "\\" + name) && names[i].isFile()) {
                        result = true;
                        System.out.println("Найдено: " + path + "\\" + name);
                        break;
                    }
                }
            }

            if (result == true)
                System.out.println("Файл(ы) найден(ы)");
            else System.out.println("Файл не найден");
        }
    }
}