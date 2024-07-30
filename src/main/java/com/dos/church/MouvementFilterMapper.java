package com.dos.church;

import com.dos.model.MouvementView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MouvementFilterMapper<T> {
    T selection;
    String aggregate;
}
