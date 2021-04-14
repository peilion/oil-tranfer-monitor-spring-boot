package cn.peilion.common.constans;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class LoginConstant {
    private List<String> roles = Arrays.asList("admin");
    private String token = "admin";
    private String name = "Super admin";
    private String introduction = "This is superuser";
    private String avatar = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2720094078,3198972262&fm=26&gp=0.jpg";

}


