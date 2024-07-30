package com.dos.contrat;

import com.dos.model.Balance;
import com.dos.model.Monthly;

import java.util.List;

public interface IAnnualFilter {
    public List<Monthly<Balance>> filter(Integer year, boolean avg);
}
