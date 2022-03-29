package com.blucyn;

import java.util.*;

public class ZhongliBuilder {

    public Scanner s = new Scanner(System.in);

    public ArrayList<Artifact> allArtifacts;
    public ArrayList<Artifact> flowers      = new ArrayList<>();
    public ArrayList<Artifact> feathers     = new ArrayList<>();
    public ArrayList<Artifact> hourglasses  = new ArrayList<>();
    public ArrayList<Artifact> goblets      = new ArrayList<>();
    public ArrayList<Artifact> circlets     = new ArrayList<>();
    public ArrayList<String>   weapons      = new ArrayList<>();

    public String CURRENT_WEAPON = null;

    // Childe's current stats.
    // (no artifacts)
    /** DON'T CHANGE THESE !!!! **/
    public double BASE_HP          = 14695;
    public double BASE_ATK         = 251;
    public double BASE_CRIT_DMG    = .5;
    public double BASE_CRIT_RATE   = .05;
    public double BASE_GEO_BONUS = 0.288;

    public double[] highest_damage = null;


    // ---------------------- //
    // Main method.


    public void run(){
        weapons.add("THE_CATCH");
        weapons.add("SKYWARD_SPINE");
        weapons.add("JADE_SPEAR");
        weapons.add("BLACK_TASSEL");
        showBestBuilds();
        System.out.println(weapons.size());
    }

    public ZhongliBuilder(ArrayList<Artifact> allArtifacts){

        this.allArtifacts = allArtifacts;

    }




    // ---------------------- //
    // Calculation Methods.

    // Calculates damage from attack and elemental bonus.
    public double getDamage(double atk, double hp, double elementalBonus){

        return (8.9972 * atk + .33 * hp) * (1 + elementalBonus);

    }

    // Returns a string carrying active set bonuses.
    // The first two characters will display the severity of the set bonus.
    public ArrayList<String> getSetBonuses(Artifact[] artifacts){

        // Store all unique sets and their associated counts.
        ArrayList<String> uniqueSets = new ArrayList<>();
        ArrayList<Integer> uniqueCounts = new ArrayList<>();
        for(Artifact artifact : artifacts)
        {

            String setName = artifact.setName;
            int index = uniqueSets.indexOf(setName);

            if(index == -1)
            {

                uniqueSets.add(setName);
                uniqueCounts.add(1);

            }else{

                uniqueCounts.set(index, uniqueCounts.get(index) + 1);

            }
        }

        // Print all stored sets and their counts.
        ArrayList<String> output = new ArrayList<>();
        for(int i = 0; i < uniqueSets.size(); i++)
        {

            int count = uniqueCounts.get(i);
            String setName = uniqueSets.get(i);
            // System.out.println(count + ":" + setName);
            if(count >= 2)
            {
                output.add("2:" + setName);
                if(count >= 4)
                    output.add("4:" + setName);
            }

        }
        return output;

    }

    // Returns an arrayList of only one type of artifact.
    public ArrayList<Artifact> scanForType(ArrayList<Artifact> allArtifacts, int type){

        ArrayList<Artifact> output = new ArrayList<>();
        for(Artifact artifact : allArtifacts)
        {
            if(artifact.type == type)
                output.add(artifact);
        }
        return output;

    }

