package com.blucyn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RaidenBuilder {


    // ---------------------- //
    // Instance variables.

    public Scanner s = new Scanner(System.in);

    public ArrayList<Artifact> allArtifacts = new ArrayList<>();
    public ArrayList<Artifact> flowers      = new ArrayList<>();
    public ArrayList<Artifact> feathers     = new ArrayList<>();
    public ArrayList<Artifact> hourglasses  = new ArrayList<>();
    public ArrayList<Artifact> goblets      = new ArrayList<>();
    public ArrayList<Artifact> circlets     = new ArrayList<>();
    public ArrayList<String>   weapons      = new ArrayList<>();


    // Raiden's current stats.
    // (no artifacts)
    /** DON'T CHANGE THESE !!!! **/
    public double BASE_ATK           = 337;
    public double BASE_CRIT_DMG      = .5;
    public double BASE_CRIT_RATE     = .05;
    public double BASE_ELECTRO_BONUS = 0;
    public double BASE_ENERGY        = 1.32;
    public double BASE_BURST_DMG     = 1.00;

    public boolean thrillingTales;

    public double[] highest_damage = null;

    public boolean food = false;


    public void run(){

        weapons.add("CATCH");
        weapons.add("JADE");
        weapons.add("SKYWARD");
        showBestBuilds(true);

    }

    public RaidenBuilder(ArrayList<Artifact> allArtifacts){

        this.allArtifacts = allArtifacts;

    }


    // ---------------------- //
    // Calculation Methods.

    // Calculates damage from attack and elemental bonus.
    public double getDamage(double atk, double elementalBonus){

        return .7982 * atk * (1 + elementalBonus);

    }
    public double getDamageBurst(double atk, double elementalBonus){

        return 7.2144 * atk * (1 + elementalBonus);

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
    public ArrayList<int[]> getBestCombos(boolean critBuild, boolean optimizeForInitial){

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
                            for (int weapon = 0; weapon < weapons.size(); weapon++)
                            {

                                currentCombination++;
                                if (currentCombination % 1000 == 0) {
                                    double percent = ((double) currentCombination / weapons.size()) / totalCombinations;
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

                                double[] damageValues = getArtComboDamage(comboCode, false, optimizeForInitial);
                                double damage = critBuild ? damageValues[1] : damageValues[0];

                                damageNumbers.add(damage);
                                comboCodes.add(comboCode);

                                // Prune arrayList amounts if they go over a critical value.
                                int cutOffLimit = 1000;
                                if (damageNumbers.size() > 10000) {
                                    keySort(damageNumbers, damageNumbers, comboCodes);
                                    while (damageNumbers.size() > cutOffLimit) {
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

    public double[] getArtComboDamage(int[] comboCode, boolean print, boolean optimizeInitialStrike){

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
            System.out.println("Slot\t\tATK\t\tATK%\tCRIT RATE\tCRIT DMG\tEnergy\t\tElectro Bonus\t\tSet Name");

            for(Artifact artifact : artifacts)
            {
                switch (artifact.type) {
                    case 1 ->  System.out.print("Flower   ");
                    case 2 ->  System.out.print("Feather  ");
                    case 3 ->  System.out.print("Hourglass");
                    case 4 ->  System.out.print("Goblet   ");
                    case 5 ->  System.out.print("Circlet  ");
                }
                System.out.print("\t" +   padString(artifact.getStat("ATK") + "", " ", 4));
                System.out.print("\t" +   padString(Math.round(artifact.getStat("ATK%") * 10000) / 100.0 + "%", " ", 6));
                System.out.print("\t" +   padString(Math.round(artifact.getStat("CRIT_RATE") * 10000) / 100.0 + "%", " ", 8));
                System.out.print("\t" + padString(Math.round(artifact.getStat("CRIT_DMG" ) * 10000) / 100.0 + "%", " ", 8));
                System.out.print("\t" + padString(Math.round(artifact.getStat("ENERGY_RECHARGE" ) * 10000) / 100.0 + "%", " ", 8));
                System.out.print("\t" + padString(Math.round(artifact.getStat("ELECTRO_DMG_BONUS" ) * 10000) / 100.0 + "%", " ", 8));
                System.out.println("\t\t" + artifact.setName);
            }
        }

        double attackPercentSum = getStatSum(artifacts, "ATK%");
        double newCritDamage = BASE_CRIT_DMG + getStatSum(artifacts, "CRIT_DMG");
        double newCritRate = BASE_CRIT_RATE + getStatSum(artifacts, "CRIT_RATE");
        double newElectroBonus = BASE_ELECTRO_BONUS + getStatSum(artifacts, "ELECTRO_DMG_BONUS");
        double newEnergy = BASE_ENERGY + getStatSum(artifacts, "ENERGY_RECHARGE");
        double additionalDamage = BASE_BURST_DMG + newEnergy * .3;
        double additionalAttackSpeed = 0;

        double weaponAttack = 0;
        switch (weapon) {
            case "CATCH" -> {
                weaponAttack += 510;
                newEnergy += .459;
                newCritRate += .12;
                additionalDamage += .32;
            }
            case "JADE" -> {
                weaponAttack += 674;
                newCritRate += .221;
                if(!optimizeInitialStrike)
                {
                    attackPercentSum += .032 * 7;
                    additionalDamage += .12;
                }
            }
            case "SKYWARD" -> {
                weaponAttack += 674;
                newEnergy += .368;
                newCritRate += .08;
                if(!optimizeInitialStrike)
                {
                    additionalAttackSpeed += .2;
                }
            }
        }


        if(thrillingTales)
        {
            attackPercentSum += .48;
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
        if(setBonuses.contains("2:THUNDERING_FURY"))
        {
            newElectroBonus += .15;
        }
        if(setBonuses.contains("2:EMBLEM_OF_SEVERED_FATE"))
        {
            newEnergy += .2;
        }

        double newAtk = (BASE_ATK + weaponAttack) * (1 + attackPercentSum);
        newAtk += getStatSum(artifacts, "ATK");
        if(this.food)
        {
            newAtk += 372;
        }

        newElectroBonus += (newEnergy - 1) * .4;


        double damage = optimizeInitialStrike ?
                getDamageBurst(newAtk, newElectroBonus):
                getDamage     (newAtk, newElectroBonus);



        if(setBonuses.contains("2:NOBLESSE_OBLIGE"))
        {
            additionalDamage += .2;
        }
        if(setBonuses.contains("4:EMBLEM_OF_SEVERED_FATE"))
        {
            additionalDamage += Math.min(.75, (newEnergy) * .25);
        }

        damage *= additionalDamage;

        double nonCritDamage = damage;
        double critDamage = damage * (1 + newCritDamage);
        double averageDamage = newCritRate > 0 ? damage * Math.min(newCritRate, 1) * (1 + newCritDamage) : damage;
        averageDamage *= 1 + additionalAttackSpeed;

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
            System.out.println("Weapon: " + weapon);
            System.out.println("\nOverall Stats:");
            System.out.println("ATK\t\t\tCRIT RATE\tCRIT DMG\tEnergy\t\tElectro Bonus");
            System.out.print(padString(Math.round(newAtk   * 100) / 100.0 + "", " ", 8));
            System.out.print("\t" + padString(Math.round(newCritRate   * 10000) / 100.0 + "%" + "", " ", 8));
            System.out.print("\t" + padString(Math.round(newCritDamage   * 10000) / 100.0 + "%" + "", " ", 8));
            System.out.print("\t" + padString(Math.round(newEnergy   * 10000) / 100.0 + "%" + "", " ", 8));
            System.out.print("\t" + padString(Math.round(newElectroBonus   * 10000) / 100.0 + "%" + "", " ", 8));
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

    public void         askFood(){

        String food = "";
        System.out.print("[Builder]: " + "Will you be using adeptus temptation? (y/n): ");
        food = s.nextLine().toUpperCase();
        if(food.equals("Y"))
        {

            this.food = true;
            BASE_CRIT_RATE += .12;

        }

    }
    public boolean     askBurst(){

        String answer = "";
        System.out.print("[Builder]: " + "Are you optimizing for initial strike burst damage? (y/n): ");
        answer = s.nextLine().toUpperCase();
        return answer.equals("Y");

    }
    public void       askWeapon(){

        System.out.println("[Builder]: " + "What weapon are you using?");
        System.out.println("1 --> The Catch");
        System.out.println("2 --> Primordial Jade-Winged Spear");
        System.out.println("3 --> Skyward Spine");
        System.out.println("4 --> Check All");
        System.out.println();
        System.out.print("Answer: ");
        try {
            switch (Integer.parseInt(s.nextLine().toUpperCase())) {
                case 1 -> {
                    weapons = new ArrayList<>();
                    weapons.add("CATCH");
                }
                case 2 -> {
                    weapons = new ArrayList<>();
                    weapons.add("JADE");
                }
                case 3 -> {
                    weapons = new ArrayList<>();
                    weapons.add("SKYWARD");
                }
            }
        }catch (Exception ignored){

        }



    }
    public void       askPotion(){

        String potion = "";
        System.out.print("[Builder]: " + "Will you be using an electro damage potion? (y/n): ");
        potion = s.nextLine().toUpperCase();
        if(potion.equals("Y"))
        {

            BASE_ELECTRO_BONUS += .25;

        }

    }
    public void    askThrilling(){

        String thrilling = "";
        while(!(thrilling.equals("Y") || thrilling.equals("N")))
        {
            System.out.print("[Raiden Builder]: " + "Will you have the thrilling tales buff? (y/n): ");
            thrilling = s.nextLine().toUpperCase();
        }
        thrillingTales = thrilling.equals("Y");

    }
    public boolean askCritBuild(){

        String critBuild = "";
        while(!(critBuild.equals("1") || critBuild.equals("2")))
        {
            System.out.println("[Raiden Builder]: " + "Are you building for the biggest number, or highest average damage?");
            System.out.println("1 --> average damage");
            System.out.println("2 --> highest numbers");
            System.out.println();
            System.out.print("Answer: ");
            critBuild = s.nextLine();
        }
        return critBuild.equals("2");

    }
    public void scrollBuilds(boolean critBuild, boolean optimizeForInitial){

        ArrayList<int[]> comboCodes = getBestCombos(critBuild, optimizeForInitial);
        if(highest_damage == null)
        {
            highest_damage = getArtComboDamage(comboCodes.get(0), false, optimizeForInitial);
        }

        for(int i = 0; i < comboCodes.size(); i++)
        {
            int[] comboCode = comboCodes.get(i);
            wipeScreen();
            System.out.println("# " + (i + 1) + " Best Build");
            double[] damage = getArtComboDamage(comboCode, true, optimizeForInitial);
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
    public void showBestBuilds (boolean askExtraQuestions){

        wipeScreen();
        if(askExtraQuestions)
        {
            askFood();
            wipeScreen();
            askPotion();
            wipeScreen();
            // askThrilling();
            askWeapon();
            wipeScreen();
        }
        boolean burstOptimizer = askBurst();
        wipeScreen();
        boolean critBuild = askCritBuild();
        initialize();
        wipeScreen();
        while(true)
        {
            scrollBuilds(critBuild, burstOptimizer);
        }


    }

    public void addImaginaryArtifact(int artifactType, String setName, double atkFlat, double atkPercent, double critRate, double critDmg, double hydro){

        allArtifacts.add(new Artifact(
                artifactType, setName,
                "ATK", atkFlat,
                "ATK%", atkPercent,
                "CRIT_RATE", critRate,
                "CRIT_DMG", critDmg,
                "HYDRO_DMG_BONUS", hydro
        ));

    }


}
