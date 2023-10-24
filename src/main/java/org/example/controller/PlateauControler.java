package org.example.controller;

import org.example.model.AbstractModel;
import org.example.model.Case;
import org.example.model.DéAttaquant;

import java.util.Arrays;
import java.util.Random;

public class PlateauControler extends AbstractControler {

    public PlateauControler(AbstractModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void cliqueSur(int x, int y) {
        Case caseClique = this.model.getCase(x,y);


    }

    @Override
    public void calculerStepSuivant() {
        // TODO Auto-generated method stub

    }

    public void bataille(){


    }

    public void faireBataille(){
        int attackerArmies = 5; // nb attaqueur
        int defenderArmies = 2; // nb defneceur

        int attackerDice = Math.min(attackerArmies - 1, 3); // nb des attaqueur
        int defenderDice = Math.min(defenderArmies, 2); // nb de defenceur

        int[] attackerRoll = rollDice(attackerDice);
        int[] defenderRoll = rollDice(defenderDice);

        System.out.println("Attacker's dice roll: " + arrayToString(attackerRoll));
        System.out.println("Defender's dice roll: " + arrayToString(defenderRoll));

        // 比较骰子点数并计算结果
        int[] battleResult = compareDice(attackerRoll, defenderRoll, attackerArmies, defenderArmies);
        System.out.println("Battle result: Attacker loses " + battleResult[0] + " armies, Defender loses " + battleResult[1] + " armies.");
        boolean attaqueReusi = false;
        if (defenderDice - battleResult[1] <= 0)
            attaqueReusi = true;
        System.out.println("Attaque reussi : " + attaqueReusi);
    }
    // lacner des
    public static int[] rollDice(int numDice) {
        int[] result = new int[numDice];
        Random rand = new Random();
        for (int i = 0; i < numDice; i++) {
            result[i] = rand.nextInt(6) + 1; // 1到6之间的随机整数
        }
        Arrays.sort(result);
        for (int i = 0; i < numDice / 2; i++) {
            int temp = result[i];
            result[i] = result[numDice - i - 1];
            result[numDice - i - 1] = temp;
        }
        return result;
    }

    // comparer
    public static int[] compareDice(int[] attackerRoll, int[] defenderRoll, int attackerArmies, int defenderArmies) {
        int[] result = new int[2]; // 0索引用于存储攻击者损失的军队数，1索引用于存储防守者损失的军队数

        while (attackerArmies >= 1 && defenderArmies >= 1) {
            for (int i = 0; i < Math.min(attackerRoll.length, defenderRoll.length); i++) {
                if (attackerRoll[i] > defenderRoll[i]) {
                    result[1]++; // 防守者损失一支军队
                    defenderArmies--;
                } else {
                    result[0]++; // 攻击者损失一支军队
                    attackerArmies--;
                }
                System.out.println(Arrays.toString(result));
            }
        }

        return result;
    }

    // 将整数数组转换为字符串
    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