    // Tries all combinations of artifacts and prints the details of the best one.
    public ArrayList<int[]> getBestCombos(boolean critBuild){

        // Initialize array.
        ArrayList<int[]>  comboCodes    = new ArrayList<>();
        ArrayList<Double> damageNumbers = new ArrayList<>();

        long totalCombinations = (long)flowers.size() * feathers.size() * hourglasses.size() * goblets.size() * circlets.size();
        long currentCombination = 0;

        // Loop through all combinations of the artifacts.
        for(int flowerCount = 0; flowerCount < flowers.size(); flowerCount++)
        {
            for(int featherCount = 0; featherCount < feathers.size(); featherCount++)
            {
                for(int hourglassCount = 0; hourglassCount < hourglasses.size(); hourglassCount++)
                {
                    for(int gobletCount = 0; gobletCount < goblets.size(); gobletCount++)
                    {
                        for(int circletCount = 0; circletCount < circlets.size(); circletCount++)
                        {
                            for(int weapon = 0; weapon < weapons.size(); weapon++)
                            {
                                CURRENT_WEAPON = weapons.get(weapon);


                                currentCombination++;
                                if(currentCombination % 1000 == 0)
                                {
                                    double percent = ((double)currentCombination / weapons.size()) / totalCombinations;
                                    percent = Math.round(percent * 10000) / 100.0;
                                    System.out.print("\b\b\b\b\b\b\b\b\b");
                                    System.out.print("[" + percent + "%]");
                                }


                                // Figure out which index the damage goes to.
                                int[] comboCode = new int[]{
                                        weapon,
                                        flowerCount,
                                        featherCount,
                                        hourglassCount,
                                        gobletCount,
                                        circletCount
                                };


                                double[] damageValues = getArtComboDamage(comboCode, false);
                                double damage = critBuild ? damageValues[1] : damageValues[0];

                                damageNumbers.add(damage);
                                comboCodes.add(comboCode);


                                // Prune arrayList amounts if they go over a critical value.
                                int cutOffLimit = 1000;
                                if(damageNumbers.size() > 10000)
                                {
                                    keySort(damageNumbers, damageNumbers, comboCodes);
                                    while(damageNumbers.size() > cutOffLimit)
                                    {
                                        damageNumbers.remove(cutOffLimit);
                                        comboCodes.remove(cutOffLimit);
                                    }
                                }




                            }
                        }
                    }
                }
            }
        }

        keySort(damageNumbers, damageNumbers, comboCodes);
        System.out.print("\b\b\b\b\b\b\b\b\b");
        return comboCodes;


    }

    public <T extends Comparable<T>> void keySort(final List<T> key, List<?>... lists){
        // Create a List of indices
        List<Integer> indices = new ArrayList<>();
        for(int i = 0; i < key.size(); i++)
            indices.add(i);

        // Sort the indices list based on the key
        indices.sort((i, j) -> key.get(j).compareTo(key.get(i)));

        // Create a mapping that allows sorting of the List by N swaps.
        Map<Integer,Integer> swapMap = new HashMap<>(indices.size());

        // Only swaps can be used b/c we cannot create a new List of type <?>
        for(int i = 0; i < indices.size(); i++){
            int k = indices.get(i);
            while(swapMap.containsKey(k))
                k = swapMap.get(k);

            swapMap.put(i, k);
        }

        // for each list, swap elements to sort according to key list
        for(Map.Entry<Integer, Integer> e : swapMap.entrySet())
            for(List<?> list : lists)
                Collections.swap(list, e.getKey(), e.getValue());
    }


    // ---------------------- //
    // Helper Methods.

    public void wipeScreen(){

        for(int i = 0; i < 50; i++)
        {
            System.out.println();
        }

    }

    // Initializes all the individual arrays.
    public void initialize(){

        flowers     = scanForType(allArtifacts, 1);
        feathers    = scanForType(allArtifacts, 2);
        hourglasses = scanForType(allArtifacts, 3);
        goblets     = scanForType(allArtifacts, 4);
        circlets    = scanForType(allArtifacts, 5);


    }

    public double getStatSum(Artifact[] artifacts, String searchStat){

        double sum = 0;
        for(Artifact a : artifacts)
        {
            sum += a.getStat(searchStat);
        }
        return sum;

    }

