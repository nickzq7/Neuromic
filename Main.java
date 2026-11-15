
public class Main {

    public static void main(String... args) {
        Engine engine = new Engine();
        if (args.length != 0) {
            if (args[0].equals("--Help") || args[0].equals("--help")) {
                System.out.println("Welcome to Neuromic Antivirus");
                System.out.println("Usage: java Main <command> <path>");
                System.out.println("");
                System.out.println("--Help / --help : Show this help");
                System.out.println("--Scan / --scan <folder or file> : Scan folder or file for malware");
                System.out.println("--Watch / --watch <folder or file> : Watch folder or file every 1 second");
                System.out.println("--Add / --add <hash> : Add one SHA-256 hash to database");
                System.out.println("--Import / --import <file> : Import hashes from file (not used now)");
                System.out.println("");
                System.out.println("Example: java Main --scan <folder>");
                System.out.println("Example: java Main --watch <file>");
                System.out.println("Example: java Main --add <SHA-256 hash>");
            } else if (args[0].equals("--Scan") || args[0].equals("--scan")) {
                if (args.length > 1) {
                    engine.scan(args[1]);
                } else {
                    System.out.println("Missing Dir Path...");
                }

            } else if (args[0].equals("--Watch") || args[0].equals("--watch")) {
                if (args.length > 1) {
                    System.out.println("Watching dir... ---> " + args[1]);
                    engine.watch(args[1]);
                } else {
                    System.out.println("Missing Dir Path...");
                }
            } else if (args[0].equals("--Add") || args[0].equals("--add")) {
                if (args.length > 1) {
                    System.out.println("Adding Hash... ---> " + args[1]);
                    engine.addHash(args[1]);
                } else {
                    System.out.println("Missing Hash...");
                }
            } else if (args[0].equals("--Import") || args[0].equals("--import")) {
                if (args.length > 1) {
                    System.out.println("Importing ... ---> " + args[1]);
                } else {
                    System.out.println("Missing File Path...");
                }
            } else {
                System.out.println("Unknown Command");
            }
        } else {
            System.out.println("Give Command To Run Neuromic Antivirus");
        }
    }
}
