/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.church;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author andri
 * @param <T> the type of Data to return
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HttpResult<T> {
    T data;
    String status;
    String message;
}
