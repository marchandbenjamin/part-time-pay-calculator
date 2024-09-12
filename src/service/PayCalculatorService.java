package service;

import configuration.ConfigurationLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PayCalculatorService {

    ConfigurationLoader configurationLoader = new ConfigurationLoader();

    private final Scanner scanner;
    private final int[] hourlyRates = new int[24];
    private final List<boolean[]> listOfWorkingHours = new ArrayList<>();
    private int regularHourlyRate = 0;
    private int nightTimeHourlyRate = 0;
    private int midnightHourlyRate = 0;
    private int numberOfWorkingDays = 0;
    private int totalEarnings = 0;

    public PayCalculatorService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void calculatePay() throws Exception {
        configurationLoader.loadConfiguration();

        readHourlyRates();
        fillHourlyRates(configurationLoader.getRegularShift(), configurationLoader.getNightTimeShift(), configurationLoader.getMidnightShift());
        readNumberOfWorkingDays();
        fillListOfWorkingHours();
        calculateTotalEarnings();
    }

    private void readHourlyRates() {
        System.out.println("Enter hourly rates (3 Integers separated by space)");
        String firstLine = scanner.nextLine();
        String[] stringHourlyRates = firstLine.split(" ");

        regularHourlyRate = Integer.parseInt(stringHourlyRates[0]);
        nightTimeHourlyRate = Integer.parseInt(stringHourlyRates[1]);
        midnightHourlyRate = Integer.parseInt(stringHourlyRates[2]);
    }

    private void fillHourlyRates(String[] regularShift, String[] nightTimeShift, String[] midnightShift) throws Exception {
        for (String hour : regularShift) {
            hourlyRates[Integer.parseInt(hour)] = regularHourlyRate;
        }
        for (String hour : nightTimeShift) {
            hourlyRates[Integer.parseInt(hour)] = nightTimeHourlyRate;
        }
        for (String hour : midnightShift) {
            hourlyRates[Integer.parseInt(hour)] = midnightHourlyRate;
        }

        for(int i = 0 ; i < 24 ; i++) {
            if (hourlyRates[i] == 0) {
                throw new Exception("An hour is missing in the configuration, make sure to always put all hours from 0 to 23 in the configuration file.");
            }
        }
    }

    private void readNumberOfWorkingDays() {
        System.out.println("Enter number of working days (1 Integer)");
        numberOfWorkingDays = Integer.parseInt(scanner.nextLine());
    }

    private void fillListOfWorkingHours() {
        for (int i = 0; i < numberOfWorkingDays; i++) {
            System.out.println("Enter working hours for day " + (i + 1) + " (2 Integers between 0 and 23 separated by space)");
            String[] workingTime = scanner.nextLine().split(" ");

            boolean[] workingHours = new boolean[24];
            int startHour = Integer.parseInt(workingTime[0]);
            int endHour = Integer.parseInt(workingTime[1]);

            for (int j = startHour; j < endHour; j++) {
                workingHours[j] = true;
            }
            listOfWorkingHours.add(workingHours);
        }
    }

    private void calculateTotalEarnings() {
        for (boolean[] workingHours : listOfWorkingHours) {
            for (int i = 0; i < workingHours.length; i++) {
                if (workingHours[i]) {
                    totalEarnings += hourlyRates[i];
                }
            }
        }
        System.out.println(totalEarnings);
    }

}
