package com.railway.biticket.common;

public interface BaseService {
    default boolean isEmpty(Object... properties) {
        for (Object prop : properties) {
            if(prop == null)
                return true;
        }

        return false;
    }
}
