package com.jupiter.androidstudy.sqlite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class StudentBasicInfo {
    String name;
    String password;
    String gender;
    String email;
}
