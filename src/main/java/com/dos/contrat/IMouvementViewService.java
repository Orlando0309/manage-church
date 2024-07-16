package com.dos.contrat;

import com.dos.model.MouvementView;
import jakarta.persistence.criteria.CriteriaQuery;

public interface IMouvementViewService <T extends IEntity<Long>> extends IDAO<T,Long>{
    public void filterByFluxAndYear(CriteriaQuery<MouvementView> cq, String flux, Integer year);
}
