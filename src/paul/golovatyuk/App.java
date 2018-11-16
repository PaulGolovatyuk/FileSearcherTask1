package paul.golovatyuk;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws IOException {
        Path rootPath = Paths.get(args[0]);
        int depth = Integer.parseInt(args[1]);
        String mask = args[2];

        List<Path> list = searchFiles(rootPath, depth, mask);
        showFiles(list);
    }

    private static void showFiles(List<Path> list) {
        for (Path path : list) {
            System.out.println(path);
        }
    }

    private static List<Path> searchFiles(Path aPath, int depth, String mask) throws IOException {
        List<Path> resultList = new ArrayList<>();
        Stream<Path> stream =
                Files.find(aPath, depth, (path, basicFileAttributes) -> {
                    File file = path.toFile();
                    return file.getName().contains(mask);
                });
        stream.forEach(resultList::add);
        return resultList;
    }
}
