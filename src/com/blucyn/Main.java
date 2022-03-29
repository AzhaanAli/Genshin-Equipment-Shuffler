package com.blucyn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Artifact> allArtifacts = new ArrayList<>();


    public static void main(String[] args) {

        addArtifacts();
//        addCsvArtifacts();

        System.out.println("[Builder]: " + "Who do you want to build?");
        System.out.println("1 --> Childe");
        System.out.println("2 --> Raiden");
        System.out.println("3 --> Beidou");
        System.out.println("4 --> Xinqiu");
        System.out.println("5 --> Eula");
        System.out.println("6 --> Hu Tao");
        System.out.println("7 --> Chongyun");
        System.out.println("8 --> Albedo");
        System.out.println("9 --> Zhongli");
        System.out.println();
        System.out.print("Answer: ");
        try {
            switch (Integer.parseInt(new Scanner(System.in).nextLine().toUpperCase())) {
                case 1 -> new ChildeBuilder(allArtifacts).run();
                case 2 -> new RaidenBuilder(allArtifacts).run();
                case 3 -> new BeidouBuilder(allArtifacts).run();
                case 4 -> new XinqiuBuilder(allArtifacts).run();
                case 5 -> new EulaBuilder  (allArtifacts).run();
                case 6 -> new HuTaoBuilder (allArtifacts).run();
                case 7 -> new ChongyunBuilder(allArtifacts).run();
                case 8 -> new AlbedoBuilder(allArtifacts).run();
                case 9 -> new ZhongliBuilder(allArtifacts).run();
            }
        }catch (Exception ignored){

        }


    }

    public static void addArtifacts(){


        // Add artifacts.
        // Add all flowers.
        allArtifacts.add(new Artifact(
                1,
                "HEART_OF_DEPTH",
                "HP", 4780.0,
                "CRIT_DMG", 0.07,
                "CRIT_RATE", 0.117,
                "ATK", 14.0,
                "ENERGY_RECHARGE", .175
        ));
        allArtifacts.add(new Artifact(
                1,
                "BLOODSTAINED_CHIVALRY",
                "HP", 4780.0,
                "CRIT_DMG", 0.272,
                "CRIT_RATE", 0.078,
                "ATK%", .053,
                "ELEMENTAL_MASTERY", 44
        ));
        allArtifacts.add(new Artifact(
                1,
                "EMBLEM_OF_SEVERED_FATE",
                "HP", 4780.0,
                "ATK", 33.0,
                "ENERGY_RECHARGE", .045,
                "CRIT_RATE", 0.109,
                "CRIT_DMG", 0.148
        ));
        allArtifacts.add(new Artifact(
                1,
                "EMBLEM_OF_SEVERED_FATE",
                "CRIT_RATE", 0.027,
                "ATK%", .134,
                "ELEMENTAL_MASTERY", 51,
                "CRIT_DMG", .14,
                "HP", 4780
        ));
        allArtifacts.add(new Artifact(
                1,
                "TENACITY_OF_THE_MILILITH",
                "HP", 4780.0,
                "DEF", 37.0,
                "CRIT_RATE", 0.074,
                "CRIT_DMG", 0.225,
                "ENERGY_RECHARGE", 0.045
        ));
        allArtifacts.add(new Artifact(
                1,
                "THUNDERING_FURY",
                "HP", 4780.0,
                "ATK", 18.0,
                "CRIT_RATE", 0.074,
                "ATK%", 0.105,
                "CRIT_DMG", 0.202
        ));
        allArtifacts.add(new Artifact(
                1,
                "BOLIDE",
                "HP", 4780.0,
                "CRIT_RATE", 0.062,
                "CRIT_DMG", 0.21,
                "ENERGY_RECHARGE", 0.104,
                "DEF", 19.0
        ));
        allArtifacts.add(new Artifact(
                1,
                "WANDERERS_TROUPE",
                "HP", 4780.0,
                "DEF", 39.0,
                "HP%", 0.041,
                "CRIT_DMG", 0.303,
                "CRIT_RATE", 0.062
        ));
        allArtifacts.add(new Artifact(
                1,
                "CRIMSON_WITCH_OF_FLAMES",
                "HP", 4780.0,
                "ELEMENTAL_MASTERY", 21.0,
                "CRIT_DMG", 0.225,
                "ENERGY_RECHARGE", 0.11,
                "ATK%", 0.093
        ));
        allArtifacts.add(new Artifact(
                1,
                "NOBLESSE_OBLIGE",
                "HP", 4780.0,
                "CRIT_RATE", 0.027,
                "CRIT_DMG", 0.179,
                "ENERGY_RECHARGE", 0.045,
                "ATK%", 0.163
        ));
        allArtifacts.add(new Artifact(
                1,
                "SHIMENAWAS_REMINISCENCE",
                "HP", 4780.0,
                "CRIT_DMG", 0.163,
                "HP%", 0.087,
                "ENERGY_RECHARGE", 0.065,
                "DEF", 35.0
        ));
        allArtifacts.add(new Artifact(
                1,
                "SHIMENAWAS_REMINISCENCE",
                "HP", 4780.0,
                "CRIT_DMG", 0.21,
                "ATK%", 0.041,
                "CRIT_RATE", 0.031,
                "DEF%", .16
        ));
        allArtifacts.add(new Artifact(
                1,
                "VIRIDESCENT",
                "HP", 4780.0,
                "ELEMENTAL_MASTERY", 47.0,
                "CRIT_DMG", 0.155,
                "DEF%", 0.19,
                "CRIT_RATE", 0.035
        ));
        allArtifacts.add(new Artifact(
                1,
                "HEART_OF_DEPTH",
                "HP", 4780.0,
                "CRIT_DMG", 0.14,
                "ELEMENTAL_MASTERY", 54.0,
                "ENERGY_RECHARGE", 0.123,
                "ATK%", 0.053
        ));
        allArtifacts.add(new Artifact(
                1,
                "ARCHAIC_PETRA",
                "HP", 4780.0,
                "ENERGY_RECHARGE", 0.214,
                "ATK%", 0.099,
                "CRIT_DMG", 0.07,
                "ELEMENTAL_MASTERY", 23.0
        ));
        allArtifacts.add(new Artifact(
                1,
                "PALE_FLAME",
                "HP", 4780.0,
                "ENERGY_RECHARGE", 0.201,
                "ATK", 19,
                "CRIT_DMG", 0.054,
                "CRIT_RATE", .066
        ));
        allArtifacts.add(new Artifact(
                1,
                "MAIDEN_BELOVED",
                "HP", 4780.0,
                "ELEMENTAL_MASTERY", 58.0,
                "DEF", 21.0,
                "ENERGY_RECHARGE", 0.162,
                "HP%", 0.047
        ));

        // Add all feathers.
        allArtifacts.add(new Artifact(
                2,
                "HEART_OF_DEPTH",
                "ATK", 311.0,
                "ATK%", 0.146,
                "CRIT_RATE", 0.136,
                "DEF", 23.0,
                "ENERGY_RECHARGE", 0.058
        ));
        allArtifacts.add(new Artifact(
                2,
                "PALE_FLAME",
                "ATK", 311.0,
                "CRIT_DMG", .202,
                "CRIT_RATE", 0.066,
                "HP", 448,
                "HP%", .047
        ));
        allArtifacts.add(new Artifact(
                2,
                "BLIZZARD_STRAYER",
                "ATK", 311.0,
                "CRIT_DMG", .187,
                "CRIT_RATE", 0.027,
                "ELEMENTAL_MASTERY", 33,
                "HP%", .099
        ));
        allArtifacts.add(new Artifact(
                2,
                "NOBLESSE_OBLIGE",
                "ATK", 311.0,
                "CRIT_RATE", 0.066,
                "ENERGY_RECHARGE", 0.065,
                "DEF%", .233,
                "CRIT_DMG", 0.132
        ));
        allArtifacts.add(new Artifact(
                2,
                "CRIMSON_WITCH_OF_FLAMES",
                "ATK", 311.0,
                "HP", 299.0,
                "ATK%", 0.158,
                "CRIT_DMG", 0.132,
                "CRIT_RATE", 0.082
        ));
        allArtifacts.add(new Artifact(
                2,
                "THUNDERING_FURY",
                "ATK", 311.0,
                "DEF%", 0.175,
                "ELEMENTAL_MASTERY", 19.0,
                "CRIT_DMG", 0.202,
                "CRIT_RATE", 0.062
        ));
        allArtifacts.add(new Artifact(
                2,
                "WANDERERS_TROUPE",
                "ATK", 311.0,
                "ATK%", 0.111,
                "CRIT_DMG", 0.187,
                "CRIT_RATE", 0.066,
                "HP", 508.0
        ));
        allArtifacts.add(new Artifact(
                2,
                "VIRIDESCENT",
                "ATK", 311.0,
                "ENERGY_RECHARGE", 0.214,
                "CRIT_DMG", 0.109,
                "ELEMENTAL_MASTERY", 19.0,
                "CRIT_RATE", 0.054
        ));
        allArtifacts.add(new Artifact(
                2,
                "GLADIATORS_FINALE",
                "ATK", 311.0,
                "CRIT_DMG", 0.225,
                "ENERGY_RECHARGE", 0.175,
                "CRIT_RATE", 0.031,
                "DEF", 42.0
        ));
        allArtifacts.add(new Artifact(
                2,
                "MAIDENS",
                "ATK", 311.0,
                "DEF", 23.0,
                "CRIT_DMG", 0.257,
                "HP%", 0.14,
                "CRIT_RATE", 0.031
        ));
        allArtifacts.add(new Artifact(
                2,
                "EMBLEM_OF_SEVERED_FATE",
                "ATK", 311.0,
                "HP", 269.0,
                "ATK%", 0.047,
                "ENERGY_RECHARGE", 0.175,
                "CRIT_DMG", 0.202
        ));
        allArtifacts.add(new Artifact(
                2,
                "SHIMENAWAS_REMINISCENCE",
                "ATK", 311.0,
                "CRIT_DMG", 0.194,
                "ENERGY_RECHARGE", 0.045,
                "CRIT_RATE", 0.109,
                "DEF", 19.0
        ));
        allArtifacts.add(new Artifact(
                2,
                "TENACITY_OF_THE_MILILITH",
                "ATK", 311.0,
                "CRIT_DMG", 0.194,
                "DEF", 16.0,
                "ENERGY_RECHARGE", 0.117,
                "ELEMENTAL_MASTERY", 40.0
        ));
        allArtifacts.add(new Artifact(
                2,
                "NOBLESSE_OBLIGE",
                "ATK", 311.0,
                "ATK%", 0.047,
                "ENERGY_RECHARGE", 0.091,
                "CRIT_RATE", 0.066,
                "CRIT_DMG", 0.218
        ));
        allArtifacts.add(new Artifact(
                2,
                "CRIMSON_WITCH_OF_FLAMES",
                "ATK", 311.0,
                "HP", 598.0,
                "CRIT_DMG", 0.171,
                "CRIT_RATE", 0.039,
                "ENERGY_RECHARGE", 0.091
        ));
        allArtifacts.add(new Artifact(
                2,
                "MAIDENS_BELOVED",
                "ATK", 311.0,
                "HP", 807.0,
                "DEF%", 0.066,
                "HP%", 0.152,
                "ELEMENTAL_MASTERY", 16.0
        ));
        allArtifacts.add(new Artifact(
                2,
                "GLADIATORS_FINALE",
                "ATK", 311.0,
                "HP", 717.0,
                "ATK%", 0.105,
                "CRIT_DMG", 0.054,
                "CRIT_RATE", 0.101
        ));

        // Add all hourglasses.
        allArtifacts.add(new Artifact(
                3,
                "CRIMSON_WITCH_OF_FLAMES",
                "ATK%", 0.466,
                "HP", 717.0,
                "CRIT_RATE", 0.101,
                "ELEMENTAL_MASTERY", 23.0,
                "DEF%", 0.131
        ));
        allArtifacts.add(new Artifact(
                3,
                "WANDERERS_TROUPE",
                "HP%", .466,
                "ATK%", 0.047,
                "ENERGY_RECHARGE", 0.227,
                "ELEMENTAL_MASTERY", 42.0,
                "CRIT_DMG", 0.14
        ));
        allArtifacts.add(new Artifact(
                3,
                "WANDERERS_TROUPE",
                "ENERGY_RECHARGE", 0.518,
                "ELEMENTAL_MASTERY", 84.0,
                "CRIT_DMG", 0.117,
                "ATK", 14.0,
                "CRIT_RATE", 0.07
        ));
        allArtifacts.add(new Artifact(
                3,
                "GLADIATORS_FINALE",
                "ATK%", 0.466,
                "CRIT_RATE", 0.066,
                "HP", 269.0,
                "ATK", 14.0,
                "CRIT_DMG", 0.326
        ));
        allArtifacts.add(new Artifact(
                3,
                "EMBLEM_OF_SEVERED_FATE",
                "ATK%", 0.466,
                "ELEMENTAL_MASTERY", 42,
                "CRIT_RATE", 0.101,
                "DEF", 16,
                "ENERGY_RECHARGE", 0.104
        ));
        allArtifacts.add(new Artifact(
                3,
                "SHIMENAWAS_REMINISCENCE",
                "ATK%", 0.466,
                "ENERGY_RECHARGE", 0.045,
                "ELEMENTAL_MASTERY", 58.0,
                "ATK", 53.0,
                "DEF%", 0.066
        ));
        allArtifacts.add(new Artifact(
                3,
                "TENACITY_OF_THE_MILILITH",
                "HP%", 0.466,
                "HP", 448.0,
                "CRIT_RATE", 0.058,
                "ATK%", 0.099,
                "ELEMENTAL_MASTERY", 33.0
        ));
        allArtifacts.add(new Artifact(
                3,
                "HEART_OF_DEPTH",
                "ENERGY_RECHARGE", 0.518,
                "CRIT_RATE", 0.039,
                "HP", 777.0,
                "CRIT_DMG", 0.21,
                "ELEMENTAL_MASTERY", 23.0
        ));
        allArtifacts.add(new Artifact(
                3,
                "ARCHAIC_PETRA",
                "DEF%", 0.583,
                "ELEMENTAL_MASTERY", 77.0,
                "DEF", 16.0,
                "CRIT_RATE", 0.035,
                "ATK%", 0.099
        ));
        allArtifacts.add(new Artifact(
                3,
                "BLOODSTAINED_CHIVALRY",
                "ATK%", 0.466,
                "CRIT_RATE", 0.113,
                "CRIT_DMG", 0.062,
                "DEF%", 0.109,
                "ELEMENTAL_MASTERY", 35.0
        ));
        allArtifacts.add(new Artifact(
                3,
                "NOBLESSE_OBLIGE",
                "HP%", 0.466,
                "CRIT_DMG", 0.07,
                "ELEMENTAL_MASTERY", 40.0,
                "ENERGY_RECHARGE", 0.155,
                "DEF%", 0.146
        ));
        allArtifacts.add(new Artifact(
                3,
                "NOBLESSE_OBLIGE",
                "ATK%", 0.466,
                "ATK", 37.0,
                "CRIT_RATE", 0.039,
                "ELEMENTAL_MASTERY", 40.0,
                "CRIT_DMG", 0.218
        ));
        allArtifacts.add(new Artifact(
                3,
                "NOBLESSE_OBLIGE",
                "ENERGY_RECHARGE", 0.518,
                "DEF%", 0.066,
                "ATK", 27.0,
                "CRIT_RATE", 0.101,
                "ELEMENTAL_MASTERY", 37.0
        ));
        allArtifacts.add(new Artifact(
                3,
                "CRIMSON_WITCH_OF_FLAMES",
                "HP%", 0.466,
                "DEF%", 0.111,
                "CRIT_DMG", 0.062,
                "ATK%", 0.099,
                "ELEMENTAL_MASTERY", 65.0
        ));
        allArtifacts.add(new Artifact(
                3,
                "WANDERERS_TROUPE",
                "ELEMENTAL_MASTERY", 187.0,
                "CRIT_RATE", 0.132,
                "CRIT_DMG", 0.062,
                "HP%", 0.053,
                "ATK%", 0.105
        ));
        allArtifacts.add(new Artifact(
                3,
                "VIRIDESCENT",
                "ELEMENTAL_MASTERY", 187.0,
                "ATK%", 0.128,
                "DEF", 46.0,
                "CRIT_RATE", 0.039,
                "DEF%", 0.131
        ));
        allArtifacts.add(new Artifact(
                3,
                "GLADIATORS_FINALE",
                "ENERGY_RECHARGE", 0.518,
                "DEF", 21.0,
                "CRIT_RATE", 0.101,
                "HP%", 0.163,
                "CRIT_DMG", 0.062
        ));
        allArtifacts.add(new Artifact(
                3,
                "BLIZZARD_STRAYER",
                "ELEMENTAL_MASTERY", 187.0,
                "CRIT_DMG", 0.194,
                "ENERGY_RECHARGE", 0.123,
                "CRIT_RATE", 0.066,
                "DEF%", 0.051
        ));

        // Add all goblets.
        allArtifacts.add(new Artifact(
                4,
                "WANDERERS_TROUPE",
                "ATK%", 0.466,
                "CRIT_DMG", 0.124,
                "CRIT_RATE", 0.066,
                "HP", 777.0,
                "DEF%", 0.124
        ));
        allArtifacts.add(new Artifact(
                4,
                "VIRIDESCENT",
                "ELEMENTAL_MASTERY", 187.0,
                "HP", 1046.0,
                "CRIT_DMG", 0.132,
                "HP%", 0.047,
                "ATK%", 0.093
        ));
        allArtifacts.add(new Artifact(
                4,
                "VIRIDESCENT",
                "HYDRO_DMG_BONUS", 0.466,
                "ENERGY_RECHARGE", 0.11,
                "DEF%", 0.051,
                "CRIT_DMG", 0.264,
                "ATK%", 0.105
        ));
        allArtifacts.add(new Artifact(
                4,
                "GLADIATORS_FINALE",
                "ELECTRO_DMG_BONUS", 0.466,
                "HP", 717.0,
                "CRIT_RATE", 0.07,
                "ATK%", 0.146,
                "ENERGY_RECHARGE", 0.052
        ));
        allArtifacts.add(new Artifact(
                4,
                "MAIDEN_BELOVED",
                "HYDRO_DMG_BONUS", 0.466,
                "CRIT_RATE", 0.07,
                "ENERGY_RECHARGE", 0.136,
                "ELEMENTAL_MASTERY", 37.0,
                "CRIT_DMG", 0.14
        ));
        allArtifacts.add(new Artifact(
                4,
                "MAIDEN_BELOVED",
                "HP%", 0.466,
                "ELEMENTAL_MASTERY", 23.0,
                "ENERGY_RECHARGE", .168,
                "ATK%", 0.187,
                "DEF", 19.0
        ));
        allArtifacts.add(new Artifact(
                4,
                "EMBLEM_OF_SEVERED_FATE",
                "ATK%", 0.466,
                "ATK", 16.0,
                "DEF", 37.0,
                "HP", 777.0,
                "CRIT_RATE", 0.058
        ));
        allArtifacts.add(new Artifact(
                4,
                "TENACITY_OF_THE_MILILITH",
                "HP%", 0.466,
                "ATK%", 0.087,
                "CRIT_DMG", 0.14,
                "CRIT_RATE", 0.058,
                "HP", 538.0
        ));
        allArtifacts.add(new Artifact(
                4,
                "TENACITY_OF_THE_MILILITH",
                "GEO_DMG_BONUS", 0.466,
                "ATK", 14.0,
                "CRIT_DMG", 0.14,
                "HP", 807.0,
                "ATK%", 0.111
        ));
        allArtifacts.add(new Artifact(
                4,
                "ARCHAIC_PETRA",
                "ELECTRO_DMG_BONUS", 0.466,
                "CRIT_RATE", 0.14,
                "ATK%", 0.099,
                "ATK", 16.0,
                "DEF", 23.0
        ));
        allArtifacts.add(new Artifact(
                4,
                "BLOODSTAINED_CHIVALRY",
                "PHYSICAL_DMG_BONUS", 0.583,
                "HP", 239.0,
                "CRIT_DMG", 0.132,
                "CRIT_RATE", 0.128,
                "DEF", 21.0
        ));
        allArtifacts.add(new Artifact(
                4,
                "BLOODSTAINED_CHIVALRY",
                "ANEMO_DMG_BONUS", 0.583,
                "HP", 508,
                "CRIT_DMG", .218,
                "CRIT_RATE", .066,
                "DEF%", .124
        ));
        allArtifacts.add(new Artifact(
                4,
                "CRIMSON_WITCH_OF_FLAMES",
                "ANEMO_DMG_BONUS", 0.466,
                "ENERGY_RECHARGE", 0.123,
                "ELEMENTAL_MASTERY", 40.0,
                "ATK%", 0.053,
                "DEF%", 0.19
        ));
        allArtifacts.add(new Artifact(
                4,
                "WANDERERS_TROUPE",
                "ANEMO_DMG_BONUS", 0.466,
                "HP%", 0.041,
                "ELEMENTAL_MASTERY", 42.0,
                "ENERGY_RECHARGE", 0.155,
                "CRIT_DMG", 0.14
        ));
        allArtifacts.add(new Artifact(
                4,
                "WANDERERS_TROUPE",
                "ELEMENTAL_MASTERY", 187,
                "ATK", 33,
                "DEF", 39,
                "CRIT_RATE", 0.097,
                "CRIT_DMG", 0.078
        ));
        allArtifacts.add(new Artifact(
                4,
                "GLADIATORS_FINALE",
                "CRYO_DMG_BONUS", 0.466,
                "CRIT_RATE", 0.109,
                "ATK", .190,
                "CRIT_DMG", 0.124,
                "HP%", 0.087
        ));
        allArtifacts.add(new Artifact(
                4,
                "MAIDEN_BELOVED",
                "DEF%", 0.583,
                "CRIT_RATE", 0.027,
                "ATK", 16.0,
                "CRIT_DMG", 0.21,
                "ELEMENTAL_MASTERY", 65.0
        ));
        allArtifacts.add(new Artifact(
                4,
                "MAIDEN_BELOVED",
                "HP%", 0.466,
                "CRIT_RATE", 0.058,
                "DEF", 35.0,
                "ELEMENTAL_MASTERY", 65.0,
                "DEF%", 0.068
        ));
        allArtifacts.add(new Artifact(
                4,
                "BLIZZARD_STRAYER",
                "PYRO_DMG_BONUS", 0.466,
                "CRIT_DMG", 0.155,
                "CRIT_RATE", 0.031,
                "ATK", 35.0,
                "ENERGY_RECHARGE", 0.155
        ));
        allArtifacts.add(new Artifact(
                4,
                "SHIMENAWAS_REMINISCENCE",
                "ATK%", 0.466,
                "CRIT_DMG", 0.109,
                "CRIT_RATE", 0.074,
                "DEF%", 0.19,
                "HP%", 0.105
        ));

        // Add all circlets.
        allArtifacts.add(new Artifact(
                5,
                "EMBLEM_OF_SEVERED_FATE",
                "CRIT_DMG", 0.622,
                "ENERGY_RECHARGE", 0.058,
                "DEF", 23.0,
                "CRIT_RATE", 0.101,
                "ELEMENTAL_MASTERY", 77.0
        ));
        allArtifacts.add(new Artifact(
                5,
                "GLADIATORS_FINALE",
                "CRIT_DMG", .155,
                "ATK%", .087,
                "DEF", 21,
                "CRIT_RATE", .311,
                "ELEMENTAL_MASTERY", 56
        ));
        allArtifacts.add(new Artifact(
                5,
                "ARCHAIC_PETRA",
                "CRIT_DMG", 0.622,
                "CRIT_RATE", 0.066,
                "ATK", 31.0,
                "ATK%", 0.117,
                "ENERGY_RECHARGE", 0.181
        ));
        allArtifacts.add(new Artifact(
                5,
                "THUNDERING_FURY",
                "ATK%", 0.466,
                "CRIT_RATE", 0.066,
                "HP%", 0.146,
                "CRIT_DMG", 0.194,
                "HP", 299.0
        ));
        allArtifacts.add(new Artifact(
                5,
                "WANDERERS_TROUPE",
                "HP%", 0.466,
                "CRIT_RATE", 0.105,
                "ENERGY_RECHARGE", 0.117,
                "HP", 598.0,
                "CRIT_DMG", 0.132
        ));
        allArtifacts.add(new Artifact(
                5,
                "WANDERERS_TROUPE",
                "CRIT_RATE", 0.311,
                "ATK", 33.0,
                "CRIT_DMG", 0.187,
                "ENERGY_RECHARGE", 0.097,
                "DEF", 42.0
        ));
        allArtifacts.add(new Artifact(
                5,
                "EMBLEM_OF_SEVERED_FATE",
                "CRIT_RATE", 0.311,
                "HP", 209,
                "ELEMENTAL_MASTERY", 54.0,
                "ATK%", 0.093,
                "DEF", 42
        ));
        allArtifacts.add(new Artifact(
                5,
                "SHIMENAWAS_REMINISCENCE",
                "ELEMENTAL_MASTERY", 187.0,
                "CRIT_DMG", 0.062,
                "CRIT_RATE", 0.07,
                "DEF%", .204,
                "HP", 418.0
        ));
        allArtifacts.add(new Artifact(
                5,
                "SHIMENAWAS_REMINISCENCE",
                "CRIT_DMG", 0.622,
                "ENERGY_RECHARGE", .058,
                "ELEMENTAL_MASTERY", 56,
                "CRIT_RATE", 0.105,
                "DEF", 16
        ));
        allArtifacts.add(new Artifact(
                5,
                "HEART_OF_DEPTH",
                "CRIT_DMG", 0.622,
                "HP", 538.0,
                "ENERGY_RECHARGE", 0.11,
                "ATK%", 0.111,
                "DEF%", 0.109
        ));
        allArtifacts.add(new Artifact(
                5,
                "HEART_OF_DEPTH",
                "CRIT_RATE", 0.311,
                "ATK", 51.0,
                "HP", 239.0,
                "ENERGY_RECHARGE", 0.149,
                "ELEMENTAL_MASTERY", 16.0
        ));
        allArtifacts.add(new Artifact(
                5,
                "NOBLESSE_OBLIGE",
                "ATK%", 0.466,
                "CRIT_RATE", 0.074,
                "ATK", 39.0,
                "DEF%", 0.058,
                "HP", 896.0
        ));
        allArtifacts.add(new Artifact(
                5,
                "NOBLESSE_OBLIGE",
                "CRIT_RATE", 0.311,
                "ELEMENTAL_MASTERY", 19.0,
                "HP%", 0.047,
                "CRIT_DMG", 0.28,
                "ATK", 31.0
        ));
        allArtifacts.add(new Artifact(
                5,
                "CRIMSON_WITCH_OF_FLAMES",
                "CRIT_RATE", 0.311,
                "ENERGY_RECHARGE", 0.168,
                "DEF", 5.1,
                "CRIT_DMG", 0.148,
                "HP", 508.0
        ));
        allArtifacts.add(new Artifact(
                5,
                "WANDERERS_TROUPE",
                "ATK%", 0.466,
                "CRIT_RATE", 0.035,
                "ENERGY_RECHARGE", 0.104,
                "CRIT_DMG", 0.179,
                "DEF", 42.0
        ));
        allArtifacts.add(new Artifact(
                5,
                "VIRIDESCENT",
                "CRIT_RATE", 0.311,
                "HP%", 0.181,
                "CRIT_DMG", 0.124,
                "HP", 239.0,
                "DEF%", 0.051
        ));
        allArtifacts.add(new Artifact(
                5,
                "MAIDEN_BELOVED",
                "HEALING_BONUS", 0.359,
                "HP", 807.0,
                "ATK", 33.0,
                "CRIT_RATE", 0.058,
                "ENERGY_RECHARGE", 0.058
        ));
        allArtifacts.add(new Artifact(
                5,
                "MAIDEN_BELOVED",
                "HEALING_BONUS", 0.359,
                "ENERGY_RECHARGE", 0.117,
                "DEF", 37.0,
                "HP%", 0.111,
                "ATK", 29.0
        ));
        allArtifacts.add(new Artifact(
                5,
                "BLIZZARD_STRAYER",
                "CRIT_RATE", 0.311,
                "ATK%", 0.105,
                "ELEMENTAL_MASTERY", 23.0,
                "CRIT_DMG", 0.194,
                "ENERGY_RECHARGE", 0.11
        ));
        allArtifacts.add(new Artifact(
                5,
                "SHIMENAWAS_REMINISCENCE",
                "CRIT_RATE", 0.035,
                "ATK%", 0.466,
                "ELEMENTAL_MASTERY", 0,
                "CRIT_DMG", 0,
                "ENERGY_RECHARGE", 0
        ));



    }
    public static void addCsvArtifacts(){

        String line = "";
        String splitBy = ",";
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("src/com/blucyn/Artifacts.csv"));
            br.readLine();
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] artString = line.split(splitBy);    // use comma as separator
                int artType = Integer.parseInt(artString[0]);
                String setName = formatSetName(artString[1]);
                double atkFlat = Double.parseDouble(artString[2]);
                double atkPercent = Double.parseDouble(artString[3]) / 100;
                double critDmg = Double.parseDouble(artString[4]) / 100;
                double critRate = Double.parseDouble(artString[5]) / 100;
                double energy = Double.parseDouble(artString[6]) / 100;
                double hydroBonus = Double.parseDouble(artString[7]) / 100;
                double pyroBonus = Double.parseDouble(artString[8]) / 100;
                double electroBonus = Double.parseDouble(artString[9]) / 100;


                Artifact a = new Artifact(artType, setName);
                a.addStat("ATK", atkFlat);
                a.addStat("ATK%", atkPercent);
                a.addStat("CRIT_DMG", critDmg);
                a.addStat("CRIT_RATE", critRate);
                a.addStat("ENERGY_RECHARGE", energy);
                a.addStat("HYDRO_DMG_BONUS", hydroBonus);
                a.addStat("PYRO_DMG_BONUS", pyroBonus);
                a.addStat("ELECTRO_DMG_BONUS", electroBonus);

                allArtifacts.add(a);

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static String formatSetName(String setName){

        StringBuilder formatted = new StringBuilder();
        for(char c : setName.toUpperCase().toCharArray())
        {
            if(c == ' ')
            {
                formatted.append('_');

            }else if(c >= 65 && c <= 90)
            {
                formatted.append(c);
            }
        }
        return formatted.toString();

    }

    public static void convertAllArtifactSets(String convertTo){

        for(Artifact a : allArtifacts)
        {
            a.setName = convertTo;
        }

    }
    public static void convertAllArtifactSets(ArrayList<Artifact> allArtifacts, String convertTo){

        for(Artifact a : allArtifacts)
        {
            a.setName = convertTo;
        }

    }



}
