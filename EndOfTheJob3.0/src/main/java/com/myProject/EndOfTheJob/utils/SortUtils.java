package com.myProject.EndOfTheJob.utils;
import org.springframework.data.domain.Sort;


public class SortUtils {
	public static Sort basicSort() {
        return basicSort("desc", "id");
    }

    public static Sort basicSort(String orderType, String orderField) {
        Sort sort = Sort.by(Sort.Direction.fromString(orderType), orderField);
        return sort;
    }
    
    public static Sort basicSort(SortDto... dtos) {
        Sort result = null;
        for(int i=0; i<dtos.length; i++) {
            SortDto dto = dtos[i];
            if(result == null) {
                result = Sort.by(Sort.Direction.fromString(dto.getOrderType()), dto.getOrderField());
            } else {
                result = result.and(Sort.by(Sort.Direction.fromString(dto.getOrderType()), dto.getOrderField()));
            }
        }
        return result;
    } 
}
