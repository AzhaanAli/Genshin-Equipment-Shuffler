package com.blucyn;

import java.util.ArrayList;

public class Artifact {

    // ---------------------- //
    // Instance variables.

    // Describes the type of artifact.
    /*
      - 1 --> flower
      - 2 --> feather
      - 3 --> sands
      - 4 --> goblet
      - 5 --> circlet
     */
    public int type;

    // Set the artifact belongs to.
    public String setName;

    public ArrayList<String> statTags   = new ArrayList<>();
    public ArrayList<Double> statValues = new ArrayList<>();

    private final String[] POSSIBLE_STATS = {

            "ATK", "ATK%",
            "DEF", "DEF%",
            "HP" , "HP%",
            "CRIT_RATE", "CRIT_DMG",

            "ELEMENTAL_MASTERY",
            "ENERGY_RECHARGE",
            "HEALING_BONUS",

            "PHYSICAL_DMG_BONUS", "PYRO_DMG_BONUS",
            "ELECTRO_DMG_BONUS" , "CRYO_DMG_BONUS",
            "HYDRO_DMG_BONUS"   , "ANEMO_DMG_BONUS",
            "GEO_DMG_BONUS"     , "DENDRO_DMG_BONUS"

    };

    // ---------------------- //
    // Constructor.

    public Artifact(int type, String setName){

        this.type = type;
        this.setName = setName;

    }
    public Artifact(int type, String setName, String tag1, double value1){

        this.type = type;
        this.setName = setName;

        this.addStat(tag1, value1);

    }
    public Artifact(int type, String setName, String tag1, double value1, String tag2, double value2){

        this.type = type;
        this.setName = setName;

        this.addStat(tag1, value1);
        this.addStat(tag2, value2);

    }
    public Artifact(int type, String setName, String tag1, double value1, String tag2, double value2, String tag3, double value3){

        this.type = type;
        this.setName = setName;

        this.addStat(tag1, value1);
        this.addStat(tag2, value2);
        this.addStat(tag3, value3);

    }
    public Artifact(int type, String setName, String tag1, double value1, String tag2, double value2, String tag3, double value3, String tag4, double value4){

        this.type = type;
        this.setName = setName;

        this.addStat(tag1, value1);
        this.addStat(tag2, value2);
        this.addStat(tag3, value3);
        this.addStat(tag4, value4);

    }
    public Artifact(int type, String setName, String tag1, double value1, String tag2, double value2, String tag3, double value3, String tag4, double value4, String tag5, double value5){

        this.type = type;
        this.setName = setName;

        this.addStat(tag1, value1);
        this.addStat(tag2, value2);
        this.addStat(tag3, value3);
        this.addStat(tag4, value4);
        this.addStat(tag5, value5);

    }

    // ---------------------- //
    // Helper methods.

    private boolean arrayContains(String[] arr, String searchFor){

        for(String s : arr)
        {
            if(searchFor.equals(s))
            {
                return true;
            }
        }
        return false;

    }

    // ---------------------- //
    // Methods.

    public void addStat(String tag, double value){

        // Normalize inputs.
        tag = tag.toUpperCase();

        // Check whether stat tag is okay or not.
        if(!arrayContains(POSSIBLE_STATS, tag))
        {
            System.out.println("INVALID STAT: " + tag);
            System.out.println(1 / 0);
        }

        this.statTags.add(tag);
        this.statValues.add(value);

    }
    public double getStat(String tag){

        int index = statTags.indexOf(tag);
        return index == -1 ? 0 : statValues.get(index);

    }

    public void printArtifact(){

        System.out.print("SLOT: ");
        switch (this.type) {
            case 1 ->  System.out.println("FLOWER");
            case 2 ->  System.out.println("FEATHER");
            case 3 ->  System.out.println("HOURGLASS");
            case 4 ->  System.out.println("GOBLET");
            case 5 ->  System.out.println("CIRCLET");
            default -> System.out.println(1 / 0);
        }

        System.out.println("SET: " + this.setName);

        for(int i = 0; i < this.statTags.size(); i++)
        {

            // Retrieve values.
            double value = statValues.get(i);
            if(value != 0)
            {
                String tag = statTags.get(i);

                // Correctly display percent values.
                System.out.print(tag + ": ");
                if(tag.contains("%") || tag.contains("CRIT") || tag.contains("BONUS") || tag.contains("ENERGY_RECHARGE"))
                {
                    value *= 100;
                    System.out.println(Math.round(value * 10000) / 10000.0 + "%");
                }else{
                    System.out.println(value);
                }
            }


        }

    }

}
