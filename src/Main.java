import entity.Flight;
import service.FilterImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int num;
        Scanner in = new Scanner(System.in);
        List<Flight> flights = FlightBuilder.createFlights();
        do {
            System.out.println("Выберите фильтрацию сегментов для вашего удобства:\n" +
                    "1-сигменты без фильтра\n" +
                    "2-вылет до текущего момента времени\n" +
                    "3-имеются сегменты с датой прилёта раньше даты вылета\n" +
                    "4-перелеты, где общее время, проведённое на земле, превышает два часа\n" +
                    "0-для завершения просмотра\n" +
                    "\nВаш выбор: ");
            num = in.nextInt();
            if (num == 1) {
                System.out.println("Сигменты без фильтра:\n" + flights);
            } else if (num == 2) {
                List<Flight> flightsDepartureBeforeNow = new FilterImpl(flights)
                        .filterDepartureBeforeNow()
                        .build();
                System.out.println("Вылет до текущего момента времени:\n" + flightsDepartureBeforeNow);

            } else if (num == 3) {
                List<Flight> flightsArrivalBeforeDeparture = new FilterImpl(flights)
                        .filterArrivalBeforeDeparture()
                        .build();
                System.out.println("Имеются сегменты с датой прилёта раньше даты вылета:\n" + flightsArrivalBeforeDeparture);
            } else if (num==4) {
                List<Flight> listMoreTwoHours = new FilterImpl(flights)
                        .filterSumTimeOnGroundMoreThanTwoHours()
                        .build();
                System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа:\n" + listMoreTwoHours);
            } else if (num > 4 || num < 0) {
                System.out.println("Введено неправильное значение");
            }
        }
        while (num != 0);
    }
}