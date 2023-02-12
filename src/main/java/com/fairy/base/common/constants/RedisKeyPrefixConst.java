package com.fairy.base.common.constants;

/**
 * redi缓存常量列表值
 *
 * @author 鹿少年
 * @date 2022/7/31 21:02
 */
public class RedisKeyPrefixConst {

    /**用户角色权限*/
    public final static String USER_ROLE_PERMISSIOn = "user:role:permission:";

    /**
     * 产品详情内容缓存前缀
     */
    public final static String PRODUCT_DETAIL_CACHE = "product:detail:cache:";

    /**
     * 产品库存缓存
     */
    public final static String MIAOSHA_STOCK_CACHE_PREFIX = "miaosha:stock:cache:";

    /**
     * 已购买过商品的用户不能再次购买
     */
    public final static String MEMBER_BUYED_MIAOSHA_PREFIX = "buyed:miaosha:";

    /**
     * 秒杀用验证码限流
     */
    public final static String MIAOSHA_VERIFY_CODE_PREFIX = "miaosha:verifycode:";

    /**
     * 秒杀token
     */
    public final static String MIAOSHA_TOKEN_PREFIX = "miaosha:token:";

    /**
     * 秒杀异步下单排队中
     * 0->排队中,-1->秒杀失败,>0 ->秒杀成功,对应的是订单编号
     */
    public final static String MIAOSHA_ASYNC_WAITING_PREFIX = "miaosha:async:waiting:";

    /**
     * 当前正在进行的秒杀商品缓存hash - key
     */
    public final static String FLASH_PROMOTION_PRODUCT_KEY = "flash:promotion:hashtable";

    /**
     * 存储在redis的hashtable当中秒杀活动信息
     */
    public final static String ACTIVE_FLASH_PROMOTION_KEY = "flash:promotion:info";

    /**
     * 当库存减到0时,需要做一次库存同步,存在预减
     */
    public final static String STOCK_REFRESHED_MESSAGE_PREFIX = "stock:refreshed:message:";

    /**
     * redis布隆过滤器key
     */
    public final static String PRODUCT_REDIS_BLOOM_FILTER = "product:redis:bloom:filter";
}
