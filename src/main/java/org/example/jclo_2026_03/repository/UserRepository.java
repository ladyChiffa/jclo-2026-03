package org.example.jclo_2026_03.repository;

import org.example.jclo_2026_03.service.Authorities;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        if (user.equals("oksana")) return List.of(Authorities.READ);
        return new ArrayList<>(); // TODO
    }
}