    public double[] getArtComboDamage(int[] comboCode, boolean print){

        String weapon = weapons.get(comboCode[0]);
        Artifact[] artifacts = new Artifact[]{
                flowers.    get(comboCode[1]),
                feathers.   get(comboCode[2]),
                hourglasses.get(comboCode[3]),
                goblets.    get(comboCode[4]),
                circlets.   get(comboCode[5])
        };

        if(print)
        {
            System.out.println("\nUSING " + weapon);
            System.out.println("\nSlot\t\tHP\t\tHP%\t\tATK\t\tATK%\tCRIT RATE\tCRIT DMG\tEnergy\t\tGeo Bonus\t\tSet Name");

            for(Artifact artifact : artifacts)
            {
                switch (artifact.type) {
                    case 1 ->  System.out.print("Flower   ");
                    case 2 ->  System.out.print("Feather  ");
                    case 3 ->  System.out.print("Hourglass");
                    case 4 ->  System.out.print("Goblet   ");
                    case 5 ->  System.out.print("Circlet  ");
                }
                System.out.print("\t" +   padString(artifact.getStat("HP") + "", " ", 4));
                System.out.print("\t" +   padString(Math.round(artifact.getStat("HP%") * 10000) / 100.0 + "%", " ", 6));
                System.out.print("\t" +   padString(artifact.getStat("ATK") + "", " ", 4));
                System.out.print("\t" +   padString(Math.round(artifact.getStat("ATK%") * 10000) / 100.0 + "%", " ", 6));
                System.out.print("\t" +   padString(Math.round(artifact.getStat("CRIT_RATE") * 10000) / 100.0 + "%", " ", 8));
                System.out.print("\t" + padString(Math.round(artifact.getStat("CRIT_DMG" ) * 10000) / 100.0 + "%", " ", 8));
                System.out.print("\t" + padString(Math.round(artifact.getStat("ENERGY_RECHARGE" ) * 10000) / 100.0 + "%", " ", 8));
                System.out.print("\t" + padString(Math.round(artifact.getStat("GEO_DMG_BONUS" ) * 10000) / 100.0 + "%", " ", 8));
                System.out.println("\t\t" + artifact.setName);
            }
        }

        double hpPercentSum = getStatSum(artifacts, "HP%");
        double attackPercentSum = getStatSum(artifacts, "ATK%");
        double newEnergy = getStatSum(artifacts, "ENERGY_RECHARGE");
        double newCritDamage = BASE_CRIT_DMG + getStatSum(artifacts, "CRIT_DMG");
        double newCritRate = BASE_CRIT_RATE + getStatSum(artifacts, "CRIT_RATE");
        double newGeoBonus = BASE_GEO_BONUS + getStatSum(artifacts, "GEO_DMG_BONUS");
        double additionalDamage = 0;

        double weaponAttack = 0;
        switch (weapon) {
            case "THE_CATCH" -> {
                weaponAttack += 510;
                newEnergy += .459;
                newCritRate += .12;
                additionalDamage += .32;
            }
            case "SKYWARD_SPINE" -> {
                weaponAttack += 674;
                newEnergy += .368;
                newCritRate += .08;
            }
            case "JADE_SPEAR" -> {
                weaponAttack += 674;
                newCritRate += .221;
            }
            case "BLACK_TASSEL" -> {
                weaponAttack += 354;
                hpPercentSum += .469;
            }
        }

        ArrayList<String> setBonuses = getSetBonuses(
                artifacts
        );

        // Account for set bonuses.
        if(setBonuses.contains("2:SHIMENAWAS_REMINISCENCE"))
        {
            attackPercentSum += .18;
        }
        if(setBonuses.contains("2:GLADIATORS_FINALE"))
        {
            attackPercentSum += .18;
        }
        if(setBonuses.contains("4:PALE_FLAME"))
        {
//            newPhysBonus += .25;
            attackPercentSum += .18;
        }
        if(setBonuses.contains("2:EMBLEM_OF_SEVERED_FATE"))
        {
            newEnergy += .2;
        }
        if(setBonuses.contains("2:ARCHAIC_PETRA"))
        {
            newGeoBonus += .15;
        }
        if(setBonuses.contains("2:TENACITY_OF_THE_MILILITH"))
        {
            hpPercentSum += .20;
        }

        double newHP = (BASE_HP) * (1 + hpPercentSum);
        newHP += getStatSum(artifacts, "HP");

        double newAtk = (BASE_ATK + weaponAttack) * (1 + attackPercentSum);
        newAtk += getStatSum(artifacts, "ATK");

        double damage = getDamage(
                newAtk,
                newHP,
                newGeoBonus
        );

        if(setBonuses.contains("2:NOBLESSE_OBLIGE"))
        {
            additionalDamage += .2;
        }
        if(setBonuses.contains("4:EMBLEM_OF_SEVERED_FATE"))
        {
            additionalDamage += Math.min(.75, (newEnergy) * .25);
        }

        damage *= additionalDamage + 1;

        damage *= .4;

        double nonCritDamage = damage;
        double critDamage = damage * (1 + newCritDamage);
        double averageDamage = newCritRate > 0 ? damage * Math.min(newCritRate, 1) * (1 + newCritDamage) : damage;

        if(print)
        {
            System.out.println();
            if(setBonuses.size() != 0)
            {
                if(setBonuses.size() == 1)
                {
                    System.out.println("Set Bonus: " + setBonuses.get(0));
                }else {
                    System.out.println("Set Bonuses");
                    for (String setBonus : setBonuses) {
                        System.out.println("  - " + setBonus);
                    }
                }
            }

            System.out.println();
            System.out.println("Overall Stats:");
            System.out.println("HP\t\t\tATK\t\t\tCRIT RATE\tCRIT DMG\tEnergy\t\tGeo Bonus\tBonus DMG");
            System.out.print(padString(Math.round(newHP * 100) / 100.0 + "", " ", 8));
            System.out.print("\t" + padString(Math.round(newAtk   * 100) / 100.0 + "", " ", 8));
            System.out.print("\t" + padString(Math.round(newCritRate   * 10000) / 100.0 + "%" + "", " ", 8));
            System.out.print("\t" + padString(Math.round(newCritDamage   * 10000) / 100.0 + "%" + "", " ", 8));
            System.out.print("\t" + padString(Math.round(newEnergy   * 10000) / 100.0 + "%" + "", " ", 8));
            System.out.print("\t" + padString(Math.round(newGeoBonus   * 10000) / 100.0 + "%" + "", " ", 8));
            System.out.print("\t" + padString(Math.round(additionalDamage   * 10000) / 100.0 + "%" + "", " ", 8));
            System.out.println("\n");
            System.out.println("Average Hit Damage: " + averageDamage);
            System.out.println("Crit Hit Damage:    " + critDamage);
        }

        return new double[]{
                averageDamage,
                critDamage
        };

    }

