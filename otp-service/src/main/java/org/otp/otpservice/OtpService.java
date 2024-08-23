package org.otp.otpservice;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    private static final Integer EXPIRE_MIN = 5;
    private final LoadingCache<String, String> otpCache;

    public OtpService() {
        otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public String load(String s) {
                        return "";
                    }
                });
    }

    public String getOtp(String username) {
        return StringUtils.isNotEmpty(getCacheOtp(username)) ? getCacheOtp(username) : getRandomOTP(username);
    }

    private String getRandomOTP(String username) {
        String otp = new DecimalFormat("0000")
                .format(new Random().nextInt(9999));
        otpCache.put(username, otp);
        return otp;
    }

    public String getCacheOtp(String key) {
        try {
            return otpCache.get(key);
        } catch (Exception e) {
            return "";
        }
    }

    public void clearOtp(String key) {
        otpCache.invalidate(key);
    }
}
