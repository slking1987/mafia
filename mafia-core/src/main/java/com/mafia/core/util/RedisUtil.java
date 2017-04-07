package com.mafia.core.util;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by shaolin on 2017/1/22.
 */
@Service
public class RedisUtil {

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    public void counterIncrease(String key, Integer i, Long expSec)
    {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "key is empty");
        Preconditions.checkNotNull(i, "counterIncrease num is null");

        BoundValueOperations<String, Object> b = redisTemplate.boundValueOps(key);
        b.increment(i);
        if(expSec != null && b.getExpire() != null)
        {
            redisTemplate.expire(key, expSec, TimeUnit.SECONDS);
        }
    }

    public Long counterGet(String key)
    {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "key is empty");

        BoundValueOperations<String, Object> b = redisTemplate.boundValueOps(key);
        Object val = b.get(0, -1);
        return val == null ? null : Long.valueOf((String)val);
    }
}
