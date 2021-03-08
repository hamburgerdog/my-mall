package org.xjosiah.mymall.service;

import org.xjosiah.mymall.common.api.CommonResult;

public interface UmsMemberService {

    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 校验验证码
     * @param telephone
     * @param authCode
     * @return
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
