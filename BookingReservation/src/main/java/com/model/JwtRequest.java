package com.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequest {
    private String email;
    private String password;

//    private List<GrantedAuthority> authorities;
//    public JwtRequest(User user) {
//        email = user.getEmail();
//        password = user.getPassword();
//        authorities = Arrays.stream(user.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }

}