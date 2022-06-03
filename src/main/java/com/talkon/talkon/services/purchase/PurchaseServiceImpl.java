package com.talkon.talkon.services.purchase;

import com.talkon.talkon.dtos.purchase.PurchaseCoinDto;
import com.talkon.talkon.entities.schedule.Schedule;
import com.talkon.talkon.entities.user.Account;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.enums.PayType;
import com.talkon.talkon.enums.ScheduleStatus;
import com.talkon.talkon.exceptions.purchase.CoinNotEnough;
import com.talkon.talkon.exceptions.purchase.PayTypeNotFoundException;
import com.talkon.talkon.exceptions.user.UserNotFoundException;
import com.talkon.talkon.repositories.schedule.ScheduleRepository;
import com.talkon.talkon.repositories.user.user.AccountRepository;
import com.talkon.talkon.repositories.user.member.mentee.MenteeRepository;
import com.talkon.talkon.repositories.user.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    UserRepository userRepository;
    MenteeRepository menteeRepository;
    AccountRepository accountRepository;
    ScheduleRepository scheduleRepository;


    @Override
    public HttpEntity<?> getPayTypes() {
        PayType[] values = PayType.values();
        return ResponseEntity.ok(values);
    }

    @Override
    public HttpEntity<?> purchaseTalkCoin(PurchaseCoinDto purchaseCoinDto) {

        String id = "c45ab69a-057f-43ae-95b4-e8e7300c4e84";
        PayType payType = null;
        try {
            payType = Enum.valueOf(PayType.class, purchaseCoinDto.getPaytype().trim());
        } catch (Exception e) {
            throw new PayTypeNotFoundException(String.format("%s payment type not found", purchaseCoinDto.getPaytype()));
        }
        if (purchaseCoinDto.getCoin() < 0) {
            throw new CoinNotEnough(String.format("%s not enough", purchaseCoinDto.getCoin()));
        }
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            throw new UserNotFoundException("user not found");
        }
        User user = byId.get();
        Optional<Account> byUserIdAndDeletedFalse = accountRepository.findByUserIdAndDeletedFalse(id);
        Account account;
        if (!byUserIdAndDeletedFalse.isPresent()) {
            account = new Account();
            account.setUser(userRepository.getById(id));
            account.setCreatedAt(LocalDateTime.now());
            account.setCreatedBy(id);
            account.setTalkCoins(0);
            account.setStatus((short) 0);
        } else {
            account = byUserIdAndDeletedFalse.get();
        }
        account.setTalkCoins(account.getTalkCoins() + purchaseCoinDto.getCoin());
        accountRepository.save(account);

        return ResponseEntity.ok("success");
    }

    @Transactional
    @Override
    public HttpEntity<?> purchaseSchedule(String scheduleId) {
        String id = "c45ab69a-057f-43ae-95b4-e8e7300c4e84";
        Optional<Schedule> byId = scheduleRepository.findById(scheduleId);
        if (!byId.isPresent()) {
            throw new IllegalArgumentException("schedule Not Found");
        }
        Schedule schedule = byId.get();

        if (!schedule.getStartDateTime().isAfter(LocalDateTime.now().plusMinutes(1))) {
            schedule.setScheduleStatus(ScheduleStatus.EXPIRED);
            throw new IllegalArgumentException("time expired");

        }
        if (schedule.getMentee() != null || !schedule.getScheduleStatus().equals(ScheduleStatus.NEW)) {
            throw new IllegalArgumentException("selected time is busy");
        }
        Optional<Account> accountOptional = accountRepository.findByUserIdAndDeletedFalse(id);
        if (!accountOptional.isPresent() || schedule.getCost() > accountOptional.get().getTalkCoins()) {
            throw new CoinNotEnough("user coin not enough");
        }
        Account account = accountOptional.get();
        account.setTalkCoins(account.getTalkCoins() - schedule.getCost());
        Optional<Mentee> menteeOptional = menteeRepository.findByUserId(id);
        Mentee mentee = menteeOptional.get();
        schedule.setMentee(mentee);
        schedule.setScheduleStatus(ScheduleStatus.ACCEPTED);
        scheduleRepository.save(schedule);
        accountRepository.save(account);

        return ResponseEntity.ok("schedule purchased");
    }
}
