package uncheck;

import java.util.Scanner;

/**
 * 一副扑克牌按大小分为13种牌，每种各4个花色，总共52张牌
 * 规定13种牌从小到大依次为 A23456789TJQK,
 * 现从一副牌中抽取20张，每轮选择以下一种规则出牌：
 * （1）单牌：任意一张
 * （2）对子：两张相同的牌
 * （3）三带：三张相同的牌，附带至多一张任意牌
 * （4）四带：四张相同的牌，附带至多两张任意牌
 * （5）顺子：至少连续5张大小连续的牌
 *
 * 求至少需要多少轮将20张牌出完
 */
public class PokerCard {
    private static int minCount = 20;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char[] cards = sc.nextLine().toCharArray();
        int[] cardsCount = new int[13];
        for(char c : cards){
            cardsCount[charToInt(c) - 1]++;
        }
        new PokerCard().select(cardsCount, 20, 0, 0, 0);
        // test case "8K67A65K27T59K346AK2"
        System.out.println(minCount);
    }

    /**
     * 递归函数
     * @param cards int数组。表示每种牌的张数
     * @param remainNum 剩余没出的牌张数
     * @param playCount 已出牌次数
     * @param fourSameCount 四带的数量
     * @param threeSameCount 三带的数量
     */
    public void select(int[] cards, int remainNum, int playCount, int fourSameCount, int threeSameCount){
        if (playCount >= minCount){ // 如果已经大于已有最小出牌次数，直接返回
            return;
        }
        if(remainNum <= 0){
            minCount = minCount > playCount ? playCount : minCount;
            return;
        }
        if (remainNum >= 5){ //出连子
            for(int i = 0; i < 9; i++){
                if (cards[i] <= 0){ // 某种值没有牌了，它不可能构成连子
                    continue;
                }
                boolean con = true;
                for (int j = 0; j < 5; j++) { // 需有至少连续的5张
                    if (cards[i + j] <= 0){
                        con = false;
                    }
                }
                if (con){
                    for (int k = 5; i + k <= 13; k++) { // 连子的张数
                        if (cards[i + k - 1] <= 0) {
                            break;
                        }
                        for (int j = 0; j < k; j++) { // 依次减
                            cards[i + j]--;
                        }
                        select(cards, remainNum - k, playCount + 1, fourSameCount, threeSameCount);
                        for (int j = 0; j < k; j++) { // 递归返回后恢复
                            cards[i + j]++;
                        }
                    }
                }
            }
        }
        if (remainNum >= 4){ //出四带，remainNum 只减 4，因为带走的单牌最后算
            for(int i = 0; i < cards.length; i++){
                if (cards[i] == 4){
                    cards[i] = 0;
                    select(cards, remainNum - 4, playCount + 1, fourSameCount + 1, threeSameCount);
                    cards[i] = 4;
                }
            }
        }
        if (remainNum >= 3){ //出三带，remainNum 只减 3，因为带走的单牌最后算
            for(int i = 0; i < cards.length; i++){
                if (cards[i] >= 3){
                    cards[i] -= 3;
                    select(cards, remainNum - 3, playCount + 1, fourSameCount, threeSameCount + 1);
                    cards[i] += 3;
                }
            }
        }
        if (remainNum >= 2){ //出对子
            for(int i = 0; i < cards.length; i++){
                if (cards[i] >= 2){
                    cards[i] -= 2;
                    select(cards, remainNum - 2, playCount + 1, fourSameCount, threeSameCount);
                    cards[i] += 2;
                }
            }
        }
        // 看最后剩的单牌能否被 四带 和 三带 顺带出完
        if (remainNum > fourSameCount * 2 + threeSameCount){
            minCount = Math.min(minCount, playCount + remainNum - fourSameCount * 2 + threeSameCount);
        }
        else {
            minCount = Math.min(minCount, playCount);
        }
    }

    private static int charToInt(char c){
        switch(c){
            case 'A':
                return 1;
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            default:
                return c - '0';
        }
    }
}
