package com.jarry.serviceutils.DTO;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.serviceutils.DTO
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-18 16:03
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private int id;
    private String userName;
    private String phone;
    private String password;
    private String salt;
    private String head;
    private int loginCount;
    private Date registerDate;
    private Date lastLoginDate;
}
