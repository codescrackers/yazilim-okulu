package com.yazilimokulu.mvc.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.yazilimokulu.mvc.dto.ResponsePageDTO;
import com.yazilimokulu.mvc.dto.UserDTO;
import com.yazilimokulu.mvc.entities.User;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User findByUsername(String username);

    boolean emailExists(String email);

    boolean usernameExists(String username);

    void register(User user);

    void changeEmail(String newEmail, String currentPassword) throws AuthException;

    void changePassword(String newPassword, String currentPassword) throws AuthException;

    void changeProfileInfo(User newProfileInfo);

    void changeAvatar(UploadedAvatarInfo uploadedAvatarInfo);

    void removeAvatar();

    void authenticate(User user);

    boolean isAuthenticated();

    boolean isAdmin();

    User currentUser();

	List<User> findAll();
	
	ResponsePageDTO<UserDTO> findUsersPage(int pageNumber, int pageSize);

}

