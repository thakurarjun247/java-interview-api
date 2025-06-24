package java.interview.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.interview.authservice.entity.User;
import java.interview.authservice.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<User> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void deleteUserByEmail(String email) {
        adminRepository.deleteByEmail(email);
    }

    @Transactional
    @Override
    public void deleteAllUsers() {
        adminRepository.deleteAll();
    }
}
