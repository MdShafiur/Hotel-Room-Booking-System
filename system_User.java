public abstract class system_User {
    protected String name;// login name
    protected String fullname;// real name
    protected String userID;
    protected String password;
    protected boolean login_status = false;

    system_User() {
        this.name = "";
        this.password = "";
    }

    system_User(String n, String pw) {
        this.name = n;
        this.password = pw;
    }

    public abstract boolean Login(String id, String pw);

    public abstract void displayInfo();

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pressToContinue() {
        System.out.print("Press Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
}
