package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;

public class KavinPlayer implements Player {
    @Override
    public String getName() {
        String name="凯文";
        return name;
    }

    @Override
    public String getStuNum() {
        String num="2017214832";
        return num;
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {
        System.out.println(manager);
        int a;
        for(a=0;a<=totalPlayer;a++)
        System.out.println("现在有"+a+"个玩家");

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers){
        if (isSameColor(pokers) )
            return (int) ((1.3 +(round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 +(round / 10f)) * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;
        if(isPair(pokers))
            return(int)(1.1 * moneyYouNeedToPayLeast);
        if(isSamePoint(pokers))
            return(int)(2.5 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (3.0 * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;
        if(isSameColorStraight(pokers))
            return(int)(2.0 +(round / 10f)* moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 +(round / 10f)) * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;
        if(isStraight(pokers))
            return(int)(1.0 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 +(round / 10f)) * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;

        return moneyYouNeedToPayLeast;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }

    private boolean isPair(List<Poker> pokers) {
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }

    private boolean isStraight(List<Poker> pokers) {
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }

    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }
}
