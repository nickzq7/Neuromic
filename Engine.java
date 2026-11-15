
import java.io.File; import java.security.MessageDigest; import java.io.FileInputStream;

class Engine {

    

    private String getHash(String fileName){
         try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        FileInputStream input = new FileInputStream(fileName);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            digest.update(buffer, 0, bytesRead);
        }
        input.close();
        byte[] hashBytes = digest.digest();
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < hashBytes.length; i++) {
        String part = Integer.toHexString(0xff & hashBytes[i]);
        if (part.length() == 1) {
        hex.append('0');
    }
        hex.append(part);
        }
        return hex.toString();

    }
    catch (Exception e) {
        return "";
    }
    }

    void scan(String foldername) {
        File path = new File(foldername);
        loadHash dataBase = new loadHash();
        java.util.List<String> hashes = dataBase.load("C:\\Users\\Manish Parihar\\Desktop\\Neuromic\\DatabaseHash_full.txt");
        java.util.HashSet<String> set = new java.util.HashSet<String>(hashes);

        if (path.exists()) {

            System.out.println("Working dir... ---> " + foldername);
            if (path.isFile()) {
                System.out.println("This is File");
                String hash = getHash(path.getPath());
                if (set.contains(hash)) {
                 System.out.println("MALWARE");
                }
                else {
                    System.out.println("CLEAN");
                    }
                            
                
            } else if (path.isDirectory()) {
                File[] files = path.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()){
                    System.out.println(files[i].getName());
                    String hash = getHash(files[i].getPath());
                    if (set.contains(hash)) {
                        System.out.println("MALWARE");
                            }
                        else {
                        System.out.println("CLEAN");
                            }
                    }
                    else if(files[i].isDirectory()){
                        scan(files[i].getPath());
                    }
                }
            } else {
                System.out.println("Invalid Path");
            }
        }
    }

    void watch(String filePath){
        

        while (true){
        try{
            Thread.sleep(1000);
            scan(filePath);
        }
        catch (Exception e) {
        System.out.println("Sleep interrupted");
            }
        }
    }

    void addHash(String hash) {
    try {
        java.io.FileWriter writer = new java.io.FileWriter("C:\\Users\\Manish Parihar\\Desktop\\Neuromic\\DatabaseHash_full.txt", true);
        writer.write(hash.toLowerCase() + "\n");
        writer.close();
        System.out.println("Added: " + hash);
    }
    catch (Exception e) {
        System.out.println("Error adding hash");
    }
    }

}
