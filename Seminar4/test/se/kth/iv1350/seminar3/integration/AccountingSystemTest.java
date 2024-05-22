import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountingSystemTest {
    private AccountingSystem accountingSystem;

    @BeforeEach
    void setUp() {
        accountingSystem = new AccountingSystem();
    }

    @AfterEach
    void tearDown() {
        accountingSystem = null;
    }

    @Test
    void testUpdateAccountBalance() {
        int accountId = 1;
        double amount = 1000.0;
        accountingSystem.updateAccountBalance(accountId, amount);
        double expectedBalance = 1000.0;
        double actualBalance = accountingSystem.getAccountBalance(accountId);
        assertEquals(expectedBalance, actualBalance, "Account balance not updated correctly.");
    }

    @Test
    void testUpdateAccountBalanceWithNegativeAmount() {
        int accountId = 2;
        double amount = -500.0;
        accountingSystem.updateAccountBalance(accountId, amount);
        double expectedBalance = -500.0;
        double actualBalance = accountingSystem.getAccountBalance(accountId);
        assertEquals(expectedBalance, actualBalance, "Account balance not updated correctly with negative amount.");
    }

    @Test
    void testUpdateAccountBalanceWithZeroAmount() {
        int accountId = 3;
        double amount = 0.0;
        accountingSystem.updateAccountBalance(accountId, amount);
        double expectedBalance = 0.0;
        double actualBalance = accountingSystem.getAccountBalance(accountId);
        assertEquals(expectedBalance, actualBalance, "Account balance not updated correctly with zero amount.");
    }

    @Test
    void testGetAccountBalanceForNonExistentAccount() {
        int nonExistentAccountId = 999;
        double expectedBalance = 0.0;
        double actualBalance = accountingSystem.getAccountBalance(nonExistentAccountId);
        assertEquals(expectedBalance, actualBalance, "Incorrect balance returned for non-existent account.");
    }
}
