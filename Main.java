import java.util.Scanner;

interface IVehicle {
    void drive();
    void refuel();
}
class Car implements IVehicle {
    private String brand;
    private String model;
    private String fuelType;

    public Car(String brand, String model, String fuelType) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
    }

    @Override
    public void drive() {
        System.out.println("Автомобиль " + brand + " " + model + " едет.");
    }

    @Override
    public void refuel() {
        System.out.println("Автомобиль заправляется топливом: " + fuelType);
    }
}

class Motorcycle implements IVehicle {
    private String type; // спортивный, туристический
    private int engineVolume;

    public Motorcycle(String type, int engineVolume) {
        this.type = type;
        this.engineVolume = engineVolume;
    }

    @Override
    public void drive() {
        System.out.println("Мотоцикл типа " + type + " с объёмом двигателя " + engineVolume + "cc едет.");
    }

    @Override
    public void refuel() {
        System.out.println("Мотоцикл заправляется бензином.");
    }
}

class Truck implements IVehicle {
    private double loadCapacity;
    private int axles;

    public Truck(double loadCapacity, int axles) {
        this.loadCapacity = loadCapacity;
        this.axles = axles;
    }

    @Override
    public void drive() {
        System.out.println("Грузовик с грузоподъёмностью " + loadCapacity + " тонн и " + axles + " осями едет.");
    }

    @Override
    public void refuel() {
        System.out.println("Грузовик заправляется дизелем.");
    }
}

abstract class VehicleFactory {
    public abstract IVehicle createVehicle(Scanner scanner);
}

class CarFactory extends VehicleFactory {
    @Override
    public IVehicle createVehicle(Scanner scanner) {
        System.out.print("Введите марку автомобиля: ");
        String brand = scanner.nextLine();
        System.out.print("Введите модель: ");
        String model = scanner.nextLine();
        System.out.print("Введите тип топлива: ");
        String fuelType = scanner.nextLine();
        return new Car(brand, model, fuelType);
    }
}

class MotorcycleFactory extends VehicleFactory {
    @Override
    public IVehicle createVehicle(Scanner scanner) {
        System.out.print("Введите тип мотоцикла (спортивный/туристический): ");
        String type = scanner.nextLine();
        System.out.print("Введите объём двигателя (в куб. см): ");
        int engineVolume = Integer.parseInt(scanner.nextLine());
        return new Motorcycle(type, engineVolume);
    }
}

class TruckFactory extends VehicleFactory {
    @Override
    public IVehicle createVehicle(Scanner scanner) {
        System.out.print("Введите грузоподъёмность (в тоннах): ");
        double loadCapacity = Double.parseDouble(scanner.nextLine());
        System.out.print("Введите количество осей: ");
        int axles = Integer.parseInt(scanner.nextLine());
        return new Truck(loadCapacity, axles);
    }
}

class Bus implements IVehicle {
    private int passengerCapacity;
    private String fuelType;

    public Bus(int passengerCapacity, String fuelType) {
        this.passengerCapacity = passengerCapacity;
        this.fuelType = fuelType;
    }

    @Override
    public void drive() {
        System.out.println("Автобус везёт " + passengerCapacity + " пассажиров.");
    }

    @Override
    public void refuel() {
        System.out.println("Автобус заправляется топливом: " + fuelType);
    }
}

class BusFactory extends VehicleFactory {
    @Override
    public IVehicle createVehicle(Scanner scanner) {
        System.out.print("Введите вместимость автобуса (человек): ");
        int capacity = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите тип топлива: ");
        String fuelType = scanner.nextLine();
        return new Bus(capacity, fuelType);
    }
}

class ElectricScooter implements IVehicle {
    private int batteryCapacity;
    private int maxSpeed;

    public ElectricScooter(int batteryCapacity, int maxSpeed) {
        this.batteryCapacity = batteryCapacity;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void drive() {
        System.out.println("Электросамокат едет со скоростью до " + maxSpeed + " км/ч.");
    }

    @Override
    public void refuel() {
        System.out.println("Электросамокат заряжается от розетки. Ёмкость батареи: " + batteryCapacity + " Wh.");
    }
}

class ElectricScooterFactory extends VehicleFactory {
    @Override
    public IVehicle createVehicle(Scanner scanner) {
        System.out.print("Введите ёмкость батареи (Wh): ");
        int battery = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите максимальную скорость (км/ч): ");
        int speed = Integer.parseInt(scanner.nextLine());
        return new ElectricScooter(battery, speed);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nВыберите тип транспорта для создания:");
            System.out.println("1. Автомобиль");
            System.out.println("2. Мотоцикл");
            System.out.println("3. Грузовик");
            System.out.println("4. Автобус (новый тип)");
            System.out.println("5. Электросамокат (новый тип)");
            System.out.println("6. Выход");
            System.out.print("Ваш выбор: ");

            String choice = scanner.nextLine();
            VehicleFactory factory = null;

            switch (choice) {
                case "1":
                    factory = new CarFactory();
                    break;
                case "2":
                    factory = new MotorcycleFactory();
                    break;
                case "3":
                    factory = new TruckFactory();
                    break;
                case "4":
                    factory = new BusFactory();
                    break;
                case "5":
                    factory = new ElectricScooterFactory();
                    break;
                case "6":
                    exit = true;
                    System.out.println("Выход из программы.");
                    continue;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    continue;
            }

            IVehicle vehicle = factory.createVehicle(scanner);
            System.out.println("\n--- Создано транспортное средство ---");
            vehicle.drive();
            vehicle.refuel();
        }

        scanner.close();
    }
}