    public String padString(String toPad, String padWith, int characters){

        StringBuilder toPadBuilder = new StringBuilder(toPad);
        while(toPadBuilder.length() < characters)
        {
            toPadBuilder.append(padWith);
        }
        toPad = toPadBuilder.toString();
        return toPad;

    }

    public String formatSetName(String setName){

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


    // ---------------------- //
    // User Interaction Methods.


    public void       askWeapon(){

        String answer = "";
        System.out.println("[Builder]: " + "What weapon are you using?: ");
        System.out.println("1 --> The Catch");
        System.out.println("2 --> Skyward Spine");
        System.out.println("3 --> Primordial Jade Spear");
        System.out.println("4 --> Black Tassel");
        System.out.println("5 --> Check All");
        System.out.print  ("\nAnswer: ");
        answer = s.nextLine().toUpperCase();
        switch (Integer.parseInt(answer)) {
            case 1 -> {
                weapons = new ArrayList<>();
                weapons.add("THE_CATCH");
            }
            case 2 -> {
                weapons = new ArrayList<>();
                weapons.add("SKYWARD_SPINE");
            }
            case 3 -> {
                weapons = new ArrayList<>();
                weapons.add("JADE_SPEAR");
            }
            case 4 -> {
                weapons = new ArrayList<>();
                weapons.add("BLACK_TASSEL");
            }
        }



    }
    public boolean askCritBuild(){

        String critBuild = "";
        while(!(critBuild.equals("1") || critBuild.equals("2")))
        {
            System.out.println("[Builder]: " + "Are you building for the biggest number, or highest average damage?");
            System.out.println("1 --> average damage");
            System.out.println("2 --> highest numbers");
            System.out.println();
            System.out.print("Answer: ");
            critBuild = s.nextLine();
        }
        return critBuild.equals("2");

    }
    public void scrollBuilds(boolean critBuild){

        ArrayList<int[]> comboCodes = getBestCombos(critBuild);
        if(highest_damage == null)
        {
            highest_damage = getArtComboDamage(comboCodes.get(0), false);
        }

        for(int i = 0; i < comboCodes.size(); i++)
        {
            int[] comboCode = comboCodes.get(i);
            wipeScreen();
            System.out.println("# " + (i + 1) + " Best Build");
            double[] damage = getArtComboDamage(comboCode, true);
            System.out.println();
            for(int j = 0; j < highest_damage.length; j++)
            {
                damage[j] /= highest_damage[j];
                damage[j] = Math.round(damage[j] * 10000) / 100.0;
            }
            System.out.println("Build places at " + (critBuild ? damage[1] : damage[0]) + "% of your current max potential.");
            System.out.println();
            String answer = "";

            System.out.println("[Builder]: Press enter to skip to the next build.");
            System.out.print("[Builder]: If you wish to remove an artifact, type its corresponding number: ");
            answer = s.nextLine().toUpperCase();
            if(answer.length() != 0)
            {
                String[] numbers = answer.split(" ");
                wipeScreen();
                for(String number : numbers)
                {
                    if(!number.startsWith("L"))
                    {
                        int num = Integer.parseInt(number);
                        System.out.println("Removing artifact # " + num + "...");
                        switch (num) {
                            case 0 -> weapons.remove(comboCode[num]);
                            case 1 -> flowers.remove(comboCode[num]);
                            case 2 -> feathers.remove(comboCode[num]);
                            case 3 -> hourglasses.remove(comboCode[num]);
                            case 4 -> goblets.remove(comboCode[num]);
                            case 5 -> circlets.remove(comboCode[num]);
                        }
                    }else{
                        int num = Integer.parseInt(number.substring(1));
                        System.out.println("Locking artifact # " + num + "...");
                        switch (num) {
                            case 0 -> {
                                String w = weapons.get(comboCode[num]);
                                weapons = new ArrayList<>();
                                weapons.add(w);
                            }
                            case 1 -> {
                                Artifact a = flowers.get(comboCode[num]);
                                flowers = new ArrayList<>();
                                flowers.add(a);
                            }
                            case 2 -> {
                                Artifact a = feathers.get(comboCode[num]);
                                feathers = new ArrayList<>();
                                feathers.add(a);
                            }
                            case 3 -> {
                                Artifact a = hourglasses.get(comboCode[num]);
                                hourglasses = new ArrayList<>();
                                hourglasses.add(a);
                            }
                            case 4 -> {
                                Artifact a = goblets.get(comboCode[num]);
                                goblets = new ArrayList<>();
                                goblets.add(a);
                            }
                            case 5 -> {
                                Artifact a = circlets.get(comboCode[num]);
                                circlets = new ArrayList<>();
                                circlets.add(a);
                            }
                        }
                    }

                }
                return;
            }


        }

    }
    public void showBestBuilds(){

        initialize();

        wipeScreen();
        askWeapon();

        wipeScreen();
        boolean critBuild = askCritBuild();

        wipeScreen();

        while(true)
        {
            scrollBuilds(critBuild);
        }


    }

}
