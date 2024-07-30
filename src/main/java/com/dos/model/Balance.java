package com.dos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Balance {
    BigDecimal credit;
    BigDecimal debit;
    public static  Balance DEFAULT=new Balance(BigDecimal.ZERO,BigDecimal.ZERO);
    public void addCredit(BigDecimal credit){
        setCredit(BigDecimal.valueOf(credit.doubleValue()+this.credit.doubleValue()));
    }
    public void addDebit(BigDecimal debit){
        setDebit(BigDecimal.valueOf(debit.doubleValue()+this.debit.doubleValue()));
    }

    @Override
    public String toString() {
        return "Balance{" +
                "credit=" + credit +
                ", debit=" + debit +
                '}';
    }
}
