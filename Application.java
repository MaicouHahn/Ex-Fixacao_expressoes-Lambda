import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        
        String path = "C:\\Users\\Zeturiell\\Desktop\\programação\\Java\\EXUdemy\\Expressões Lambda-Programação Funcional\\ExFixacao\\in.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            List<Employee> list = new ArrayList<>();

            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            System.out.print("Digite uma faixa salarial para filtrar os emails:");

            Scanner scan = new Scanner(System.in);
            double salario = scan.nextDouble();
            List<String> emails = list.stream()// transforma em dados
                    .filter(x -> x.getSalary() > salario)// filtra os elementos cuja o salario é maior que o determinado
                    .map(p -> p.getEmail())// diz para utilizar o campo getemail e combina com o .sorted
                    .sorted((s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase()))// organiza por ordem alfabetica
                    .collect(Collectors.toList());// converte novamente em lista, porem de string

            for (String employee : emails) {
                System.out.println(employee);
            }

            Double salarios = list.stream().filter(x -> x.getName().toUpperCase().startsWith("M"))
                    .map(p -> p.getSalary()).reduce(0.0, (x, y) -> x + y);
            System.out.println("Soma dos salarios cujo nome Inicia com M: " + salarios);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
