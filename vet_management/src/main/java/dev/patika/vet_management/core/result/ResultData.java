package dev.patika.vet_management.core.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultData<T> extends Result {
    private T data;
    public ResultData(boolean status, String message, String code,T data) {
        super(status, message, code);
        this.data = data;
    }
}
