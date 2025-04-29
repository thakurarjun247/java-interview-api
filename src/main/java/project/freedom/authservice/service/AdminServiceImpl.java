package project.freedom.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.freedom.authservice.entity.User;
import project.freedom.authservice.repository.AdminRepository;

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
