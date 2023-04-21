package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.User;

public interface AuthService {

    User login(String username, String password);
}
