package ustc.nftchainv1.common.util;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileUtil {
    private static  final String prePath = "contract/";
    public String readFile(String fileName){
        fileName = prePath + fileName;

        StringBuilder res = new StringBuilder();
        try(InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"))){
            String str = null;
            while((str = br.readLine())!=null){
                res.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();;
        }
        return res.toString();

    }

    public static void main(String[] args) {
        FileUtil fileUtil = new FileUtil();
        System.out.println(fileUtil.readFile("store2.abi"));
        System.out.println(fileUtil.readFile("store2.bin"));
    }

}
