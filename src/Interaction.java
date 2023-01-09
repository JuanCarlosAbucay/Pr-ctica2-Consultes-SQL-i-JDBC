import java.util.Scanner;

public class Interaction {
    Scanner escaner = new Scanner(System.in);
    int seleccio;
    String titol;
    String data;
    String pais;
    public int menu(){
        System.out.println("Que vols fer?");
        System.out.println("1. Pelicules estrenades");
        System.out.println("2. Pelicules per director");
        System.out.println("3. Insert peliculas");

        seleccio = escaner.nextInt();

        return seleccio;
    }

    public String director(){
        System.out.println("Que director vols buscar?");
        return escaner.nextLine();
    }

    public void film(){
        System.out.println("Nom de la película: ");
        titol = escaner.nextLine();
        System.out.println("Data d'estrena: ");
        data = escaner.nextLine();
        System.out.println("Pais: ");
        pais = escaner.nextLine();
    }

    public String getTitol() {
        return titol;
    }

    public String getData() {
        return data;
    }

    public String getPais() {
        return pais;
    }
}
