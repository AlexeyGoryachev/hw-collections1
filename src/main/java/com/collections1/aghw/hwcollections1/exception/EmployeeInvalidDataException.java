package com.collections1.aghw.hwcollections1.exception;
//добавляем исключение некорректно введенных данных пользователя
public class EmployeeInvalidDataException extends RuntimeException {
    public EmployeeInvalidDataException(String message) {
        super(message);
    }
}
