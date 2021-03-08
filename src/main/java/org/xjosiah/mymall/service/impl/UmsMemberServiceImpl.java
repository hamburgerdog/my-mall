package org.xjosiah.mymall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xjosiah.mymall.common.api.CommonResult;
import org.xjosiah.mymall.service.RedisService;
import org.xjosiah.mymall.service.UmsMemberService;

import java.util.Optional;
import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString() + " 获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        //  不用担心NPE是因为StringRedisTemplate中已经进行了处理
        if (telephone.length() == 0)
            return CommonResult.failed("请输入手机号码");
        if (authCode.length() == 0)
            return CommonResult.failed("请输入验证码");

        String inRedisAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        if (inRedisAuthCode == null)
            return CommonResult.failed("请先获取验证码");
        return inRedisAuthCode.equals(authCode) ? CommonResult.success("验证成功")
                : CommonResult.failed("验证失败");
    }
}
