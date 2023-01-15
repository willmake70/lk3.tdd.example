package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    @Test
    void if_pay_10000_then_expiry_date_after_one_month() {


        assertExpiryDate(PayData.builder()
                            .billingDate(LocalDate.of(2019, 3, 1))
                            .payAmount(10_000)
                            .build(),
                        LocalDate.of(2019, 4, 1));
        assertExpiryDate(PayData.builder()
                            .billingDate(LocalDate.of(2019, 5, 5))
                            .payAmount(10_000)
                            .build(),
                        LocalDate.of(2019, 6, 5));

    }
    @Test
    void if_pay_10000_but_not_expiry_date_after_one_month(){

        assertExpiryDate(PayData.builder()
                            .billingDate(LocalDate.of(2019, 1, 31))
                            .payAmount(10_000)
                            .build(),
                        LocalDate.of(2019, 2, 28));


        assertExpiryDate(PayData.builder()
                            .billingDate(LocalDate.of(2019, 5, 31))
                            .payAmount(10_000)
                            .build(),
                         LocalDate.of(2019, 6, 30));


        assertExpiryDate(PayData.builder()
                            .billingDate(LocalDate.of(2020, 1, 31))
                            .payAmount(10_000)
                            .build(),
                        LocalDate.of(2020, 2, 29));
    }

    @Test
    void if_not_equal_between_billingDate_and_expiryDate_then_pay_10000(){
        PayData payData = PayData.builder().firstBillingDate(LocalDate.of(2019, 1, 31))
                            .billingDate(LocalDate.of(2019, 2, 28))
                            .payAmount(10_000)
                            .build();
        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

        PayData payData2 = payData.builder()
                                .firstBillingDate(LocalDate.of(2019, 1,30))
                                .billingDate(LocalDate.of(2019, 2, 28))
                                .payAmount(10_000)
                                .build();

        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

        PayData payData3 = payData.builder()
                .firstBillingDate(LocalDate.of(2019, 5,31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
    }

    @Test
    void if_pay_greater_and_equal_20000_then_cal_expiry_date_that_proportionally_pay_size(){
        assertExpiryDate(PayData.builder()
                            .billingDate(LocalDate.of(2019, 3, 1))
                            .payAmount(20_000)
                            .build(),
                        LocalDate.of(2019, 5, 1));

        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 6, 1));
    }

    @Test
    void if_not_equal_between_dayOfFirstBillingDate_and_dayOfExpiryDate_and_pay_greater_and_equal_20000(){

        assertExpiryDate(PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 4, 30));

        assertExpiryDate(PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(40_000)
                        .build(),
                LocalDate.of(2019, 6, 30));

        assertExpiryDate(PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 3, 31))
                        .billingDate(LocalDate.of(2019, 4, 30))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 7, 31));

    }

    @Test
    void if_pay_100000_then_offer_1_year_of_service(){

        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019,1, 28)).payAmount(100_000).build(),
                LocalDate.of(2020, 1, 28));
    }

     void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate){

        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);

        assertEquals(expectedExpiryDate, realExpiryDate);

    }
}
