package lecturacsv;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;
public class LecturaCSV{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> alumnos = new ArrayList<>();
        ArrayList<Integer> medias = new ArrayList<>();
        try
        {
            File notas = new File("notas.txt");
            FileReader x = new FileReader(notas);
            BufferedReader leernotas = new BufferedReader(x);
            String aux;
            while((aux = leernotas.readLine())!=null)
            {
                String snotas[] = aux.split(":");
                int inotas[] = {parseInt(snotas[1]),parseInt(snotas[2]),parseInt(snotas[3])};
                alumnos.add(snotas[0]);
                medias.add(calcularMedia(inotas));
            }
            leernotas.close();
        } 
        catch (IOException e)
        {
            System.out.println("Ha habido un error al leer el archivo");
        }
        try
        {
            File notasmedias = new File("mediasAlumnos.txt");
            FileWriter x = new FileWriter(notasmedias);
            PrintWriter escribirmedias = new PrintWriter(x);
            for(int i=0;i<alumnos.size();++i)
            {
                escribirmedias.println(alumnos.get(i) + ":" + medias.get(i));
            }
            escribirmedias.close();
        }
        catch (IOException e)
        {
            System.out.println("Ha habido un error al escribir el archivo");
        }
        for(int i=0;i<alumnos.size();++i)
        {
            System.out.println("Alumno: " + alumnos.get(i) + " Media: " + medias.get(i));
        }        
    }
    
    public static int calcularMedia(int[] notas)
    {
        int media=0;
        int total=0;
        for (int i=0;i<notas.length;++i)
        {
            total += notas[i];
        }
        media = total/notas.length;
        return media;
    }
    
}

