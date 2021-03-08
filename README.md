准备内网穿透：
1.登录https://natapp.cn/获取你的authtoken，填到项目下natapp_windows_amd64_2_3_9/config.ini中的authtoken中，就可以运行natapp.exe开启您的内网穿透之旅
在命令行窗户中的Forwarding就是外网可以访问的地址，注意这个地址有时会变化


支付宝电脑支付demo地址，localhost换成你的natapp外网地址：http://localhost:8080/index
修改AlipayConfig类中app_id，merchant_private_key，alipay_public_key，notify_url（改成natapp的Forwarding地址），return_url（改成natapp的Forwarding地址）


支付宝H5支付demo地址，localhost换成你的natapp外网地址：http://localhost:8080/wrapIndex
修改AlipayConfig类中app_id，merchant_private_key，alipay_public_key，notify_url（改成natapp的Forwarding地址），return_url（改成natapp的Forwarding地址）


微信电脑支付demo地址，localhost换成你的natapp外网地址：http://localhost:8080/wxPcIndex
修改WXPayConfig类getAppID()，getMchID()，getKey()，getNotifyUrl()（改成natapp的Forwarding地址）


微信H5支付demo地址，localhost换成你的natapp外网地址：http://localhost:8080/wxWrapIndex
修改WXPayConfig类getAppID()，getMchID()，getKey()，getNotifyUrl()（改成natapp的Forwarding地址）


各个支付方式参考：http://www.yayihouse.com/yayishuwu/chapter/2614