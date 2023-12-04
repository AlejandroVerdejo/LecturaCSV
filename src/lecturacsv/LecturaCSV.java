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
            //Abre el archivo indicado
            File notas = new File("notas.txt");
            //Crea un FileReader para acceder a este
            FileReader x = new FileReader(notas);
            //Crea un BufferedReader para mostrar los datos
            BufferedReader leernotas = new BufferedReader(x);
            String aux;
            //Mostrara el contenido del archivo linea a linea hasta que encuentre una linea vacia
            while((aux = leernotas.readLine())!=null)
            {
                //Divide la linea por cada ':' que es el divisor del CSV en este caso
                String snotas[] = aux.split(":");
                //Añade las notas de un mismo alumno en un array, pero no la primera posicion ya que es el nombre
                int inotas[] = {parseInt(snotas[1]),parseInt(snotas[2]),parseInt(snotas[3])};
                //Añade el alummno a un arraylist de alumnos
                alumnos.add(snotas[0]);
                //Añade la media de este alumno a un arraylist con las medias utilizando un metodo que la calcula enviandole el array de notas
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
            //Abrimos el nuevo archivo en el que guardamos las medias
            File notasmedias = new File("mediasAlumnos.txt");
            //Creamos el PrintWriter para escribir los datos en el archivo
            PrintWriter escribirmedias = new PrintWriter(notasmedias);
            //Escribiremos la nota media de cada alumno en el formato del CSV, en este caso el divisor es ':'
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
        //Con esto lo mostraremos por pantalla en el programa
        for(int i=0;i<alumnos.size();++i)
        {
            System.out.println("Alumno: " + alumnos.get(i) + " Media: " + medias.get(i));
        }        
    }
    //Metodo para sacar la media de un alumno
    public static int calcularMedia(int[] notas)
    {
        int media=0;
        int total=0;
        //Suma todas las notas del alumno
        for (int i=0;i<notas.length;++i)
        {
            total += notas[i];
        }
        //Calcula la media dividiendo la suma de las notas entre cantidad de notas que tenga
        media = total/notas.length;
        return media;
    }
    
}

