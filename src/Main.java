import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Main {

    //Create arraylists to hold the Shareholder, Share, Portfolio objects
    private static ArrayList<Shareholder> shareholders = new ArrayList<>();
    private static ArrayList<Share> shares = new ArrayList<>();
    private static ArrayList<Portfolio> portfolios = new ArrayList<>();


    public static void main(String[] args) {

        while (true) {
            //display the main menu
            mainMenu();
            //get user input for the option
            System.out.print("\nEnter the number of the option and press enter : ");
            int choice;
            //error handling for invalid inputs
            try {
                choice = Integer.parseInt(getUserInput());
            } catch (Exception e) {
                System.out.println("\nPlease enter a valid number between 1 and 6.");
                continue;
            }
            //execute relevant function according to the choice
            switch (choice) {
                case 1 -> loadFiles();
                case 2 -> update();
                case 3 -> tradeShares();
                case 4 -> portfolioReport();
                case 5 -> saveFiles();
                case 6 -> exitProgram();
                default -> System.out.println("\nPlease enter a valid number between 1 and 6.");
            }
        }

    }

    //function to display the main-menu
    private static void mainMenu() {
        System.out.println("1.Load Files");
        System.out.println("2.Update");
        System.out.println("3.Trade Shares");
        System.out.println("4.Portfolio report");
        System.out.println("5.Save files");
        System.out.println("6.Exit Program");
    }

    //function to get the input from the user
    private static String getUserInput() {
        return new Scanner(System.in).nextLine();
    }

    //function to load all the text files
    private static void loadFiles() {
        loadShareholders();
        loadShares();
        loadPortfolios();
    }

    //function to load shareholders.txt
    private static void loadShareholders() {
        String fileName = "src/shareholders.txt";

        while (true) {
            //connect the program with the text file
            File file = new File(fileName);
            try {
                //new scanner to scan the file line by line
                Scanner readFile = new Scanner(file);

                int custId = 0;
                String[] temp;
                String name = "";
                String firstName = "";
                String surname = "";
                String address = "";
                String phone = "";
                String pId = "";

                while (readFile.hasNextLine()) {
                    temp = readFile.nextLine().split(",");
                    custId = Integer.parseInt(temp[0]);
                    name = temp[1];
                    firstName = name.split(" ", 2)[0];
                    surname = name.split(" ", 2)[1];
                    address = temp[2];
                    phone = (temp[3]);
                    pId = temp[4];
                    //create new sharehlder object using the read data and pushing it to the shareholders arraylist
                    Shareholder shareholder = new Shareholder(custId, firstName, surname, address, phone, pId);
                    shareholders.add(shareholder);

                }
                System.out.println("Shareholders loaded successfully");
                break;
            } catch (Exception e) {
                System.out.print(e + "\nFile is either corrupted or not found. Please enter a valid name of the file containing shareholder information :");
                fileName = getUserInput();
            }
        }
    }
//    function to load the sharea.txt
    private static void loadShares() {
        String fileName = "src/shares.txt";

        while (true) {
            //connect the program with the text file
            File file = new File(fileName);
            try {
                //new scanner to read the file
                Scanner readFile = new Scanner(file);


                String shareCode = "";
                String company = "";
                double price = 0.0;
                String[] temp;

                while (readFile.hasNextLine()) {
                    temp = readFile.nextLine().split(",");
                    shareCode = temp[0];
                    company = temp[1];
                    price = Double.parseDouble(temp[2]);
                    //create a new share object and add it to the shares arraylist
                    Share share = new Share(shareCode, company, price);
                    shares.add(share);

                }
                System.out.println("Shares loaded successfully");
                break;
            } catch (Exception e) {
                System.out.print(e + "\nFile is either corrupted or not found. Please enter a valid name of the file containing Shares information :");
                fileName = getUserInput();
            }
        }
    }
    //function to load the portfolios.txt
    private static void loadPortfolios() {
        String fileName = "src/portfolios.txt";

        while (true) {
            //connect the program with the text file
            File file = new File(fileName);
            try {
                Scanner readFile = new Scanner(file);


                while (readFile.hasNextLine()) {
                    //read data seperated by commas
                    String pId;
                    ArrayList<PortfolioShare> shares = new ArrayList<>();
                    String[] rest, temp;
                    temp = readFile.nextLine().split(",");
                    pId = temp[0];
                    rest = Arrays.copyOfRange(temp, 1, temp.length);
                    int i = 0;
                    for (String x : rest) {
                        if (i % 2 == 0) {
                            //create portfolioshare object inside portfolio object and add data
                            PortfolioShare temp2 = new PortfolioShare(x, Integer.parseInt(rest[i + 1]));
                            shares.add(temp2);
                        }
                        i++;
                    }


                    Portfolio portfolio = new Portfolio(pId, shares);
                    portfolios.add(portfolio);

                }
                System.out.println("Portfolios loaded successfully\n\n");
                break;
            } catch (Exception e) {
                System.out.print(e + "\nFile is either corrupted or not found. Please enter a valid name of the file containing portfolios information :");
                fileName = getUserInput();
            }
        }
    }
    //function to display the update menu
    private static void update() {
        loop:
        while (true) {
            //display update menu
            System.out.println("1.Update share price");
            System.out.println("2.Update Customer phone");
            System.out.println("3.Return to main menu");

            System.out.print("\nEnter the number of the sub-menu option and press enter : ");
            int choice;
            try {
                choice = Integer.parseInt(getUserInput());
            } catch (Exception e) {
                System.out.println("\nPlease enter a valid number between 1 and 6.");
                continue;
            }
            switch (choice) {
                case 1 -> updateSharePrice();
                case 2 -> updateCustomerPhone();
                case 3 -> {
                    break loop;
                }
                default -> System.out.println("\nPlease enter a valid number between 1 and 3.");
            }
        }
    }
    //function to update the price of an individual share
    private static void updateSharePrice() {
        loop:
        do {
            System.out.print("Enter the share code of the share which you wish to update the price : ");
            String shareCode = getUserInput();
            Share share = getSharesData(shareCode);
            if ((share != null)) {
                System.out.print("Current price: " + share.getPrice() + ". Enter the new price :");

                while (true) {
                    try {
                        double price = Double.parseDouble(getUserInput());
                        share.setPrice(price);
                        System.out.println("New share price successfully updated\n\n");
                        break loop;
                    } catch (Exception e) {
                        System.out.print("Enter a valid numeric amount : ");
                    }
                }

            }

            System.out.print("No share was found with that share code. To try again press enter. To go back type \"exit\" and press enter. :");

        } while (!Objects.equals(getUserInput(), "exit"));

    }
    //function to update the customer phone number details
    private static void updateCustomerPhone() {
        loop:
        do {
            System.out.print("Enter first name and the surname of the user whose phone number must be changed : ");
            String name = getUserInput().toLowerCase();
            Shareholder shareholder = getShareholderData(name);
            if (shareholder!=null) {
                while (true) {
                    try {
                        System.out.print("Enter the new phone number : ");
                        String newPhone = getUserInput();
                        if (newPhone.length() != 10 || newPhone.charAt(0) != '0' || newPhone.charAt(1) != '4')
                            throw new Exception("Invalid Phone Number");
                        shareholder.setPhone(newPhone);
                        System.out.println("Telephone Number successfully updated\n\n");

                        break loop;
                    } catch (Exception e) {
                        System.out.println("Enter a valid numeric amount with 10 digits and starting with 04 : ");
                    }
                }
            }

            System.out.print("No user was found with that names. To try again press enter. To go back type \"exit\" and press enter. :");
        } while (!Objects.equals(getUserInput(), "exit"));
    }
    //function to do different trades on shares
    private static void tradeShares() {
        System.out.print("Enter the name of the user : ");
        Shareholder shareholder = getShareholderData(getUserInput().toLowerCase());
        if (shareholder != null) {
            System.out.print("Enter the Sharecode of the company : ");
            Share share = getSharesData(getUserInput());
            if (share != null) {
                Portfolio portfolio = getPortfolioData(shareholder.getPortfolioId());
                PortfolioShare item = getPortfolioShareData(portfolio.getShares(), share.getShareCode());

                if (item != null) {
                    System.out.println("Would you like to buy or sell?");
                    System.out.println("1.Buy");
                    System.out.println("2.Sell");
                    switch (Integer.parseInt(getUserInput())) {
                        case 1 -> buyShares(portfolio, item);
                        case 2 -> sellShares(portfolio, item);
                        default -> {
                        }
                    }
                } else {
                    buyNewShares(portfolio, share.getShareCode());
                }

            } else {
                System.out.println("No such Sharecode exists");
            }
        } else
            System.out.println("No such user exists");

    }
    //function to buy new shares
    private static void buyNewShares(Portfolio portfolio, String shareCode) {
        loop:
        while (true) {
            System.out.print("Enter the amount of " + shareCode + " shares you want to buy : ");
            try {
                int amount = Integer.parseInt(getUserInput());
                portfolio.getShares().add(new PortfolioShare(shareCode, amount));
                System.out.println("Records successfully updated.");
                break loop;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid amount");
            }
        }
    }
    //function to buy shares that the user already have
    private static void buyShares(Portfolio portfolio, PortfolioShare item) {
        loop:
        while (true) {
            System.out.print("Enter the amount of " + item.getShareCode() + " shares you want to buy : ");
            try {
                int amount = Integer.parseInt(getUserInput());
                item.setNumOfShares(item.getNumOfShares() + amount);
                System.out.println("Records successfully updated.");
                break loop;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid amount");
            }
        }
    }
    //function to sell existing shares
    private static void sellShares(Portfolio portfolio, PortfolioShare item) {
        loop:
        while (true) {
            System.out.print("Enter the amount of " + item.getShareCode() + " shares you want to sell : ");
            try {
                int amount = Integer.parseInt(getUserInput());
                if (amount > item.getNumOfShares()) {
                    System.out.println("Insufficient balance for that transaction");
                    continue;
                }
                item.setNumOfShares(item.getNumOfShares() - amount);
                System.out.println("Records successfully updated.");
                break loop;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid amount");
            }
        }
    }
    //function for the report section
    private static void portfolioReport() {
        getNumOfUsers();

    }
    //function to get the choice of the user on number of reports
    private static void getNumOfUsers() {
        loop:
        while (true) {
            System.out.println("1.Single User");
            System.out.println("2.All Users");
            System.out.println("3.Go Back");
            System.out.print("Select an option and press enter : ");
            try {
                int choice = Integer.parseInt(getUserInput());
                switch (choice) {
                    case 1 -> singleUserReport();
                    case 2 -> allUserReport();
                    case 3 -> {
                        break loop;
                    }
                    default -> System.out.println("Please enter valid number between 1 and 3\n");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter valid number between 1 and 3 \n");
            }
        }
    }

    private static void allUserReport() {
        for (Shareholder x : shareholders) {
            Portfolio portfolio = getPortfolioData(x.getPortfolioId());
            createTable(portfolio, x);
        }
    }

    private static void singleUserReport() {
        System.out.print("Enter the name of the user : ");
        String name = getUserInput().toLowerCase();
        Shareholder shareholder = getShareholderData(name);
        if (shareholder != null) {
            Portfolio portfolio = getPortfolioData(shareholder.getPortfolioId());
            if (portfolio != null)
                createTable(portfolio, shareholder);
            else
                System.out.println("No Portfolio data available");
        } else
            System.out.println("No such user exists.");


    }

    private static void createTable(Portfolio portfolio, Shareholder shareholder) {
        System.out.println("\n\nPORTFOLIO REPORT for " + shareholder.getFirstname() + " " + shareholder.getSurname());
        System.out.println(shareholder.getAddress());
        System.out.println(shareholder.getPortfolioId() + ", " + java.time.LocalDate.now() + "\n");
        ArrayList<PortfolioShare> shareList = portfolio.getShares();

        Table table = new Table();
        table.setShowVerticalLines(true);
        table.setHeaders("Share Code", "Company name", "Number of Shares", "Share Price($)", "Shares Total($)");
        int totShare = 0;
        double totSharePrice = 0;
        for (PortfolioShare x : shareList) {
            Share share = getSharesData(x.getShareCode());
            totShare += x.getNumOfShares();
            totSharePrice += (x.getNumOfShares() * share.getPrice());
            table.addRow(x.getShareCode(), share.getCompany(), Integer.toString(x.getNumOfShares()), Double.toString(share.getPrice()), Double.toString((x.getNumOfShares() * share.getPrice())));
        }
        table.addRow("Total", "", Double.toString(totShare), "", Double.toString(totSharePrice));
        table.print();
    }

    private static Shareholder getShareholderData(String name) {
        for (Shareholder shareholder : shareholders) {
            if (Objects.equals((shareholder.getFirstname().toLowerCase() + " " + shareholder.getSurname().toLowerCase()), name)) {
                return shareholder;
            }
        }
        return null;
    }

    private static Share getSharesData(String name) {
        for (Share share : shares) {
            if (Objects.equals(share.getShareCode(), name)) {
                return share;
            }
        }
        return null;
    }

    private static Portfolio getPortfolioData(String pId) {
        for (Portfolio portfolio : portfolios) {
            if (Objects.equals(portfolio.getpId(), pId)) {
                return portfolio;
            }
        }
        return null;
    }

    private static PortfolioShare getPortfolioShareData(ArrayList<PortfolioShare> portfo, String shareCode) {
        for (PortfolioShare portfolio : portfo) {
            if (Objects.equals(portfolio.getShareCode(), shareCode)) {
                return portfolio;
            }
        }
        return null;
    }

    private static void saveFiles() {
        saveShares();
        saveShareholders();
        savePortfolios();
    }


    private static void savePortfolios() {
        try {
            FileWriter writer = new FileWriter("src/portfolios.txt");
            for (Portfolio x : portfolios
            ) {
                StringBuilder temp = new StringBuilder(x.getpId());
                for (PortfolioShare y : x.getShares()
                ) {
                    temp.append(",").append(y.getShareCode()).append(",").append(y.getNumOfShares());
                }
                writer.write(new StringBuilder().append(temp).append("\n").toString());
            }
            writer.close();
            System.out.println("portfolios file written successfully.\n\n\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void saveShareholders() {
        try {
            FileWriter writer = new FileWriter("src/shareholders.txt");
            for (Shareholder x : shareholders
            ) {
                writer.write(x.getCustomerId() + "," + x.getFirstname() + " " + x.getSurname() + "," + x.getAddress() + "," + x.getPhone() + "," + x.getPortfolioId() + "\n");
            }
            writer.close();
            System.out.println("shareholders file written successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void saveShares() {
        try {
            FileWriter writer = new FileWriter("src/shares.txt");
            for (Share x : shares
            ) {
                writer.write(x.getShareCode() + "," + x.getCompany() + "," + x.getPrice() + "\n");
            }
            writer.close();
            System.out.println("shares file written successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private static void exitProgram() {
        System.out.println("The program has terminated successfully.\nThank you");
        System.exit(1);
    }

}


