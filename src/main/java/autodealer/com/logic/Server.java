package autodealer.com.logic;

import autodealer.com.logic.dao.impl.AutomobileDaoMySQl;
import autodealer.com.logic.dao.impl.ClientDaoMySql;
import autodealer.com.logic.dao.impl.MachinePartsDaoMySql;
import autodealer.com.logic.dao.impl.ManagerDaoMySql;
import autodealer.com.logic.entity.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executors;

import static autodealer.com.logic.config.Constant.*;
import static autodealer.com.logic.utils.Utils.colorPrintln;


public class Server {

    //private final static String PATH_FILE = "C:\\Users\\User\\IdeaProjects\\AutoDealer_3\\static";
    private final static String PATH_FILE = "C:\\Users\\nhaitanov\\java workspaces\\IdeaProjects\\AutoDealer_3\\static";
    private static AutomobileDaoMySQl automobileDaoMySQl;
    private static ArrayList<Automobile> automobileArrayList;

    public Server() {
        automobileDaoMySQl = new AutomobileDaoMySQl();
        starting();
    }

    static void addCors(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET");
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    }

    private static void sendResponseHeaders(int codeAnswer, String answer, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(codeAnswer, answer.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(answer.getBytes());
        os.close();
    }

    private void starting() {
        colorPrintln("Server auto dealer starting!", 35);

        InetSocketAddress inetSocketAddress = new InetSocketAddress(1030);
        HttpServer server = null;
        try {
            server = HttpServer.create(inetSocketAddress, 5);
            server.createContext("/", new StaticHandler());
            server.createContext("/api/get-client", new GetClientHandler());
            server.createContext("/login", new LoginHandler());
            server.setExecutor(Executors.newCachedThreadPool());
            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printQuery(String query){
        colorPrintln(query==null?"QUERY=null":query,33);
    }
    static class LoginHandler implements HttpHandler {
        private String answer = "";
        private String query = "";
        private String[] arrDataQuery;
        private HttpExchange httpExchange = null;

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            this.httpExchange = httpExchange;
            query = httpExchange.getRequestURI().getQuery();
            printQuery(query);
            if (query != null && query.isEmpty() != true) {
                if (query.contains("id")) {
                    arrDataQuery = query.split("&id=|&fname=");
                    colorPrintln("ArrDataQuery :   " + Arrays.toString(arrDataQuery), 37);
                    colorPrintln(arrDataQuery[1], 37);
                    checkID(arrDataQuery[1]);
                }
                colorPrintln("Answer: " + answer, 32);
            }

        }

        private void checkID(String id) {
            switch (id) {
                case "registration":
                    registration();
                    break;
                case "logIn":
                    logIn();
                    break;
                default:
                    answer = "ID - not found!";
                    System.out.println(answer);
                    break;
            }
        }

        private void registration() {
            logIn();
            System.out.println("registration()");
            try {
                if (answer.equals(LOGIN_NOT_SUCCESSFUL)) {
                    String[] arrQuery = query.split("login=|&password=|&id=|&fname=|&lname=|&email=");
                    ManagerDaoMySql managerDaoMySql = new ManagerDaoMySql();
                    Manager manager = managerDaoMySql.findManager(arrQuery[1], arrQuery[5], arrQuery[6]);
                    if (manager != null) {
                        if (manager.getManager_pass().isEmpty() && manager.getManager_login().isEmpty()) {
                            manager.setManager_login(arrQuery[1]);
                            manager.setManager_pass(arrQuery[2]);
                            managerDaoMySql.updateManager(manager);
                            generateAnswer(SUCCESSFUL_REGIST_IN_SYSTEM, manager);
                            sendResponseHeaders(200, answer, httpExchange);
                        }
                    } else {
                        answer = WRONG_REGIST_IN_SYSTEM;
                        sendResponseHeaders(401, answer, httpExchange);
                    }
                } else if (answer.contains(SUCCESSFUL_ENTER_IN_SYSTEM)) {
                    answer = WRONG_REGIST_IN_SYSTEM_BECOUSE_ALREADY_REGIST;
                    sendResponseHeaders(406, answer, httpExchange);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void logIn() {
            try {
                System.out.println("login()");
                String[] arrQuery = query.split("login=|&password=|&id=");
                Manager manager = new ManagerDaoMySql().findManager(arrQuery[1], arrQuery[2]);
                if (manager != null) {
                    generateAnswer(SUCCESSFUL_ENTER_IN_SYSTEM, manager);
                    sendResponseHeaders(200, answer, httpExchange);
                } else {
                    answer = WRONG_ENTER_IN_SYSTEM;
                    sendResponseHeaders(401, answer, httpExchange);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void generateAnswer(String message, Manager manager) {
            answer = message + " Data manager: " +
                    manager.getManager_fname() + " " +
                    manager.getManager_lname() + " " +
                    manager.getManager_post() + " " +
                    manager.getManager_email();
        }
    }

    static class GetClientHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            printQuery(query);
            if (query != null) {
                String answer = performOperation(query);
                if (answer != "") {
                    sendResponseHeaders(200, answer, exchange);
                } else {
                    sendResponseHeaders(405, answer, exchange);
                }

                addCors(exchange);
            }

        }

        private String performOperation(String query) {
            String[] arrSplitQuery = query.split("operation=|&");
            String answer = "", operation = "";
            if (arrSplitQuery != null) {
                System.out.println(Arrays.toString(arrSplitQuery));
                operation = arrSplitQuery[1];
            }
            switch (operation) {
                case "searchAuto":
                    arrSplitQuery = query.split("operation=|&model_auto=|&engine_car=|&power_car=|&car_body=|&color_car=");
                    System.out.println(Arrays.toString(arrSplitQuery));
                    AutomobileDaoMySQl automobileDaoMySQl = new AutomobileDaoMySQl();
                    String queryTodb = Utilites.queryGetAllCars + " where a.model_car = " + arrSplitQuery[2] +
                            " and a.engine_car=" + arrSplitQuery[3] +
                            " and a.power_car =" + arrSplitQuery[4] +
                            " and a.type_car_body = " + arrSplitQuery[5] +
                            " and a.color_car = " + arrSplitQuery[6];
                    answer = buildAnswerAuto(automobileDaoMySQl.queryAboutAuto(queryTodb), CAR_NOT_FOUND);
                    break;
                case "getMostPopularAuto":
                    Automobile a = new AutomobileDaoMySQl().getMostPopularAuto().get(0);
                    answer = a.getId() + " " + a.getCar_make() + " " + a.getCar_price() + " " +
                            a.color_carString + " " + a.engine_carString + " " + a.model_carString + " " +
                            a.getPower_car() + " " + a.type_car_bodyString + " " + a.getYear_issue_car() + " ";
                    break;
                case "getSalesProfit":
                    arrSplitQuery = query.split("operation=|&from=|&for=");
                    answer = new AutomobileDaoMySQl().getSalesProfitForTheGap(arrSplitQuery[2], arrSplitQuery[3]);
                    break;
                case "getAllAuto":
                    AutomobileDaoMySQl automobileDaoMySQl1 = new AutomobileDaoMySQl();
                    answer = buildAnswerAuto(automobileDaoMySQl1.readAllAutomobiles(), "Виникла проблема, спробуйте пізніше");
                    break;
                case "getAllClient":
                    for (Client client : new ClientDaoMySql().readAllClients()) answer += client.printDataClient();
                    break;
                case "getAllManager":
                    for (Manager manager : new ManagerDaoMySql().readAllManagers())
                        answer += manager.printDataManager();
                    break;
                case "getAllModelCars":
                    MachinePartsDaoMySql<ModelCar> alModelsCar = new MachinePartsDaoMySql<>(ModelCar.class);
                    for (ModelCar m : alModelsCar.read()) answer += m.toString() + " ";
                    break;
                case "GetAllTypeCarBody":
                    MachinePartsDaoMySql<TypeCarBody> alTypesCarBody = new MachinePartsDaoMySql(TypeCarBody.class);
                    for (TypeCarBody typeCarBody : alTypesCarBody.read()) answer += typeCarBody.toString() + " ";
                    break;
                case "GetAllEngineCar":
                    MachinePartsDaoMySql<EngineCar> alEngineCar = new MachinePartsDaoMySql(EngineCar.class);
                    for (EngineCar engineCar : alEngineCar.read()) answer += engineCar.toString() + " ";
                    break;
                case "GetAllPowerCar":
                    MachinePartsDaoMySql<PowerCar> alPowerCar = new MachinePartsDaoMySql(PowerCar.class);
                    for (PowerCar powerCar : alPowerCar.read()) answer += powerCar.toString() + " ";
                    break;
                case "GetAllColorCar":
                    MachinePartsDaoMySql<ColorCar> alColorCar = new MachinePartsDaoMySql(ColorCar.class);
                    for (ColorCar colorCar : alColorCar.read()) answer += colorCar.toString() + " ";
                    break;
                case "UpdateAuto":
                    arrSplitQuery = query.split("operation=|&id_auto=|&price=|&car_make=|&model_auto=|&year_issue_car=|&power_car=|&engine_car=|&color_car=|&car_body=");
                    try {
                        Automobile automobile = new Automobile(Integer.parseInt(arrSplitQuery[2]),
                                Integer.parseInt(arrSplitQuery[3]),
                                arrSplitQuery[4],
                                Integer.parseInt(arrSplitQuery[5]),
                                arrSplitQuery[6],
                                Integer.parseInt(arrSplitQuery[7]),
                                Integer.parseInt(arrSplitQuery[8]),
                                Integer.parseInt(arrSplitQuery[9]),
                                Integer.parseInt(arrSplitQuery[10]));
                        new AutomobileDaoMySQl().updateAutomobile(automobile);
                        answer = CHANGES_MADE_SUCCESS;
                    } catch (Exception e) {
                        answer = ERROR_ENTERING_DATA;
                        e.printStackTrace();
                    }
                    break;
                case "getAutos":
                    arrSplitQuery = query.split("&id_model=");
                    Arrays.stream(arrSplitQuery).forEach((s) -> System.out.println("arrays: " + s));
                    AutomobileDaoMySQl autoDaoMySQl = new AutomobileDaoMySQl();
                    String queryToDb = Utilites.queryGetAllCars + " where a.model_car = " + arrSplitQuery[1];
                    answer = buildAnswerAuto(autoDaoMySQl.queryAboutAuto(queryToDb), NO_CARS_FOR_SALES);
                    System.err.println("GET AUTOS: "+ answer);
                    break;
            }
            return answer;
        }

        private String buildAnswerAuto(ArrayList<Automobile> autoArrayList, String elseAnswer) {
            ArrayList<Automobile> automobileArrayList = autoArrayList;
            String answer = "";
            if (automobileArrayList.isEmpty() == false) {
                for (Automobile a : automobileArrayList) {
                    answer += a.getId() + " " + a.getCar_make() + " " + a.getCar_price() + " " +
                            a.color_carString + " " + a.engine_carString + " " + a.model_carString + " " +
                            a.getPower_car() + " " + a.type_car_bodyString + " " + a.getYear_issue_car() + " ";
                }
            } else {
                answer = elseAnswer;
            }
            return answer;
        }
    }

    static class StaticHandler implements HttpHandler {

        private String nameFile = "";
        private String query = "";

        private static void readFile(HttpExchange exchange, String nameFile) {

            printQuery(exchange.getRequestURI().getQuery());
            Path path = Paths.get(PATH_FILE + nameFile);
            if (path != null) {
                try {
                    byte[] fileBuffer = Files.readAllBytes(path);
                    if (fileBuffer != null) {
                        exchange.sendResponseHeaders(200, fileBuffer.length);
                        addCors(exchange);
                        OutputStream body = exchange.getResponseBody();
                        body.write(fileBuffer);
                        body.close();
                    } else {
                        sendResponseHeaders(404, "", exchange);
                    }
                } catch (NoSuchFileException e) {
                    System.err.println(e.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StaticHandler that = (StaticHandler) o;
            return Objects.equals(query, that.query);
        }

        @Override
        public int hashCode() {
            return Objects.hash(query);
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            query = exchange.getRequestURI().toString();
            if (!query.equals("/")) {
                nameFile = query;
                readFile(exchange, nameFile);
                return;
            } else if (query.equals("/")) {
                nameFile = "\\index.html";
                readFile(exchange, nameFile);
                return;
            }
        }


    }
}
