package com.trip.hotel_gabriella.admin.service.admin;

import com.trip.hotel_gabriella.admin.repository.AdminRepository;
import com.trip.hotel_gabriella.common.domain.Admin;
import com.trip.hotel_gabriella.common.security.UserAuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminDetailsServiceImpl implements AdminDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Admin findAdmin =
                adminRepository.findByAccount(account)
                        .orElseThrow(()->new UsernameNotFoundException("Account Not Found"));

        UserAuthInfo userAuthInfo
                = new UserAuthInfo(findAdmin.getAccount(), findAdmin.getPassword());
        userAuthInfo.setRoles("ADMIN");

        userAuthInfo.setExtraInfo("id", findAdmin.getId());

        System.out.println(userAuthInfo.getAccount());
        System.out.println("실제 어드민 로그인 시 여기를 찍는 지 궁금합니다.");

        return userAuthInfo;
    }
}
