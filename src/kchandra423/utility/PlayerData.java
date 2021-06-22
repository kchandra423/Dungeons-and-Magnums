package kchandra423.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class PlayerData {
    static {
//        int coins1;
        intialRogueWeapon = "Pistol";
        initialMageWeapon = "MagicStaff";
        initalKnightWeapon = "RustySword";

        Scanner dataReader= null;
        try {
            dataReader = new Scanner(new File("res/Data/Coins.txt"));
            coins = dataReader.nextInt();
            dataReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            dataReader.close();
        }
//        ArrayList<String> knightWeapons1 = new ArrayList<>();
//        knightWeapons1.add("RustySword");
//        ArrayList<String> mageWeapons1 = new ArrayList<>();
//        mageWeapons1.add("MagicStaff");
//        ArrayList<String> rogueWeapons1 = new ArrayList<>();
//        rogueWeapons1.add("Pistol");
//        try {
//            Scanner dataReader = new Scanner(new BufferedReader(new FileReader("res/Data/PlayerStats")));
//            coins1 = Integer.parseInt(dataReader.nextLine());
//            String raw = dataReader.nextLine();
//            if (raw.contains("Broadsword")) {
//                knightWeapons1.add("Broadsword");
//            }
//            raw = dataReader.nextLine();
//            if(raw.contains("SpellBook")){
//                mageWeapons1.add("SpellBook");
//            }
//            raw = dataReader.nextLine();
//            if (raw.contains("SMG")) {
//                rogueWeapons1.add("SMG");
//            }
//            if (raw.contains("Shotgun")) {
//                rogueWeapons1.add("Shotgun");
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.err.println("Could not properly load player data");
//            coins1 = 0;
//        }
//        mageWeapons = mageWeapons1;
//        knightWeapons = knightWeapons1;
//        rogueWeapons = rogueWeapons1;
//        coins = coins1;
    }

    private static int coins;
//    private static final ArrayList<String> knightWeapons;
//    private static final ArrayList<String> mageWeapons;
//    private static final ArrayList<String> rogueWeapons;

    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int coins) {
        PlayerData.coins = coins;
    }

    //    static ArrayList<String> getKnightWeapons() {
//        return knightWeapons;
//    }
//
//    static ArrayList<String> getMageWeapons() {
//        return mageWeapons;
//    }
//
//    static ArrayList<String> getRogueWeapons() {
//        return rogueWeapons;
//    }
    private static String initialMageWeapon;
    private static String initalKnightWeapon;
    private static String intialRogueWeapon;

    public static String getInitialKnightWeapon() {
        return initalKnightWeapon;
    }

    public static String getInitialRogueWeapon() {
        return intialRogueWeapon;
    }

    public static String getInitialMageWeapon() {
        return initialMageWeapon;
    }

    public static void setInitialKnightWeapon(String initalKnightWeapon) {
        PlayerData.initalKnightWeapon = initalKnightWeapon;
    }

    public static void setInitialMageWeapon(String initialMageWeapon) {
        PlayerData.initialMageWeapon = initialMageWeapon;
    }

    public static void setInitialRogueWeapon(String intialRogueWeapon) {
        PlayerData.intialRogueWeapon = intialRogueWeapon;
    }
}
