
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController


public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //Scanner scan = new Scanner(System.in);
    }

    @PostMapping("/verificar-palindromo")
    public Map<String, Object> verificarPalindromo(@RequestBody Map<String, String> requestBody) {
        String entrada = requestBody.get("entrada");

        Map<String, Object> response = new HashMap<>();

        String entradaProcessada = entrada.replaceAll("\\s+", "").toLowerCase();

        boolean isPalindromo = ehPalindromo(entradaProcessada);
        response.put("isPalindromo", isPalindromo);

        Map<Character, Integer> ocorrencias = contarOcorrenciasCaracteres(entradaProcessada);
        response.put("ocorrenciasCaracteres", ocorrencias);

        return response;
    }

    public static boolean ehPalindromo(String str) {
        int comprimento = str.length();
        for (int i = 0; i < comprimento / 2; i++) {
            if (str.charAt(i) != str.charAt(comprimento - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static Map<Character, Integer> contarOcorrenciasCaracteres(String str) {
        Map<Character, Integer> ocorrencias = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (ocorrencias.containsKey(c)) {
                ocorrencias.put(c, ocorrencias.get(c) + 1);
            } else {
                ocorrencias.put(c, 1);
            }
        }
        return ocorrencias;
    }
}
