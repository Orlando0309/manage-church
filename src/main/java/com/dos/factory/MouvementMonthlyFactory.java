package com.dos.factory;

import com.dos.contrat.MonthlyFactory;
import com.dos.model.Balance;
import com.dos.model.Monthly;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class MouvementMonthlyFactory extends MonthlyFactory<Balance> {


    public void init() {
        List<Monthly<Balance>> initmonth=new ArrayList<>();
        String[] months= Monthly.months;
        for(int i=0;i<months.length;i++){
            initmonth.add(new Monthly<>(i+1,months[i],new Balance(BigDecimal.ZERO,BigDecimal.ZERO)));
        }
        setMonthResult(initmonth);
    }

    @Override
    public void addData(int month, Balance data) {
        List<Monthly<Balance>> currentMonthValue= getMonthResult();
        if(currentMonthValue.isEmpty()){
            this.init(new Balance(BigDecimal.ZERO,BigDecimal.ZERO));
        }
        Monthly<Balance>c=currentMonthValue.get(month-1);
        Balance b=c.getData();
        b.addCredit(data.getCredit());
        b.addDebit(data.getDebit());
        System.out.println("Balance"+b.toString()+c.getData());
        currentMonthValue.get(month-1).setData(b);
        setMonthResult(currentMonthValue);
    }
}
