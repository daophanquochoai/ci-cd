package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.dto.account.AccountRequestDTO;
import com.ptithcm.clinic_booking.dto.account.AccountResponseDTO;
import com.ptithcm.clinic_booking.exception.ResourceAlreadyExistsException;
import com.ptithcm.clinic_booking.exception.ResourceNotFoundException;
import com.ptithcm.clinic_booking.mapper.AccountMapper;
import com.ptithcm.clinic_booking.model.Account;
import com.ptithcm.clinic_booking.repository.AccountRepository;
import com.ptithcm.clinic_booking.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AccountResponseDTO getAccountById(String username) {
        Account account = accountRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản với username: " + username));
        return AccountMapper.toAccountResponseDTO(account);
    }

    @Override
    public void addAccount(AccountRequestDTO accountRequestDTO) {
        if (accountRepository.existsById(accountRequestDTO.getUsername())) {
            throw new ResourceAlreadyExistsException("Tên tài khoản đã tồn tại.");
        }

        Account account = AccountMapper.toAccount(accountRequestDTO);
        account.setPassword(passwordEncoder.encode(accountRequestDTO.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public void updateAccount(AccountRequestDTO accountRequestDTO) {
        Account existingAccount = accountRepository.findById(accountRequestDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản cần cập nhật"));

        existingAccount.getRole().setId(accountRequestDTO.getRoleId());
        existingAccount.setStatus(accountRequestDTO.getStatus());
        accountRepository.save(existingAccount);
    }

    @Transactional
    @Override
    public void softDeleteAccount(String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản cần cập nhật"));
        account.setStatus("DELETED");
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void changePassword(String username, String currentPassword, String newPassword) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Tên tài khoản không hợp lệ"));

        if (!passwordEncoder.matches(currentPassword, account.getPassword())) {
            throw new ResourceNotFoundException("Mật khẩu hiện tại không chính xác");
        }

        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
//        Account account = accountRepository.findByUsernameAndPassword(username, passwordEncoder.encode(currentPassword))
//                .orElseThrow(() -> new ResourceNotFoundException("Tên tài khoản hoặc mật khẩu không hợp lệ"));
//        account.setPassword(passwordEncoder.encode(newPassword));
//        accountRepository.save(account);
    }

    @Override
    public Page<AccountResponseDTO> getAllAccounts(Pageable pageable)  {
        return accountRepository.findAll(pageable)
                .map(AccountMapper::toAccountResponseDTO);
    }

    @Override
    public boolean existsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }
}
