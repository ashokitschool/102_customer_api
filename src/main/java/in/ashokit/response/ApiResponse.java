package in.ashokit.response;

import lombok.Data;

@Data
public class ApiResponse <T>{

    private String msg;
    private Integer status;
    private T data;
}
