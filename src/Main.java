import main.service.PayCalculatorService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        PayCalculatorService payCalculatorService = new PayCalculatorService(new Scanner(System.in));

        payCalculatorService.calculatePay();
    }
}