class loadHash {
    java.util.List<String> load(String fileName) {
        try {
            return java.nio.file.Files.readAllLines(java.nio.file.Paths.get(fileName));
        }
        catch (Exception e) {
            System.out.println("Error loading database");
            return null;
        }
    }
}


