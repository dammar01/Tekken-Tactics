package utils.helper;

public class Session {

    private static String username;
    private static String email;
    private static Integer id;

    // Setter untuk menyimpan data saat login berhasil
    public static void setSession(String uname, String uemail, int uid) {
        username = uname;
        email = uemail;
        id = uid;
    }
    
    public static Integer getId(){
        return id;
    }
    
    // Getter untuk mengambil data sesi
    public static String getUsername() {
        return username;
    }

    public static String getEmail() {
        return email;
    }

    // Metode untuk menghapus sesi saat logout
    public static void clearSession() {
        username = null;
        email = null;
    }
}
