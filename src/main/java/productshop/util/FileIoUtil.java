package productshop.util;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileIoUtil {
    String readFile(String filePath) throws IOException;
    void write(String fileContent, String file) throws IOException;
}
