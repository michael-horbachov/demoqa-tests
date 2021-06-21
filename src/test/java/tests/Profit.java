package tests;

public class Profit {

    public static int getProfit(int buy, int sell){
        return (sell-buy)/buy*100;
    }
}
