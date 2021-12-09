package com.darwin.prototype.config.security.sms;

import com.darwin.prototype.base.Verifier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.ClassUtils;


@Setter
@Getter
/**
 * 短信认证 Provider
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    Verifier<String,String> smsVerifier;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken)authentication;
        if (!smsVerifier.verifyAndDelete(smsAuthenticationToken.getPhone(),smsAuthenticationToken.getCaptcha())){
            throw new BadCredentialsException("验证码错误");
        }
        smsAuthenticationToken.setAuthenticated(true);
        return smsAuthenticationToken;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return ClassUtils.isAssignable(SmsAuthenticationToken.class,authentication);
    }
}
