package com.company;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Ask for values
        Scanner scan = new Scanner(System.in);

        //this allows the simulation to run the specified amount of simulated days (one play session)
        System.out.println("How many days would you like to simulate?");
        int days = scan.nextInt();
	    int daysCompleted = 0;

	    //this allows the simulation to run for the specified amount of time each day (minutes)
        System.out.println("How many minutes would you like each day?");
        int time = scan.nextInt();

        //asks how many players there will be to exclude unavailable methods
        System.out.println("How many players will there be?");
        int players = scan.nextInt();

        //track max money
        int maxProfit = 0;
        ArrayList<Integer> bestPath = new ArrayList<>();

	    //loop will run based on amount of days specified ---------------------------------------------------------- days------------
        while (daysCompleted < days) {

            int totalProfit = 0; //Money made so far
            int maxTime = time; //Max amount of simulated time the simulation can run
            int timeRun = 0; //Amount of time simulated so far

            //array containing totalProfit [0] and timeRun [1] and players [2] followed by the 8 cool down times array[3] - array[10] followed by 2 resupply trackers array[11] - array[12] to pass to functions
            //the cool downs are to keep track of last time each method was run for checking cool downs (minutes)
            int[] valuesArray = new int[] {totalProfit, timeRun, players, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

            //track the order the methods were executed in
            ArrayList<Integer> orderOfExecution = new ArrayList<>();

            //loop will run until maxTime has been reached ----------------------------------------------------------each day---------
            while (timeRun <= maxTime) {

                //first, use the randomizer to choose a money making method
                int choice = Randomizer();


                // complete activity


                switch (choice) {
                    case 1:
                        valuesArray = CasinoHeist(valuesArray);
                        break;
                    case 2:
                        valuesArray = HeistUpdate(valuesArray);
                        break;
                    case 3:
                        valuesArray = DoomsdayHeist(valuesArray);
                        break;
                    case 4:
                        valuesArray = GunRunning(valuesArray);
                        break;
                    case 5:
                        valuesArray = Businesses(valuesArray);
                        break;
                    case 6:
                        valuesArray = Nightclub(valuesArray);
                        break;
                    case 7:
                        valuesArray = VehicleCargo(valuesArray);
                        break;
                    case 8:
                        valuesArray = SpecialCargo(valuesArray);
                        break;
                    default:
                        valuesArray[1] += 20;//waste time option
                        //System.out.println("Error: I don't know how we got here.");
                        break;
                }
                valuesArray[1] += 2;//add in a travel time between every task

                orderOfExecution.add(choice);
                totalProfit = valuesArray[0];
                timeRun = valuesArray[1];

                //temporary prints for testing
                //System.out.println(totalProfit);
                //System.out.println(timeRun);
                //System.out.println();
            }
            System.out.println(orderOfExecution);
            if(maxProfit < totalProfit) {
                maxProfit = totalProfit;
                bestPath.clear();
                bestPath = orderOfExecution;
            }

            daysCompleted++;
        }
        System.out.println(maxProfit);
        System.out.println(bestPath);

    }

    public static int Randomizer() {
        return (int) ((Math.random() * 8)+1);
    }

    //choice 1 casio heist -------------------------------------------------------------------------choice 1 casino heist------------------
    public static int[] CasinoHeist(int[] array) {
        int cooldown = 10;

        //check cool down (array [1] timeRun - array[4] cool down for casino heist
        if ((array[1]-array[4]) < cooldown) {return array;}

        //check player count > 2
        if (array[3] < 2) {return array;}

        //if the cool down checks out, simulate run
        array[0] += 797000;//profit
        array[1] += 157;//time run
        array[3] += 157;//set cool down
        return array;
    }

    //choice 2 Heist Update Heists ---------------------------------------------------------------choice 2 Heist Update Heists------------------
    public static int[] HeistUpdate(int[] array) {
        int cooldown = 15;

        //check cool down (array [1] timeRun - array[4] cool down for casino heist
        if ((array[1]-array[5]) < cooldown) {return array;}

        //check player count > 2
        if (array[3] < 4) {return array;}

        /*
        future plans:
        add randomizer to include all 5 heists
        include real data instead of placeholder data
         */

        //if the cool down checks out, simulate run
        array[0] += 125000;//profit
        array[1] += 90;//add time
        array[3] += 90;//cool down
        return array;
    }

    //choice 3 Doomsday Heists ----------------------------------------------------------------------choice 3 Doomsday Heists------------------
    public static int[] DoomsdayHeist(int[] array) {
        int cooldown = 15;

        //check cool down (array [1] timeRun - array[4] cool down for casino heist)
        if ((array[1]-array[5]) < cooldown && array[1] != 0) {return array;}

        //check player count > 2
        if (array[3] < 2) {return array;}

        /*
        future plans:
        add randomizer to include all 3 heists
        include real data instead of placeholder data
         */

        //if the cool down checks out, simulate run
        array[0] += 125000;//profit
        array[1] += 90;//add time
        array[3] += 90;//cool down
        return array;
    }

    //choice 4 Gun Running ----------------------------------------------------------------------------choice 4 Gun Running-----------------
    public static int[] GunRunning(int[] array) {
        int cooldown = 150;
        int stockNeeded = 5;

        //check cool down (array [1] timeRun - array[6] cool down for gun running)
        if ((array[1]-array[6]) < cooldown && array[1] != 0) {return array;}

        //check stock level array[11]
        if (array[11] < stockNeeded) {
            array[11]++;//add to stock level
            array[0] -= 75000;//subtract supply cost
            return array;
        }

        //if cool down is done and stock is full, sell
        array[0] += 1050000; //profit
        array[1] += 10; //sell time
        array[11] = 0; //reset stock

        return array;
    }

    //choice 5 MC Businesses ------------------------------------------------------------------------choice 5 MC Businesses-----------------
    public static int[] Businesses(int[] array) {
        int cooldown = 150;
        int stockNeeded = 5;

        //check cool down (array [1] timeRun - array[7] cool down for MC Businesses)
        if ((array[1]-array[7]) < cooldown && array[1] != 0) {return array;}

        //check stock level array[11]
        if (array[12] < stockNeeded) {
            array[12]++;//add to stock level
            array[0] -= 75000;//subtract supply cost
            array[7] = array[1];//set cool down
            return array;
        }

        //if cool down is done and stock is full, sell
        array[0] += 400000; //profit
        array[12] = 0; //reset stock
        array[7] = array[1];//set cool down

        //set time based on number of players
        if(array[2] == 1) {array[1] += 19;}
        else if(array[2]<4) {array[1] += 10;}
        else {array[1] += 5;}

        return array;
    }

    //choice 6 Nightclub ------------------------------------------------------------------------------choice 6 Nightclub-------------------
    public static int[] Nightclub(int[] array) {
        int cooldown = 150;

        //check cool down (array [1] timeRun - array[8] cool down for Nightclub)
        if ((array[1]-array[8]) < cooldown || array[1] == 0) {return array;}

        array[0] += 1000000;//profit
        array[1] += 10;//timeRun
        array[8] = array[1];//reset cool down

        return array;
    }

    //choice 7 Vehicle Cargo -------------------------------------------------------------------------choice 7 Vehicle Cargo-----------------
    public static int[] VehicleCargo(int[] array) {
        int cooldown = 30;

        //the amount of players affects every part of this (array[2])
        if (array[2] == 1) {
            //check cool down (array [1] timeRun - array[9] cool down for vehicle cargo) cool down - source time for 1 car
            if ((array[1]-array[9]) < cooldown-9) {return array;}
            array[0] += 77693; //profit
            array[1] += 12;//time taken

        }
        else if (array[2] == 2) {
            //check cool down (array [1] timeRun - array[9] cool down for vehicle cargo) cool down - source time for 2 car
            if ((array[1]-array[9]) < cooldown-18) {return array;}
            array[0] += 155388; //profit
            array[1] += 21;//time taken

        }
        else if (array[2] == 3) {
            //check cool down (array [1] timeRun - array[9] cool down for vehicle cargo) cool down - source time for 3 car
            if ((array[1]-array[9]) < cooldown-27) {return array;}
            array[0] += 233082; //profit
            array[1] += 30;//time taken

        }
        else {
            array[0] += 310776; //profit
            array[1] += 39;//time taken

        }
        array[9] = array[1]; //set cool down

        return array;
    }

    //choice 8 Special Cargo -------------------------------------------------------------------------choice 8 Special Cargo-----------------
    public static int[] SpecialCargo(int[] array) {
        //check the stock level (array[10]) must be 14(trips not actual stock), otherwise, increase stock
        if (array[10] < 14) {
            array[10]++;
            array[1] += 7;//set time
            return array;
        }

        // ready to sell
        array[10] = 0; //empty stock
        array[0] += 735000;//set money

        //set time based on amount of players
        if(array[2] == 1) {array[1] += 19;}
        else if(array[2]<4) {array[1] += 10;}
        else {array[1] += 5;}

        return array;
    }

}
