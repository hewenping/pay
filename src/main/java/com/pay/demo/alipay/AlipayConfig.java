package com.pay.demo.alipay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016110200787383";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHl8OeksSld8MuoYCSda8WKDVmvUaSCSR5qQaH22If9X8DqDpujcpokAADjavhqz8zTO5URLtY+nqx97/u1CUyoOJY1Sk0y9j3Z/QqeCERzLZtg96gDgJgHSrub1FBhZEGwVmKXfDfLC20jtWgrPYgZqEsBrWYEEyV7RI+5KugtUgHFWys7AB2IvaXSYU2KiwqA+fXQ3m2L2wh9h2PfZfkk9jJCXt3YWZoUPzO0m84+fRZoYgBfB+2H2OEAIiD8ngWi4OCdkvcabckqkyrY3SYT2FIWmFkgC6J6TbXKE52PG7mCoDT86mPHNc/gUz839N4wYzbI5TLFfpUMR5XbNqRAgMBAAECggEAcXP25lP/3kPbOMVBg0qc+XkjVZfB2Kd1saZQJJTYAc20PSch3Sce2WbD3DVKQES9WPth33izGbxbtVWLbxZrGXWsbo/NLrPyEvEUSATEBtS3P+M6/8k5C5rcNS1HW/+aXYCinzgDN3kLtSxea1bNikbVTLVAdMAOlNQMlDtZr9k4R7Y6ynj9nWpAmPp+4Tanwkx+pdYOCkyUKFFn13jbRkEAI1mHZDyrih+mf0EPuoum3av7jxetkoLVF1KCLApSDrqfZkiQSNnPB0PI4QI1UwnVCpdl/JaAYiu3u+cbfsLGCV3QfI8DwWdlOKYcS4jw+tRQrY14d5tVFj28zSH6wQKBgQD+Eed9mh02iQRTy/l7EVFA8JUo23k+sHLnIn3B3DXoB2oeoSKNf1CJx8oNJO0IXFGTfCIWkPaLf0zakryyEEbb+64bficknOs4d4ccig4tYt6pmeGlFAZgHea8L9t6s0k97eynLbdtYwFsbtgexac6u/v6uCn/7T7H7e6rVXv+PQKBgQCIn3RluVLZlRd5dC9EhREMRcsjz8DzQdERZwv5bzEdufYjh+WI72ahxc3M9D0i6NrKjZkFRCM4elhfU7cpFyhg9bzw8rMtXc+KOv1apZq3IlJ78R3Y4qvUPrmfnGGdMBHGfXxstrB1r5M3bKWScwcW/hF2tvSMfuKMHrm5Q5EG5QKBgQDpYDraVcD1wiAWdOuiDwMq+pKYqBkT63JwO292rjdZ/c2NewfJH6Q0Gy/OqReNsXdBWSnavqeo9XL75wOrMGZWVTXVjSiZagZqpOiX33wsVoAfG/l38A/3JCA4xmD9sUfaufzCh6zQrru1BZBBm98DhX9tCtORgTRE45pzCR/PJQKBgFlLbrY1xDK1wVv+Vg6Asj3FOBo15TSYpd6r7SWgSQfWzUs3vQwc/IVrVTFJyNOSQb31gjfw6tw1CqFzg9j1a8zDkDQjTHywPb9pAsJGTrK3WhXr58gjjo7wWD475YjpLBUBW1tqpDanBJ9LAYSQbefXMu7WcUuKeuN+CYFO6fEJAoGAAVFtaOUrT05xN08gjACvxn1NwwiKAVRDoGzqJP9hV5VZrK4AYlwwt4MlqRLWA2xXMiiQC5doFFQy7VNB9HBRyg/FX1Ge8y88q3g2pmkSfu85u52odpOb4Vw7Qcwy+wtz7/osz/3VzCIPwn8+8nPxVTUqYhtrMi7xW8GQpkIP244=";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhWwKxawQgQeeexXNbTJq1kwFVD80HkbkrkPepL0NWH17MjU1SSfyBaSFYWmWW5UktWsFrt6KHXTbHjYTx2oEmObSFYN8/NboOXlgXewvFu6xsv37ON46WSOrgWU/E87Ugxp60sX8IPajgS5OfS43fJL3vwFPOoOd/dD5dWLvjXIXROy2Qohna5EmkZkKckhO4BMBS1acoYQUhu3YZmzMvnCde+iVlARhZG30zmI2My/gbR7sU/054VRxCEqUmZJ3gF7IS0wA3/kcwlGzyTzOFuR2It0PrESdunV3fXn3h5ao/6RFTk/0ZvRDBK/1XGdSLVWuUtzHPPYyISR6gm1nuwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://nzwhd7.natappfree.cc/payNotify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://nzwhd7.natappfree.cc/payReturn";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关

	//public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

