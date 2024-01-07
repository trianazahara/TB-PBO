import java.util.Scanner;

public class Login {
    String user = "triana";
    String password = "trianazahara";
    String captcha = "qwerty";
    String inputCaptcha;
    String inputUser;
    String inputPassword;
    
    //method login
    public void login(){
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("=============== LOGIN ===============");

    //Password
    while(true){
        System.out.println("Inputkan Username");
        inputUser = scanner.next();
        System.out.println("Inputkan Password");
        inputPassword = scanner.next();
            
    if (inputPassword.equals(password)&&inputUser.equals(user)){
        System.out.println("=========== LOGIN SUKSES ===========\n");
        break;
    }else{
        System.out.println("Username atau password anda salah \n");
        }
    }
            
    //Captcha
    System.out.println("Kode captcha = " + captcha);
    System.out.println("Masukkan captcha");
    inputCaptcha = scanner.next();
    
    while(!inputCaptcha.equalsIgnoreCase(captcha)){
        System.out.println("Kode captcha salah!!!!!\n");
        System.out.println("Masukkan captcha");
        inputCaptcha = scanner.next();
        }
    
    System.out.println("CAPTCHA YANG DIMASUKKAN BENAR\n\n");
    }
}

