package productshop.util;

import java.io.*;

public class FileIoUtilImpl implements FileIoUtil {


    @Override
    public String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        StringBuilder sb = new StringBuilder();
        String line = "";

        while ((line = bufferedReader.readLine())!= null){
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    public void write(String fileContent, String file) throws IOException {
        String path = System.getProperty("user.dir") +"/src/main/resources" + file;
        try(
                OutputStream outputStream = new FileOutputStream(path);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))
        ){
            bufferedWriter.write(fileContent);
        }
    }
}
