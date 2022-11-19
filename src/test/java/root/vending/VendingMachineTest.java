package root.vending;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    VendingMachine machine;

    @BeforeEach
    void coinVal() {
        VendingMachine.coinval1 = 1;
        VendingMachine.coinval2 = 2;
        machine = new VendingMachine();
    }

    //region [getNumberOfProduct1]
    @Test
    void getNumberOfProduct1() {
        assertEquals(0, machine.getNumberOfProduct1());
    }
    //endregion

    //region [getNumberOfProduct2]
    @Test
    void getNumberOfProduct2() {
        assertEquals(0, machine.getNumberOfProduct2());
    }
    //endregion

    //region [getCurrentBalance
    @Test
    void getCurrentBalance() {
        assertEquals(0, machine.getCurrentBalance());
    }
    //endregion

    //region [getCurrentMode]
    @Test
    void getCurrentMode() {
        assertEquals(VendingMachine.Mode.OPERATION, machine.getCurrentMode());
    }
    //endregion

    //region [getCurrentSum]
    @Test
    void getCurrentSum() {
        assertEquals(0, machine.getCurrentSum());
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(2, 3);
        assertEquals(8, machine.getCurrentSum());
        VendingMachine.coinval1++;
        assertEquals(10, machine.getCurrentSum());
    }
    //endregion

    //region [getCoins1]
    @Test
    void getCoins1() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.exitAdminMode();
        assertEquals(0, machine.getCoins1());
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(2, 3);
        assertEquals(2, machine.getCoins1());
    }
    //endregion

    //region [getCoins2]
    @Test
    void getCoins2() {
        VendingMachine machine = new VendingMachine();
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.exitAdminMode();
        assertEquals(0, machine.getCoins2());
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(2, 3);
        assertEquals(3, machine.getCoins2());
    }
    //endregion

    //region [getPrice2]
    @Test
    void getPrice1() {
        assertEquals(8, machine.getPrice1());
    }
    //endregion

    //region [getPrice2]
    @Test
    void getPrice2() {
        assertEquals(5, machine.getPrice2());
    }
    //endregion

    //region [fillProducts]

    @Test
    void fillProducts() {
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, machine.fillProducts());
        machine.enterAdminMode(117345294655382L);
        assertEquals(VendingMachine.Response.OK, machine.fillProducts());
        assertEquals(30, machine.getNumberOfProduct1());
        assertEquals(40, machine.getNumberOfProduct2());
    }
    //endregion

    //region [fillCoins]

    @Test
    void fillCoins() {
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, machine.fillCoins(10, 15));
        machine.enterAdminMode(117345294655382L);
        assertEquals(VendingMachine.Response.OK, machine.fillCoins(10, 15));
        assertEquals(10, machine.getCoins1());
        assertEquals(15, machine.getCoins2());
        assertEquals(VendingMachine.Response.OK, machine.fillCoins(6, 8));
        assertEquals(6, machine.getCoins1());
        assertEquals(8, machine.getCoins2());
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.fillCoins(-5, 0));
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.fillCoins(51, 50));
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.fillCoins(50, 52));
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.fillCoins(0, 52));
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.fillCoins(0, 1));
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.fillCoins(1, 0));
    }
    //endregion

    //region [enterAdminMode]

    @Test
    void enterAdminMode() {
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.enterAdminMode(0));
        assertEquals(VendingMachine.Response.OK, machine.enterAdminMode(117345294655382L));
        assertEquals(VendingMachine.Mode.ADMINISTERING, machine.getCurrentMode());
        machine.exitAdminMode();
        machine.putCoin1();
        assertEquals(VendingMachine.Response.CANNOT_PERFORM, machine.enterAdminMode(117345294655382L));
    }
    //endregion

    //region [exitAdminMode]
    @Test
    void exitAdminMode() {
        machine.enterAdminMode(117345294655382L);
        assertEquals(machine.getCurrentMode(), VendingMachine.Mode.ADMINISTERING);
        machine.exitAdminMode();
        assertEquals(machine.getCurrentMode(), VendingMachine.Mode.OPERATION);
    }
    //endregion

    //region [setPrices]

    @Test
    void setPrices() {
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, machine.setPrices(10, 10));
        machine.enterAdminMode(117345294655382L);
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.setPrices(-6, 10));
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.setPrices(16, -10));
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.setPrices(0, 5));
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.setPrices(5, 0));
        assertEquals(VendingMachine.Response.OK, machine.setPrices(20, 25));
        assertEquals(20, machine.getPrice1());
        assertEquals(25, machine.getPrice2());
    }
    //endregion

    //region [putCoin1]
    @Test
    void putCoin1() {
        machine.enterAdminMode(117345294655382L);
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, machine.putCoin1());
        machine.fillCoins(50, 50);
        machine.exitAdminMode();
        assertEquals(VendingMachine.Response.CANNOT_PERFORM, machine.putCoin1());
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 3);
        machine.exitAdminMode();
        assertEquals(VendingMachine.Response.OK, machine.putCoin1());
        assertEquals(1, machine.getCurrentBalance());
    }
    //endregion

    //region [putCoin2]
    @Test
    void putCoin2() {
        machine.enterAdminMode(117345294655382L);
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, machine.putCoin2());
        machine.fillCoins(50, 50);
        machine.exitAdminMode();
        assertEquals(VendingMachine.Response.CANNOT_PERFORM, machine.putCoin2());
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(3, 1);
        machine.exitAdminMode();
        assertEquals(VendingMachine.Response.OK, machine.putCoin2());
        assertEquals(2, machine.getCurrentBalance());
    }
    //endregion

    // region [returnMoney]
    @Test
    void okReturnMoney() {
        assertEquals(VendingMachine.Response.OK, machine.returnMoney());
        assertEquals(0, machine.getCurrentBalance());
        for (int i = 0; i < 8; ++i) {
            machine.putCoin2();
        }
        machine.putCoin1();
        assertEquals(VendingMachine.Response.OK, machine.returnMoney());
        assertEquals(0, machine.getCurrentBalance());
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.exitAdminMode();
        for (int i = 0; i < 7; ++i) {
            machine.putCoin1();
        }
        assertEquals(VendingMachine.Response.OK, machine.returnMoney());
        assertEquals(0, machine.getCurrentBalance());
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.exitAdminMode();
        for (int i = 0; i < 4; ++i) {
            machine.putCoin2();
        }
        assertEquals(VendingMachine.Response.OK, machine.returnMoney());
        assertEquals(0, machine.getCurrentBalance());
        for (int i = 0; i < 4; ++i) {
            machine.putCoin2();
        }
        machine.putCoin1();
        VendingMachine.coinval1 = 3;
        assertEquals(VendingMachine.Response.OK, machine.returnMoney());
        assertEquals(0, machine.getCurrentBalance());
        machine.enterAdminMode(117345294655382L);
        assertEquals(2, machine.getCoins1());
    }
    //endregion

    //region [testingReturnMoneyCoinsVal]
    @Test
    void testingReturnMoneyWithCoinsVal() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 5);
        machine.exitAdminMode();
        for (int i = 0; i < 4; ++i) {
            machine.putCoin2();
        }
        machine.putCoin1();
        assertEquals(VendingMachine.Response.OK, machine.returnMoney());
        assertEquals(0, machine.getCurrentBalance());
        machine.enterAdminMode(117345294655382L);
        assertEquals(5, machine.getCoins2());
        assertEquals(1, machine.getCoins1());
    }
    //endregion

    //region [illegalOperationReturnMoney]
    @Test
    void illegalOperationReturnMoney() {
        machine.enterAdminMode(117345294655382L);
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, machine.returnMoney());
    }
    //endregion

    //region [unsuitableChangeReturnMoney]
    @Test
    void unsuitableChangeReturnMoney() {
        machine.enterAdminMode(117345294655382L);
        machine.fillProducts();
        machine.setPrices(1, 3);
        machine.exitAdminMode();
        for (int i = 0; i < 4; ++i) {
            machine.putCoin2();
        }
        machine.giveProduct1(7);
        VendingMachine.coinval2++;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        assertEquals(VendingMachine.Response.UNSUITABLE_CHANGE, machine.returnMoney());
    }
    //endregion

    //region [tooBigChangeReturnMoney]

    @Test
    void tooBigChangeReturnMoney() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.exitAdminMode();
        VendingMachine.coinval2 = 5;
        VendingMachine.coinval1 = 5;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        VendingMachine.coinval2 = 1;
        VendingMachine.coinval1 = 1;
        assertEquals(VendingMachine.Response.TOO_BIG_CHANGE, machine.returnMoney());

    }
    //endregion

    //region biggerThanCoins2Coinval2ReturnMoney
    @Test
    void biggerThanCoins2Coinval2ReturnMoney() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 3);
        machine.exitAdminMode();
        VendingMachine.coinval2 = 5;
        VendingMachine.coinval1 = 5;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin1();
        }
        VendingMachine.coinval2 = 3;
        VendingMachine.coinval1 = 6;
        assertEquals(VendingMachine.Response.OK, machine.returnMoney());
        machine.enterAdminMode(117345294655382L);
        assertEquals(3, machine.getCoins1());
    }
    //endregion

    //region biggerThanCoins2ReturnMoney
    @Test
    void biggerThanCoins2ReturnMoney() {
        machine.enterAdminMode(117345294655382L);
        machine.fillProducts();
        machine.setPrices(1, 3);
        machine.exitAdminMode();
        for (int i = 0; i < 4; ++i) {
            machine.putCoin2();
        }
        machine.giveProduct1(7);
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        assertEquals(VendingMachine.Response.OK, machine.returnMoney());
    }
    //endregion

    //region balanceModCoinval2ReturnMoney
    @Test
    void balanceModCoinval2ReturnMoney() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 3);
        machine.exitAdminMode();
        VendingMachine.coinval2 = 5;
        VendingMachine.coinval1 = 5;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin1();
        }
        VendingMachine.coinval2 = 5;
        VendingMachine.coinval1 = 6;
        assertEquals(VendingMachine.Response.OK, machine.returnMoney());
        machine.enterAdminMode(117345294655382L);
        assertEquals(0, machine.getCoins2());
    }
    //endregion

    //region equalChangeReturnMoney
    @Test
    void equalChangeReturnMoney() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.exitAdminMode();
        VendingMachine.coinval2 = 5;
        VendingMachine.coinval1 = 5;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        VendingMachine.coinval2 = 2;
        VendingMachine.coinval1 = 7;
        assertEquals(VendingMachine.Response.OK, machine.returnMoney());
    }
    //endregion

    //region [giveProduct1]
    @Test
    void okGiveProduct1() {
        machine.enterAdminMode(117345294655382L);
        machine.fillProducts();
        machine.setPrices(2, 1);
        machine.exitAdminMode();
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        assertEquals(VendingMachine.Response.OK, machine.giveProduct1(1));
        assertEquals(0, machine.getCurrentBalance());
        machine.returnMoney();
        machine.enterAdminMode(117345294655382L);
        assertEquals(1, machine.getCoins2());
        assertEquals(29, machine.getNumberOfProduct1());
        machine.exitAdminMode();
        VendingMachine.coinval1 = 3;
        VendingMachine.coinval2 = 8;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin1();
        }
        machine.putCoin2();
        assertEquals(VendingMachine.Response.OK, machine.giveProduct1(1));
        assertEquals(0, machine.getCurrentBalance());
        machine.returnMoney();
        machine.enterAdminMode(117345294655382L);
        assertEquals(1, machine.getCoins1());
        assertEquals(1, machine.getCoins2());
        assertEquals(28, machine.getNumberOfProduct1());
        machine.exitAdminMode();
        for (int i = 0; i < 8; ++i) {
            machine.putCoin1();
        }
        assertEquals(VendingMachine.Response.OK, machine.giveProduct1(1));
        assertEquals(0, machine.getCurrentBalance());
        machine.returnMoney();
    }
    //endregion

    //region killingCoinval2Coins2Mutant
    @Test
    void killingCoinval2Coins2Mutant() {
        machine.enterAdminMode(117345294655382L);
        machine.fillProducts();
        machine.setPrices(1, 1);
        machine.exitAdminMode();
        VendingMachine.coinval1 = 3;
        VendingMachine.coinval2 = 2;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin1();
        }
        machine.putCoin2();
        machine.putCoin2();
        assertEquals(VendingMachine.Response.OK, machine.giveProduct1(1));
        assertEquals(0, machine.getCurrentBalance());
        machine.enterAdminMode(117345294655382L);
        assertEquals(-5, machine.getCoins1());
        assertEquals(0, machine.getCoins2());
        assertEquals(29, machine.getNumberOfProduct1());
    }
    //endregion

    //region killingCoinval2Coins2MutantProduct2
    @Test
    void killingCoinval2Coins2MutantProduct2() {
        machine.enterAdminMode(117345294655382L);
        machine.fillProducts();
        machine.setPrices(1, 1);
        machine.exitAdminMode();
        VendingMachine.coinval1 = 2;
        VendingMachine.coinval2 = 3;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        machine.putCoin1();
        machine.putCoin1();
        assertEquals(VendingMachine.Response.OK, machine.giveProduct2(1));
        assertEquals(0, machine.getCurrentBalance());
        machine.enterAdminMode(117345294655382L);
        assertEquals(0, machine.getCoins2());
        assertEquals(-1, machine.getCoins1());
        assertEquals(39, machine.getNumberOfProduct2());
    }
    //endregion

    //region [illegalOperationProduct1]
    @Test
    void illegalOperationProduct1() {
        machine.enterAdminMode(117345294655382L);
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, machine.giveProduct1(1));
    }
    //endregion

    //region [invalidParamProduct1]
    @Test
    void invalidParamProduct1() {
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.giveProduct1(0));
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.giveProduct1(31));
    }
    //endregion

    //region [insufficientProduct1]
    @Test
    void insufficientProduct1() {
        assertEquals(VendingMachine.Response.INSUFFICIENT_PRODUCT,
                machine.giveProduct1(machine.getNumberOfProduct1() + 1));
    }
    //endregion

    // region [insufficientMoneyProduct1]
    @Test
    void insufficientMoneyProduct1() {
        machine.enterAdminMode(117345294655382L);
        machine.fillProducts();
        machine.setPrices(2, 1);
        machine.exitAdminMode();
        assertEquals(VendingMachine.Response.INSUFFICIENT_MONEY,
                machine.giveProduct1(machine.getNumberOfProduct1()));
    }
    //endregion

    //region [unsuitableChangeProduct1]
    @Test
    void unsuitableChangeProduct1() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.setPrices(2, 1);
        machine.fillProducts();
        machine.exitAdminMode();
        for (int i = 0; i < 4; ++i) {
            machine.putCoin2();
        }
        machine.giveProduct2(7);
        VendingMachine.coinval2++;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        assertEquals(VendingMachine.Response.UNSUITABLE_CHANGE, machine.giveProduct1(1));
    }
    //endregion

    //region [okGiveProduct2]
    @Test
    void okGiveProduct2() {
        machine.enterAdminMode(117345294655382L);
        machine.fillProducts();
        machine.setPrices(2, 1);
        machine.exitAdminMode();
        for (int i = 0; i < 3; ++i) {
            machine.putCoin1();
        }
        assertEquals(VendingMachine.Response.OK, machine.giveProduct2(1));
        assertEquals(0, machine.getCurrentBalance());
        machine.returnMoney();
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        machine.putCoin1();
        assertEquals(VendingMachine.Response.OK, machine.giveProduct2(1));
        assertEquals(0, machine.getCurrentBalance());
        machine.returnMoney();
        machine.enterAdminMode(117345294655382L);
        assertEquals(0, machine.getCoins2());
        assertEquals(38, machine.getNumberOfProduct2());
        machine.exitAdminMode();
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        VendingMachine.coinval1 = 2;
        VendingMachine.coinval2 = 3;
        assertEquals(VendingMachine.Response.OK, machine.giveProduct2(1));
        assertEquals(0, machine.getCurrentBalance());
        machine.enterAdminMode(117345294655382L);
        assertEquals(1, machine.getCoins1());
        assertEquals(2, machine.getCoins2());
        assertEquals(37, machine.getNumberOfProduct2());
        machine.exitAdminMode();
    }
    //endregion

    //region resEqualsZeroTestProduct1
    @Test
    void resEqualsZeroTestProduct1() {
        machine.enterAdminMode(117345294655382L);
        machine.fillProducts();
        machine.setPrices(2, 1);
        machine.exitAdminMode();
        for (int i = 0; i < 2; ++i) {
            machine.putCoin1();
        }
        machine.exitAdminMode();
        assertEquals(VendingMachine.Response.OK,
                machine.giveProduct1(1));
    }
    //endregion

    //region resEqualsZeroTestProduct2
    @Test
    void resEqualsZeroTestProduct2() {
        machine.enterAdminMode(117345294655382L);
        machine.fillProducts();
        machine.setPrices(1, 2);
        VendingMachine.coinval1 = 2;
        VendingMachine.coinval2 = 1;
        machine.exitAdminMode();
        for (int i = 0; i < 2; ++i) {
            machine.putCoin2();
        }
        machine.exitAdminMode();
        assertEquals(VendingMachine.Response.OK,
                machine.giveProduct2(1));
    }
    //endregion

    //region [illegalOperationProduct2]
    @Test
    void IllegalOperationProduct2() {
        machine.enterAdminMode(117345294655382L);
        assertEquals(VendingMachine.Response.ILLEGAL_OPERATION, machine.giveProduct2(1));
    }
    //endregion

    //region [invalidParamProduct2]
    @Test
    void invalidParamProduct2() {
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.giveProduct2(0));
        assertEquals(VendingMachine.Response.INVALID_PARAM, machine.giveProduct2(41));
    }
    //endregion

    //region [insufficientProduct2]
    @Test
    void insufficientProduct2() {
        assertEquals(VendingMachine.Response.INSUFFICIENT_PRODUCT,
                machine.giveProduct2(machine.getNumberOfProduct2() + 1));
    }
    //endregion

    //region [insufficientMoneyProduct2]
    @Test
    void insufficientMoneyProduct2() {
        machine.enterAdminMode(117345294655382L);
        machine.fillProducts();
        machine.setPrices(2, 1);
        machine.exitAdminMode();
        assertEquals(VendingMachine.Response.INSUFFICIENT_MONEY,
                machine.giveProduct2(machine.getNumberOfProduct2()));
    }
    //endregion

    //region [unsuitableChangeProduct2]
    @Test
    void unsuitableChangeProduct2() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.setPrices(1, 2);
        machine.fillProducts();
        machine.exitAdminMode();
        for (int i = 0; i < 4; ++i) {
            machine.putCoin2();
        }
        machine.giveProduct1(7);
        VendingMachine.coinval2++;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        assertEquals(VendingMachine.Response.UNSUITABLE_CHANGE, machine.giveProduct2(1));
    }
    //endregion

    //region [tooBigChangeProduct2]
    @Test
    void tooBigChangeProduct2() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.setPrices(1, 2);
        machine.fillProducts();
        machine.exitAdminMode();
        VendingMachine.coinval2 = 5;
        VendingMachine.coinval1 = 5;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        VendingMachine.coinval2 = 1;
        VendingMachine.coinval1 = 1;
        assertEquals(VendingMachine.Response.TOO_BIG_CHANGE, machine.giveProduct2(1));
    }
    //endregion

    //region [testingGiveProduct2WithCoinVal]
    @Test
    void testingGiveProduct2WithCoinVal() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 5);
        machine.fillProducts();
        machine.setPrices(2, 1);
        machine.exitAdminMode();
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        machine.putCoin1();
        assertEquals(VendingMachine.Response.OK, machine.giveProduct2(2));
        assertEquals(0, machine.getCurrentBalance());
        machine.enterAdminMode(117345294655382L);
        assertEquals(6, machine.getCoins2());
        assertEquals(1, machine.getCoins1());
    }
    //endregion

    //region [tooBigChangeProduct1]
    @Test
    void TooBigChangeProduct1() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.setPrices(2, 1);
        machine.fillProducts();
        machine.exitAdminMode();
        VendingMachine.coinval2 = 5;
        VendingMachine.coinval1 = 5;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        VendingMachine.coinval2 = 1;
        VendingMachine.coinval1 = 1;
        assertEquals(VendingMachine.Response.TOO_BIG_CHANGE, machine.giveProduct1(1));
    }
    //endregion

    //region equalsToAllMoneyProduct1
    @Test
    void equalsToAllMoneyProduct1() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.setPrices(2, 1);
        machine.fillProducts();
        machine.exitAdminMode();
        VendingMachine.coinval2 = 5;
        VendingMachine.coinval1 = 5;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        VendingMachine.coinval2 = 2;
        VendingMachine.coinval1 = 5;
        assertEquals(VendingMachine.Response.OK, machine.giveProduct1(1));
    }
    //endregion

    //region equalsToAllMoneyProduct2
    @Test
    void equalsToAllMoneyProduct2() {
        machine.enterAdminMode(117345294655382L);
        machine.fillCoins(1, 1);
        machine.setPrices(1, 2);
        VendingMachine.coinval2 = 1;
        VendingMachine.coinval1 = 2;
        machine.fillProducts();
        machine.exitAdminMode();
        VendingMachine.coinval2 = 5;
        VendingMachine.coinval1 = 5;
        for (int i = 0; i < 3; ++i) {
            machine.putCoin1();
        }
        VendingMachine.coinval2 = 5;
        VendingMachine.coinval1 = 2;
        assertEquals(VendingMachine.Response.OK, machine.giveProduct2(1));
    }
    //endregion

    //region killingCoins2ModCoinval2Product2
    @Test
    void killingCoins2ModCoinval2Product2() {
        machine.enterAdminMode(117345294655382L);
        machine.fillProducts();
        machine.setPrices(1, 2);
        machine.exitAdminMode();
        for (int i = 0; i < 3; ++i) {
            machine.putCoin2();
        }
        assertEquals(VendingMachine.Response.OK, machine.giveProduct2(1));
    }
    //endregion
}