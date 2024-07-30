package com.dos.contrat;

import com.dos.model.Monthly;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class MonthlyFactory<T> {
    List<Monthly<T>> monthResult;
    public void init(T data){
        List<Monthly<T>> initmonth=new ArrayList<>();
        String[] months= Monthly.months;
        for(int i=0;i<months.length;i++){
            initmonth.add(new Monthly<>(i+1,months[i],data));
        }
        setMonthResult(initmonth);
    }

    public abstract void addData(int month,T data);
}
